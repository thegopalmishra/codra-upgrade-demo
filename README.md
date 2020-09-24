# Invoice CorDapp (Upgrade Corda to 4.5 from 4.0)

# This project is based on  

 * [CorDapp Template - Java](https://github.com/corda/cordapp-template-java)
 * [Corda Invoice - POC](https://github.com/thegopalmishra/corda-invoice-poc)

# Pre-Requisites

1. See [Getting started with corda](https://docs.corda.net/getting-set-up.html)
2. You should have running corda-invoice-poc

# Getting Ready

* Move your invoice state, contract and workflows to below folders
  - Add your own state definitions under `contracts/src/main/java/com/template/states`
  - Add your own contract definitions under `contracts/src/main/java/com/template/contracts`
  - Add your own flow definitions under `workflows/src/main/java/com/template/`
  - Extend or replace the client and webserver under `clients/src/main/java/com/template/webserver` (optional)
* Update the package names for the above files.
* Update the value of ID `public static String ID = "com.template.contracts.InvoiceContract";` in the contract class which is fully qualified class name of the contract class itself
* Add your contracts and workflows inside the `cordapp { }` in `build.gradle` of respective modules

For contract
````
    contract {
        name "Invoice Contracts"
        vendor "R3"
        licence "A liberal, open source licence"
        versionId 1
    }
````
For workflows 
````
    workflow {
        name "Invoice Flows"
        vendor "R3"
        licence "A really expensive proprietary licence"
        versionId 1
    }
````
* Update the cordapp dependancy and platform version to 4.0 and 4 respectively in `constants.properties` (this will make sure that we build our app in 4.0)
* Below is the Corda release vs platform version chart

| Corda release | Platform version |
|:-------------:|:----------------:|
|      4.5      |         7        |
|      4.4      |         6        |
|      4.3      |         5        |
|      4.2      |         4        |
|      4.1      |         4        |
|      4.0      |         4        |
|      3.3      |         3        |
	    
* Now reload gradle changes from the gradle menu in Intellij or by `gradlew --referesh-dependancies`
* After dependancies are reolved build the project using `gradlew clean deployNodes` from the root directory
* Start the nodes by running `"build/nodes/runnodes.bat"`
* Perform transaction as mentioned in invoice poc or by using below command on PartyB node shell
`start Invoice owner: PartyA, payTermDescription: AA, currencyCode: INR, invoiceTransactionType: CASH, policyNumber: 12345,coverageCode: 1, coverageName: CN, policyEventType: BASE, installmentDueDate: 2020-06-12, invoiceNumber: 123, invoiceLineNumber: 1, financialTransactionCode: ABC, financialTransactionAmt: 500, apStatus: Pass, payToID: abc, payeeName: ABC, invoiceTransactionID: ABC123`
* Once the transaction is successful you can check it using the vault query by executing `run vaultQuery contractStateType: com.template.states.InvoiceState`
* Drain all the nodes by shutting them down gracefully by executing `run gracefulShutdown` on node shells
* Download the corda-4.5.jar from (MVN Repository)[]
* Replace the corda.jar in `build/nodes/*` for all the nodes (Where * is the node name).
* Start the nodes again by running `"build/nodes/runnodes.bat"`
* Undrain all the nodes by executing `run setFlowsDrainingModeEnabled enabled: false` on node shells
* Do vault query and check whether previouslt created transaction is present.
* Upgrade is successful if you can see the transaction without errors.


** Credits: Corda Bootcamp and Corda Documentation


