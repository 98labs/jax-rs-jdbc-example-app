package com.example.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    private BigInteger id;
    private BigInteger customerId;
    private LocalDateTime orderDate;
    private BigDecimal amount;

    @Builder.Default
    private List<Product> products = new ArrayList<>();


}
