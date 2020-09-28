package com.template.flows;

import co.paralleluniverse.fibers.Suspendable;
import com.google.common.collect.ImmutableList;
import com.template.contracts.InvoiceContract;
import com.template.states.InvoiceState;
import net.corda.core.contracts.StateAndRef;
import net.corda.core.flows.*;
import net.corda.core.identity.CordaX500Name;
import net.corda.core.identity.Party;
import net.corda.core.transactions.SignedTransaction;
import net.corda.core.transactions.TransactionBuilder;
import net.corda.core.utilities.ProgressTracker;
import net.corda.core.contracts.CommandData;

import java.util.Date;

import static java.util.Collections.singletonList;

@InitiatingFlow
@StartableByRPC
public class InvoiceUpdateFlowInitiator extends FlowLogic<SignedTransaction> {
    private final CordaX500Name issuer;
    private final CordaX500Name owner;
    private final String payTermDescription;
    private final String currencyCode;
    private final String invoiceTransactionType;
    private final int policyNumber;
    private final int coverageCode;
    private final String coverageName;
    private final String policyEventType;
    private final Date installmentDueDate;
    private final int invoiceNumber;
    private final int invoiceLineNumber;
    private final String financialTransactionCode;
    private final int financialTransactionAmt;
    private final String apStatus;
    private final String payToID;
    private final String payeeName;
    private final String invoiceTransactionID;
    private final String remarks;

    private final StateAndRef<InvoiceState> prevState;

    public InvoiceUpdateFlowInitiator(CordaX500Name issuer, CordaX500Name owner, String payTermDescription, String currencyCode, String invoiceTransactionType, int policyNumber, int coverageCode, String coverageName, String policyEventType, Date installmentDueDate, int invoiceNumber, int invoiceLineNumber, String financialTransactionCode, int financialTransactionAmt, String apStatus, String payToID, String payeeName, String invoiceTransactionID, String remarks, StateAndRef<InvoiceState> prevState) {
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
        this.prevState = prevState;
    }

    private final ProgressTracker progressTracker = new ProgressTracker();

    @Override
    public ProgressTracker getProgressTracker() {
        return progressTracker;
    }

    @Suspendable
    @Override
    public SignedTransaction call() throws FlowException {
        // We choose our transaction's notary (the notary prevents double-spends).
        Party notary = getServiceHub().getNetworkMapCache().getNotaryIdentities().get(0);
        // We get a reference to our own identity.
        Party issuerParty = getIssuer();
        Party ownerParty = getOwner();

        /* ============================================================================
         *         Create our InvoiceState to represent on-ledger tokens!
         * ===========================================================================*/
        // We create our new TokenState.
        InvoiceState invoiceState = new InvoiceState(issuerParty, ownerParty, payTermDescription, currencyCode,
                invoiceTransactionType, policyNumber, coverageCode, coverageName, policyEventType, installmentDueDate,
                invoiceNumber, invoiceLineNumber, financialTransactionCode, financialTransactionAmt,
                apStatus, payToID, payeeName, invoiceTransactionID, remarks);
        CommandData commandData = new InvoiceContract.Commands.Update();

        /* ============================================================================
         *      Build our invoice issuance transaction to update the ledger!
         * ===========================================================================*/
        // We build our transaction.
        TransactionBuilder transactionBuilder = new TransactionBuilder(notary);
        transactionBuilder.addInputState(prevState);
        transactionBuilder.addOutputState(invoiceState, InvoiceContract.ID);
        transactionBuilder.addCommand(commandData, issuerParty.getOwningKey(), ownerParty.getOwningKey());


        /* ============================================================================
         *          InvoiceContract to control token issuance!
         * ===========================================================================*/
        // We check our transaction is valid based on its contracts.
        transactionBuilder.verify(getServiceHub());

        FlowSession ownerSession = initiateFlow(ownerParty);
//        FlowSession issuerSession = initiateFlow(issuerParty);

//        PublicKey ourSigningKey = ownerParty.getOwningKey();
//        SignedTransaction partSignedTx = getServiceHub().signInitialTransaction(transactionBuilder, ourSigningKey);

        // We sign the transaction with our private key, making it immutable.
         SignedTransaction signedTransaction = getServiceHub().signInitialTransaction(transactionBuilder);

        // The counterparty signs the transaction
         SignedTransaction fullySignedTransaction = subFlow(new CollectSignaturesFlow(signedTransaction, singletonList(ownerSession)));

//        SignedTransaction fullySignedTransaction = subFlow(new CollectSignaturesFlow(
//                partSignedTx,
//                ImmutableList.of(ownerSession, issuerSession),
//                singletonList(ourSigningKey))
//        );

        // We get the transaction notarised and recorded automatically by the platform.
//        return subFlow(new FinalityFlow(fullySignedTransaction, ImmutableList.of(ownerSession, issuerSession)));
        return subFlow(new FinalityFlow(fullySignedTransaction, ImmutableList.of(ownerSession)));
    }

    Party getIssuer(){
        if(issuer != null)
            return getServiceHub().getNetworkMapCache().getPeerByLegalName(issuer);
        else
            return getOurIdentity();
    }

    Party getOwner(){
        if(owner != null)
            return getServiceHub().getNetworkMapCache().getPeerByLegalName(owner);
        else
            return null;
    }
}
