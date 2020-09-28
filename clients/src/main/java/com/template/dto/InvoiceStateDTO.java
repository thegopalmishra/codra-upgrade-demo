package com.template.dto;

import java.util.Date;

public class InvoiceStateDTO {
    private String issuer;
    private String owner;
    private String payTermDescription;
    private String currencyCode;
    private String invoiceTransactionType;
    private int policyNumber;
    private int coverageCode;
    private String coverageName;
    private String policyEventType;
    private Date installmentDueDate;
    private int invoiceNumber;
    private int invoiceLineNumber;
    private String financialTransactionCode;
    private int financialTransactionAmt;
    private String apStatus;
    private String payToID;
    private String payeeName;
    private String invoiceTransactionID;
    private String remarks;

    public InvoiceStateDTO(String issuer, String owner, String payTermDescription, String currencyCode, String invoiceTransactionType, int policyNumber, int coverageCode, String coverageName, String policyEventType, Date installmentDueDate, int invoiceNumber, int invoiceLineNumber, String financialTransactionCode, int financialTransactionAmt, String apStatus, String payToID, String payeeName, String invoiceTransactionID, String remarks) {
        this.issuer = issuer;
        this.owner = owner;
        this.payTermDescription = payTermDescription;
        this.currencyCode = currencyCode;
        this.invoiceTransactionType = invoiceTransactionType;
        this.policyNumber = policyNumber;
        this.coverageCode = coverageCode;
        this.coverageName = coverageName;
        this.policyEventType = policyEventType;
        this.installmentDueDate = installmentDueDate;
        this.invoiceNumber = invoiceNumber;
        this.invoiceLineNumber = invoiceLineNumber;
        this.financialTransactionCode = financialTransactionCode;
        this.financialTransactionAmt = financialTransactionAmt;
        this.apStatus = apStatus;
        this.payToID = payToID;
        this.payeeName = payeeName;
        this.invoiceTransactionID = invoiceTransactionID;
        this.remarks = remarks;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getPayTermDescription() {
        return payTermDescription;
    }

    public void setPayTermDescription(String payTermDescription) {
        this.payTermDescription = payTermDescription;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getInvoiceTransactionType() {
        return invoiceTransactionType;
    }

    public void setInvoiceTransactionType(String invoiceTransactionType) {
        this.invoiceTransactionType = invoiceTransactionType;
    }

    public int getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(int policyNumber) {
        this.policyNumber = policyNumber;
    }

    public int getCoverageCode() {
        return coverageCode;
    }

    public void setCoverageCode(int coverageCode) {
        this.coverageCode = coverageCode;
    }

    public String getCoverageName() {
        return coverageName;
    }

    public void setCoverageName(String coverageName) {
        this.coverageName = coverageName;
    }

    public String getPolicyEventType() {
        return policyEventType;
    }

    public void setPolicyEventType(String policyEventType) {
        this.policyEventType = policyEventType;
    }

    public Date getInstallmentDueDate() {
        return installmentDueDate;
    }

    public void setInstallmentDueDate(Date installmentDueDate) {
        this.installmentDueDate = installmentDueDate;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public int getInvoiceLineNumber() {
        return invoiceLineNumber;
    }

    public void setInvoiceLineNumber(int invoiceLineNumber) {
        this.invoiceLineNumber = invoiceLineNumber;
    }

    public String getFinancialTransactionCode() {
        return financialTransactionCode;
    }

    public void setFinancialTransactionCode(String financialTransactionCode) {
        this.financialTransactionCode = financialTransactionCode;
    }

    public int getFinancialTransactionAmt() {
        return financialTransactionAmt;
    }

    public void setFinancialTransactionAmt(int financialTransactionAmt) {
        this.financialTransactionAmt = financialTransactionAmt;
    }

    public String getApStatus() {
        return apStatus;
    }

    public void setApStatus(String apStatus) {
        this.apStatus = apStatus;
    }

    public String getPayToID() {
        return payToID;
    }

    public void setPayToID(String payToID) {
        this.payToID = payToID;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getInvoiceTransactionID() {
        return invoiceTransactionID;
    }

    public void setInvoiceTransactionID(String invoiceTransactionID) {
        this.invoiceTransactionID = invoiceTransactionID;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "InvoiceStateDTO{" +
                "issuer='" + issuer + '\'' +
                ", owner='" + owner + '\'' +
                ", payTermDescription='" + payTermDescription + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                ", invoiceTransactionType='" + invoiceTransactionType + '\'' +
                ", policyNumber=" + policyNumber +
                ", coverageCode=" + coverageCode +
                ", coverageName='" + coverageName + '\'' +
                ", policyEventType='" + policyEventType + '\'' +
                ", installmentDueDate=" + installmentDueDate +
                ", invoiceNumber=" + invoiceNumber +
                ", invoiceLineNumber=" + invoiceLineNumber +
                ", financialTransactionCode='" + financialTransactionCode + '\'' +
                ", financialTransactionAmt=" + financialTransactionAmt +
                ", apStatus='" + apStatus + '\'' +
                ", payToID='" + payToID + '\'' +
                ", payeeName='" + payeeName + '\'' +
                ", invoiceTransactionID='" + invoiceTransactionID + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
