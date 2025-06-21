package com.ayd2.imporcomgua.services.payment;

import com.ayd2.imporcomgua.dto.payment.NewPaymentRequestDTO;
import com.ayd2.imporcomgua.dto.payment.PaymentResponseDTO;
import com.ayd2.imporcomgua.exceptions.DuplicatedEntityException;
import com.ayd2.imporcomgua.exceptions.NotFoundException;
import com.ayd2.imporcomgua.exceptions.PaymentExceedsSaleBalanceException;

public interface PaymentService {
    PaymentResponseDTO createPayment(NewPaymentRequestDTO paymentRequestDTO) throws NotFoundException, DuplicatedEntityException, PaymentExceedsSaleBalanceException;
}
