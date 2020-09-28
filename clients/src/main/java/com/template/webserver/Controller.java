package com.template.webserver;

import com.template.dto.TransactionDTO;
import com.template.flows.InvoiceIssueFlowInitiator;
import net.corda.core.identity.CordaX500Name;
import net.corda.core.identity.Party;
import net.corda.core.messaging.CordaRPCOps;
import net.corda.core.transactions.SignedTransaction;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Define your API endpoints here.
 */
@RestController
@RequestMapping("/") // The paths for HTTP requests are relative to this base path.
public class Controller {
    private final CordaRPCOps proxy;
    private final static Logger logger = LoggerFactory.getLogger(Controller.class);

    public Controller(NodeRPCConnection rpc) {
        this.proxy = rpc.proxy;
    }

    @GetMapping(value = "/templateendpoint", produces = "text/plain")
    private String templateendpoint() {
        return "Define an endpoint here.";
    }


    @PostMapping(value = "/transfererupeetokens" , produces =  APPLICATION_JSON_VALUE , headers =  "Content-Type=application/json" )
    public ResponseEntity<String> transferERupeeTokens(@RequestBody TransactionDTO transactionDTO) throws IllegalArgumentException, ParseException {

        if(transactionDTO.getTransactionDetails() == null || transactionDTO.getTransactionDetails().equals("")){
            throw new IllegalArgumentException("Invalid arguments. Arguments cannot be empty or null");
        }

//        long lAmount = transactionDTO.getTransactionDetails().getFinancialTransactionAmt();

//        String remarks = transactionDetails.toString();
//        String destinationParty = initiatorNode.toString();

        String ownerName = transactionDTO.getTransactionDetails().getOwner();
        String issuerName = transactionDTO.getTransactionDetails().getIssuer();
        System.out.println(issuerName);
        System.out.println(ownerName);
////
        CordaX500Name ownerPartyX500Name = CordaX500Name.parse(getPartyX500String(ownerName));
//        Party ownerParty = proxy.wellKnownPartyFromX500Name(ownerPartyX500Name);

        CordaX500Name issuerPartyX500Name = CordaX500Name.parse(getPartyX500String(issuerName));
//        Party issuerParty = proxy.wellKnownPartyFromX500Name(issuerPartyX500Name);

//        System.out.println(ownerParty);
//        System.out.println(issuerParty);

        // Do Transaction
        try {
            // Start the InvoiceIssueFlowInitiator.
            SignedTransaction result
                    = proxy.startTrackedFlowDynamic(InvoiceIssueFlowInitiator.class,
                    issuerPartyX500Name,
                    ownerPartyX500Name,
                    transactionDTO.getTransactionDetails().getPayTermDescription(),
                    transactionDTO.getTransactionDetails().getCurrencyCode(),
                    transactionDTO.getTransactionDetails().getInvoiceTransactionType(),
                    transactionDTO.getTransactionDetails().getPolicyNumber(),
                    transactionDTO.getTransactionDetails().getCoverageCode(),
                    transactionDTO.getTransactionDetails().getCoverageName(),
                    transactionDTO.getTransactionDetails().getPolicyEventType(),
                    transactionDTO.getTransactionDetails().getInstallmentDueDate(),
                    transactionDTO.getTransactionDetails().getInvoiceNumber(),
                    transactionDTO.getTransactionDetails().getInvoiceLineNumber(),
                    transactionDTO.getTransactionDetails().getFinancialTransactionCode(),
                    transactionDTO.getTransactionDetails().getFinancialTransactionAmt(),
                    transactionDTO.getTransactionDetails().getApStatus(),
                    transactionDTO.getTransactionDetails().getPayToID(),
                    transactionDTO.getTransactionDetails().getPayeeName(),
                    transactionDTO.getTransactionDetails().getInvoiceTransactionID(),
                    transactionDTO.getTransactionDetails().getRemarks())
                    .getReturnValue().get();
            // Return the response.
            String retString = "{\n" +
                    "  \"Transaction ID\" " + ":" + " \"" + result.getId() + "\" " +"\n" +
                    "}";
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(retString);
            // For the purposes of this demo app, we do not differentiate by exception type.
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    String getPartyX500String(String partyName){
        String partyX500String = null;
        switch (partyName){
            case "PartyA":
                partyX500String = "O=PartyA,L=London,C=GB";
                break;
            case "PartyB":
                partyX500String = "O=PartyB,L=New York,C=US";
                break;
            case "Notary":
                partyX500String = "O=Notary,L=London,C=GB";
                break;
        }
        return partyX500String;
    }

}