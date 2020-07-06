package com.gakyvan.redis.demo.controller.exception;

import com.gakyvan.redis.demo.exception.TransactionNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice
@RestController
public class TransactionExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(TransactionExceptionHandler.class);

    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<TransactionErrorResponse> handleTransactionNotFoundException(HttpServletRequest request, Exception exception){
        logger.error("HELLO {} : {}", exception.getMessage(), request.getRequestURI());
        TransactionErrorResponse transactionErrorResponse = TransactionErrorResponse.builder()
                .timeStamp(new Date().getTime())
                .statusCode(4000)
                .statusDescription("NOT FOUND")
                .errorMessage("Transaction not found exception")
                .build();

        return new ResponseEntity<>(transactionErrorResponse, HttpStatus.NOT_FOUND);
    }
}
