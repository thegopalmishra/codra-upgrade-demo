package com.template.contracts;

import com.template.states.InvoiceState;
import net.corda.core.contracts.CommandData;
import net.corda.core.contracts.CommandWithParties;
import net.corda.core.contracts.Contract;
import net.corda.core.contracts.ContractState;
import net.corda.core.transactions.LedgerTransaction;

import java.util.List;

public class InvoiceContract implements Contract {
    public static String ID = "com.template.contracts.InvoiceContract";

    public void verify(LedgerTransaction tx) throws IllegalArgumentException {
        List<ContractState> inputs = tx.getInputStates();
        List<ContractState> outputs = tx.getOutputStates();

        List<CommandWithParties<CommandData>> commands = tx.getCommands();
        if (commands.size() != 1) throw new IllegalArgumentException("Tx should have one command");
        if (!(commands.get(0).getValue() instanceof InvoiceContract.Commands))
            throw new IllegalArgumentException("Must be and instance of Commands");

        CommandData currentCommand = commands.get(0).getValue();

        if (currentCommand instanceof Commands.Issue) {
            //check for shape of transactions
            if (inputs.size() != 0) throw new IllegalArgumentException("Must have zero Inputs");
            if (outputs.size() != 1) throw new IllegalArgumentException("Must have one Outputs");
            if (!(outputs.get(0) instanceof InvoiceState))
                throw new IllegalArgumentException("Output must be of type InvoiceState");

//            InvoiceState invoiceStateIp = (InvoiceState) inputs.get(0);
            InvoiceState invoiceStateOp = (InvoiceState) outputs.get(0);

            //check contents of the state
            if (!(invoiceStateOp.getFinancialTransactionAmt() > 0))
                throw new IllegalArgumentException("Amount must be greater than zero");

            if (invoiceStateOp.getParticipants().size() != 2)
                throw new IllegalArgumentException("There must be two participants");


            //check required signers
            if (!(commands.get(0).getSigners().contains(invoiceStateOp.getIssuer().getOwningKey())))
                throw new IllegalArgumentException("issuer must be required signer");

            if (!(commands.get(0).getSigners().contains(invoiceStateOp.getOwner().getOwningKey())))
                throw new IllegalArgumentException("owner must be required signer");

        }
        else if (currentCommand instanceof Commands.Transfer) {
            // Transfer transaction rules...
        }
        else if (currentCommand instanceof Commands.Update) {
            //check for shape of transactions
            if (inputs.size() != 1) throw new IllegalArgumentException("Must have one Input");
            if (outputs.size() != 1) throw new IllegalArgumentException("Must have one Output");
            if (!(outputs.get(0) instanceof InvoiceState))
                throw new IllegalArgumentException("Output must be of type InvoiceState");

            InvoiceState invoiceStateIp = (InvoiceState) inputs.get(0);
            InvoiceState invoiceStateOp = (InvoiceState) outputs.get(0);

            //check contents of the state
            if (!(invoiceStateIp.getFinancialTransactionAmt() > 0))
                throw new IllegalArgumentException("Amount must be greater than zero");

            //check contents of the state
            if (!(invoiceStateOp.getFinancialTransactionAmt() > 0))
                throw new IllegalArgumentException("Amount must be greater than zero");


            if (invoiceStateOp.getParticipants().size() != 2)
                throw new IllegalArgumentException("There must be two participants");


            //check required signers
            if (!(commands.get(0).getSigners().contains(invoiceStateOp.getIssuer().getOwningKey())))
                throw new IllegalArgumentException("issuer must be required signer");

            if (!(commands.get(0).getSigners().contains(invoiceStateOp.getOwner().getOwningKey())))
                throw new IllegalArgumentException("owner must be required signer");
        } else if (currentCommand instanceof Commands.Exit) {
            // Exit transaction rules...
        } else throw new IllegalArgumentException("Unrecognised command.");
    }


    public interface Commands extends CommandData {
        class Issue implements InvoiceContract.Commands { }
        class Transfer implements InvoiceContract.Commands { }
        class Update implements InvoiceContract.Commands { }
        class Exit implements InvoiceContract.Commands { }
    }
}
