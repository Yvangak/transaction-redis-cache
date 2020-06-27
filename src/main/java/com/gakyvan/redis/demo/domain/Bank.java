package com.gakyvan.redis.demo.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Bank implements Serializable {
    private String bankCode;
    private String bankName;
}
