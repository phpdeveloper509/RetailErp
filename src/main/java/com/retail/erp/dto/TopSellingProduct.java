package com.retail.erp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TopSellingProduct {
    private String productName;
    private int quantitySold;
}

