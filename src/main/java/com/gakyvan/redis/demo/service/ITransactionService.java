package com.gakyvan.redis.demo.service;

import com.gakyvan.redis.demo.domain.Transaction;

public interface ITransactionService {

    void save(Transaction transaction);

    Transaction find(String code);
}
