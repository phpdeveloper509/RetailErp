package com.retail.erp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuotationItemDTO {
    public QuotationItemDTO() {
		// TODO Auto-generated constructor stub
	}
	private String productName;
    private int quantity;
    private double rate;

    // Getters and setters
}
