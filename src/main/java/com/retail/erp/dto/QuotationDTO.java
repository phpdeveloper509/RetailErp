package com.retail.erp.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuotationDTO {
	 private String quotationNumber;
	    private LocalDate quotationDate;
	    private String status;
	    private String remarks;
	    private Long supplierId;
	    private List<QuotationItemDTO> items;

	    @Data
	    public static class QuotationItemDTO {
	        private Long itemId;
	        private int quantity;
	        private double rate;
	        private double tax;
	    }
}
