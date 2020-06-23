package com.gakyvan.redis.demo.repository;

import com.gakyvan.redis.demo.domain.Transaction;

public interface ITransactionRepository {

    void save(Transaction transaction);

    Transaction find(String code);
}
