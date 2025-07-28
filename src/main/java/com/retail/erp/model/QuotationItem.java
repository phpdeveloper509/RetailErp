package com.retail.erp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class QuotationItem {

	 @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private int quantity;
	    private double rate;
	    private double tax;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JsonBackReference
	    private Quotation quotation;

	    @ManyToOne(fetch = FetchType.LAZY)
	    private Item item;
	    // getters & setters
}
