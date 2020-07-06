package com.gakyvan.redis.demo.controller.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TransactionErrorResponse {
    private long timeStamp;
    private int statusCode;
    private String statusDescription;
    private String errorMessage;
}
