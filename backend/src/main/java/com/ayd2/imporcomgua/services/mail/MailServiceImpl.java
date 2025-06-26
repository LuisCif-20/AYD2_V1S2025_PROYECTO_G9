package com.ayd2.imporcomgua.services.mail;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;

import com.ayd2.imporcomgua.models.warehouse.Inventory;
import com.ayd2.imporcomgua.properties.smtp.SmtpProperties;
import com.ayd2.imporcomgua.repositories.warehouse.InventoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class MailServiceImpl implements MailService{
    private final InventoryRepository inventoryRepository;
    private final SmtpProperties smtpProperties;
    private final TemplateEngine templateEngine;

    @Override
    public void sendInventoryNotification(Inventory inventory) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendInventoryNotification'");
    }
    
}
