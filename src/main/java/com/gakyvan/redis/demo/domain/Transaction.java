package com.gakyvan.redis.demo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gakyvan.redis.demo.domain.enums.Currency;
import com.gakyvan.redis.demo.domain.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction implements Serializable {
    private String id;
    private String transactionCode;
    private Bank bank;
    private TransactionType transactionType;
    private Double amount;
    private Double balance;
    private Currency currency;
    private String cardNumber;
    private String cardHolder;
    private Address address;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @CreatedDate
    private Date created;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @LastModifiedDate
    private Date lastModified;
}