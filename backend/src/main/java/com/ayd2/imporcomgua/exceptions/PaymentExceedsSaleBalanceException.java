package com.ayd2.imporcomgua.exceptions;

public class PaymentExceedsSaleBalanceException extends ServiceException {
    
    public PaymentExceedsSaleBalanceException() {
        super("El monto del pago excede el saldo pendiente de la venta");
    }
    
    public PaymentExceedsSaleBalanceException(String message) {
        super(message);
    }
    
    public PaymentExceedsSaleBalanceException(Double paymentAmount, Double remainingBalance) {
        super(String.format(
            "El monto del pago (%.2f) excede el saldo pendiente de la venta (%.2f)", 
            paymentAmount, 
            remainingBalance
        ));
    }
}