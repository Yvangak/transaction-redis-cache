package com.gakyvan.redis.demo.service;

import com.gakyvan.redis.demo.domain.Address;
import com.gakyvan.redis.demo.domain.Bank;
import com.gakyvan.redis.demo.domain.Transaction;
import com.gakyvan.redis.demo.exception.TransactionNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);
    private List<Transaction> transactions;

    @CachePut(value = "transaction", key = "#code")
    public Transaction updateTransaction(String code, Transaction transaction) {
        logger.info("[Cache-Put] Posted transaction with code: {}", code);
        transaction.setTransactionCode(code);

        transactions = transactions.stream()
                .filter(trans -> !trans.getTransactionCode().equals(transaction.getTransactionCode()))
                .collect(Collectors.toList());

        transactions.add(transaction);

        return transaction;
    }

    @Cacheable(value = "transaction", key = "#code")
    public Transaction findTransaction(String code) throws TransactionNotFoundException {
        logger.info("[Cache-able :)] Get transaction for id: {}", code);
        try {
            logger.info("Sleeping 1 second ....");
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        return transactions.stream()
                .filter(transaction -> transaction.getTransactionCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new TransactionNotFoundException("Cannot find Transaction with id:" + code));
    }

    @CacheEvict(value = "transaction", key = "#code")
    public void removeTransaction(String code) {
        logger.info("[Cache-Evict] removeTransaction transaction deleted code: {}", code);
        transactions = transactions.stream()
                .filter(transaction -> !transaction.getTransactionCode().equals(code))
                .collect(Collectors.toList());
    }

    @PostConstruct
    public void init() {
        transactions = new ArrayList<>();
        Transaction transaction = Transaction.builder()
                .id(UUID.randomUUID().toString())
                .cardHolder("CRD-00-01-01")
                .address(Address.builder()
                        .city("LIVONIA")
                        .country("US")
                        .state("MICHIGAN")
                        .build())
                .amount(30.0)
                .balance(1000.0)
                .transactionCode("TRI-00-01-01")
                .bank(Bank.builder()
                        .bankCode("BNK-00-01-01")
                        .bankName("J.P. MORGAN CHASE")
                        .build())
                .build();
        transactions.add(transaction);
    }
}
