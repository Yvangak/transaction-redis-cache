package com.gakyvan.redis.demo.service.implementation;

import com.gakyvan.redis.demo.domain.Transaction;
import com.gakyvan.redis.demo.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class TransactionService implements ITransactionService {

    private static final String REGISTRY_NAME = "transactions";
    private HashOperations<String, String, Transaction> hashOperations;
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public TransactionService(RedisTemplate<String, Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    public void initializeHashOperations(){
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(Transaction transaction) {
        hashOperations.put(REGISTRY_NAME, transaction.getId(), transaction);
    }

    @Override
    public Transaction find(String code) {
        return hashOperations.get(REGISTRY_NAME, code);
    }
}
