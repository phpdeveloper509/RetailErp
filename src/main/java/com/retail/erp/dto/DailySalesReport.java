package com.retail.erp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DailySalesReport {
    private String date;
    private double totalSales;
}
