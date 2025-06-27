package com.ayd2.imporcomgua.services.mail;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.ayd2.imporcomgua.models.user.UserAccount;
import com.ayd2.imporcomgua.models.warehouse.Inventory;
import com.ayd2.imporcomgua.repositories.user.UserAccountRepository;
import com.ayd2.imporcomgua.repositories.warehouse.InventoryRepository;
import com.ayd2.imporcomgua.specifications.user.UserAccountSpecs;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class MailServiceImpl implements MailService {

    private final InventoryRepository inventoryRepository;
    private final TemplateEngine templateEngine;
    private final UserAccountRepository userAccountRepository;
    private final JavaMailSender javaMailSender;

    @Override
    public boolean sendInventoryNotification(Inventory inventory) {
        if (Boolean.TRUE.equals(inventory.getIsLowStockAlertSent()) && inventory.getAvailableQuantity() != 0) {
            return false;
        }

        if (inventory.getAvailableQuantity() > inventory.getLowStockThreshold()) {
            return false;
        }

        Specification<UserAccount> userSpec = Specification.anyOf(
                UserAccountSpecs.hasRoleName("GERENTE_GENERAL"),
                UserAccountSpecs.hasRoleName("GERENTE_INVENTARIO")).and(
                        UserAccountSpecs.isActive(true));

        List<UserAccount> recipients = userAccountRepository.findAll(userSpec);

        // Enviar el correo a cada usuario
        for (UserAccount user : recipients) {
            try {
                sendInventoryEmail(user.getEmail(), String.format("%s %s", user.getFirstName(), user.getLastName()), inventory);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }

        inventory.setIsLowStockAlertSent(true);
        inventoryRepository.save(inventory);

        return true;
    }

    private void sendInventoryEmail(String email, String userFullName, Inventory inventory) throws MessagingException {
        Context context = new Context();
        context.setVariable("userFullName", userFullName);
        context.setVariable("product", inventory.getProduct());
        context.setVariable("inventory", inventory);
        context.setVariable("suggestedAmount", inventory.getLowStockThreshold() - inventory.getAvailableQuantity());

        String body = templateEngine.process("low-stock-alert", context);
        sendMail(email, "Alerta de Bajo Inventario", body);
    }

    private void sendMail(String email, String subject, String body) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText(body, true);
        javaMailSender.send(message);
    }
}
