package com.retail.erp.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
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
public class Item {
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @NotBlank
	    @Column(unique = true)
	    private String itemCode;

	    @NotBlank
	    private String itemName;

	    @ManyToOne
	    @JoinColumn(name = "category_id", nullable = false)
	    private ItemCategory itemCategoryId;

	    @ManyToOne
	    @JoinColumn(name = "unit_id", nullable = false)
	    private UnitOfMeasurement unit;

	    private String description;
}
