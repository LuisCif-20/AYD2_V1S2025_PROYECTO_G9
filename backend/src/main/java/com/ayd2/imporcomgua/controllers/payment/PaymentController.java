package com.ayd2.imporcomgua.controllers.payment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayd2.imporcomgua.dto.payment.NewPaymentRequestDTO;
import com.ayd2.imporcomgua.dto.payment.PaymentResponseDTO;
import com.ayd2.imporcomgua.exceptions.DuplicatedEntityException;
import com.ayd2.imporcomgua.exceptions.NotFoundException;
import com.ayd2.imporcomgua.exceptions.PaymentExceedsSaleBalanceException;
import com.ayd2.imporcomgua.services.payment.PaymentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1.0/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    // @PreAuthorize("hasAnyAuthority('GERENTE_GENERAL', 'GERENTE_VENTAS_FINANZAS')")
    public ResponseEntity<PaymentResponseDTO> createPayment(
            @RequestBody @Valid NewPaymentRequestDTO paymentRequestDTO)
            throws NotFoundException, PaymentExceedsSaleBalanceException, DuplicatedEntityException {

        PaymentResponseDTO responseDTO = paymentService.createPayment(paymentRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}
