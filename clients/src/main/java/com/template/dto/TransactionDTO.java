package com.template.dto;

public class TransactionDTO {
    private InvoiceStateDTO transactionDetails;

    public InvoiceStateDTO getTransactionDetails() {
        return transactionDetails;
    }

    public void setTransactionDetails(InvoiceStateDTO transactionDetails) {
        this.transactionDetails = transactionDetails;
    }

    @Override
    public String toString() {
        return "TransactionDTO{" +
                ", transactionDetails=" + transactionDetails +
                '}';
    }
}
