package com.cashfree.sdk.batchtransfer;

import com.cashfree.lib.constants.Constants.Environment;
import com.cashfree.lib.logger.VerySimpleFormatter;
import com.cashfree.lib.payout.clients.Payouts;
import com.cashfree.lib.payout.clients.Transfers;
import com.cashfree.lib.payout.domains.request.BatchTransferRequest;
import com.cashfree.lib.payout.domains.response.BatchTransferResponse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

public class batchTransfer {
    private static final Logger log = Logger.getLogger(batchTransfer.class.getName());
    static {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new VerySimpleFormatter());
        log.addHandler(consoleHandler);
    }

    public static void main(String[] args) {
        Payouts payouts = Payouts.getInstance(
                Environment.PRODUCTION, "", "");
        log.info("" + payouts.init());

        boolean isTokenValid = payouts.verifyToken();
        log.info("" + isTokenValid);
        if (!isTokenValid) return;

        log.info("Token is valid");

        Transfers transfers=new Transfers(payouts);

        String batchTransferId = "javasdktestbatchtransferid" + ThreadLocalRandom.current().nextInt(0, 1000000);
        List<BatchTransferRequest.Payload> batchTransferRequests = new ArrayList<>();
        batchTransferRequests.add(
                new BatchTransferRequest.Payload()
                        .setTransferId("PTM_00121241112")
                        .setAmount(new BigDecimal("1.00"))
                        .setPhone("9999999999")
                        .setBankAccount("9999999999")
                        .setIfsc("PYTM0_000001")
                        .setEmail("bahrat@gocashfree.com")
                        .setName("bharat"));
        batchTransferRequests.add(
                new BatchTransferRequest.Payload()
                        .setTransferId("PTM_00052312126")
                        .setAmount(new BigDecimal("1.00"))
                        .setPhone("9999999999")
                        .setBankAccount("9999999999")
                        .setIfsc("PYTM0000001")
                        .setEmail("bharat@gocashfree.com")
                        .setName("bharat"));
        batchTransferRequests.add(
                new BatchTransferRequest.Payload()
                        .setTransferId("PTM_0001321215")
                        .setAmount(new BigDecimal("1.00"))
                        .setPhone("9999999999")
                        .setBankAccount("9999999999")
                        .setIfsc("PYTM0000001")
                        .setEmail("bahrat@gocashfree.com")
                        .setName("bharat"));


        BatchTransferRequest batchTransferRequest = new BatchTransferRequest()
                .setBatchTransferId(batchTransferId)
                .setBatchFormat("BANK_ACCOUNT")
                .setDeleteBene(1)
                .setBatch(batchTransferRequests);





        BatchTransferResponse batchTransferResponse = transfers.requestBatchTransfer(batchTransferRequest);
        log.info("" + batchTransferResponse);
        log.info("Getting Status for Batch Transfer");
        log.info("" + transfers.getBatchTransferStatus(batchTransferId));
    }


}
