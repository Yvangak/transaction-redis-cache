package com.gakyvan.redis.demo.controller;

import com.gakyvan.redis.demo.domain.Transaction;
import com.gakyvan.redis.demo.exception.TransactionNotFoundException;
import com.gakyvan.redis.demo.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/redis-demo/v1/transactions")
public class TransactionController {
    private TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/{code}")
    public ResponseEntity<Transaction> saveTransaction(@PathVariable String code, @RequestBody Transaction transaction) {
        transaction.setTransactionCode(code);
        transactionService.updateTransaction(transaction);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @GetMapping("/{code}/transaction")
    public ResponseEntity<Transaction> getTransactionByCode(@PathVariable String code) throws TransactionNotFoundException {
        Transaction transaction = transactionService.findTransaction(code);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }
}
