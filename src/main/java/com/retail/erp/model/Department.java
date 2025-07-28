package com.retail.erp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Department name is required")
    private String name;

    private String description;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Telephone is required")
    @Pattern(regexp = "^[0-9]{10,15}$", message = "Invalid telephone number")
    private String telephone;

    @Pattern(regexp = "^[0-9]{6,15}$", message = "Invalid fax number")
    private String fax;

    @NotBlank(message = "Department Head is required")
    private String deptHead;

    // Getters and Setters
}
