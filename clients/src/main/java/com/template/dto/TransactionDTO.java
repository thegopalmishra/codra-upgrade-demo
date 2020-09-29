package com.template.dto;

public class TransactionDTO {
    private InvoiceStateDTO transactionDetails;
    private String prevStateId;

    public InvoiceStateDTO getTransactionDetails() {
        return transactionDetails;
    }
    public void setTransactionDetails(InvoiceStateDTO transactionDetails) {
        this.transactionDetails = transactionDetails;
    }

    public String getPrevStateId() {
        return prevStateId;
    }

    public void setPrevStateId(String prevStateId) {
        this.prevStateId = prevStateId;
    }

    @Override
    public String toString() {
        return "TransactionDTO{" +
                ", transactionDetails=" + transactionDetails +
                ", prevStateId=" + prevStateId +
                '}';
    }
}
