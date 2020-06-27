package com.gakyvan.redis.demo.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Address implements Serializable {
    private String postalCode;
    private String city;
    private String state;
    private String country;
}
