package com.gakyvan.redis.demo.service.implementation;

import com.gakyvan.redis.demo.domain.Transaction;
import com.gakyvan.redis.demo.service.ITransactionService;
import org.springframework.stereotype.Service;

@Service
public class TransactionService implements ITransactionService {

    @Override
    public void save(Transaction transaction) {

    }

    @Override
    public Transaction find(String code) {
        return null;
    }
}
