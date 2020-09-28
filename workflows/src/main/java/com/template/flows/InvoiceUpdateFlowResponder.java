package com.template.flows;

import co.paralleluniverse.fibers.Suspendable;
import net.corda.core.flows.*;
import net.corda.core.transactions.SignedTransaction;

@InitiatedBy(InvoiceUpdateFlowInitiator.class)
public class InvoiceUpdateFlowResponder extends FlowLogic<Void> {

    private final FlowSession otherSide;

    public InvoiceUpdateFlowResponder(FlowSession otherSide) {
        this.otherSide = otherSide;
    }

    @Override
    @Suspendable
    public Void call() throws FlowException {
        SignedTransaction signedTransaction = subFlow(new SignTransactionFlow(otherSide) {
            @Suspendable
            @Override
            protected void checkTransaction(SignedTransaction stx) throws FlowException {
                // For node level constraints
                // Implement responder flow transaction checks here
            }
        });
        subFlow(new ReceiveFinalityFlow(otherSide, signedTransaction.getId()));
        return null;
    }
}