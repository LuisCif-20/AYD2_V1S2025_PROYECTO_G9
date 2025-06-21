package com.ayd2.imporcomgua.controllers.payment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayd2.imporcomgua.dto.payment.NewPaymentRequestDTO;
import com.ayd2.imporcomgua.dto.payment.PaymentResponseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1.0/payments")
public class PaymentController {
    @PostMapping
    public ResponseEntity<PaymentResponseDTO> createPayment(
        @RequestBody @Valid NewPaymentRequestDTO paymentRequestDTO
    ) {
        // Aquí iría la lógica para crear un pago
        // Por ahora, retornamos un ResponseEntity vacío
        return ResponseEntity.ok(new PaymentResponseDTO(
            null, // receipt_number
            null, // sale
            null, // bank
            null, // accountNumber
            null, // transactionNumber
            null  // amount
        ));
    }
}
