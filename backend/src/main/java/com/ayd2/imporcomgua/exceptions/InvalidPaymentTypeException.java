package com.ayd2.imporcomgua.exceptions;

import com.ayd2.imporcomgua.models.sale.PaymentType;

public class InvalidPaymentTypeException extends ServiceException {
    public InvalidPaymentTypeException() {
        super("Tipo de pago no valido para esta operacion");
    }
    
    public InvalidPaymentTypeException(String clientId, PaymentType paymentType) {
        super(String.format("Cliente %s no esta autorizado a pagar con %s", clientId, paymentType));
    }
}