package com.gakyvan.redis.demo.controller;

import com.gakyvan.redis.demo.domain.Transaction;
import com.gakyvan.redis.demo.exception.TransactionNotFoundException;
import com.gakyvan.redis.demo.service.TransactionService;
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
        Transaction updatedTransaction = transactionService.updateTransaction(code,transaction);
        return new ResponseEntity<>(updatedTransaction, HttpStatus.OK);
    }

    @GetMapping("/{code}")
    public ResponseEntity<Transaction> getTransactionByCode(@PathVariable String code)
            throws TransactionNotFoundException {
        Transaction transaction = transactionService.findTransaction(code);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<HttpStatus> removeTransactionByCode(@PathVariable String code) {
        transactionService.removeTransaction(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
