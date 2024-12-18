package com.geovannycode.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "candidates")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "email", length = 250, nullable = false)
    private String email;

    @Column(name = "gender", length = 10)
    private String gender;

    @Column(name = "expected_salary", precision = 10, scale = 2)
    private BigDecimal expectedSalary;

    @Column(name = "created_at", updatable = false)
    private LocalDate createdAt;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @Column(name = "type_of_contract", length = 50)
    private String typeOfContract;

    public Candidate() {}

    public Candidate(String name, String email, String gender, BigDecimal expectedSalary, String typeOfContract) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.expectedSalary = expectedSalary;
        this.typeOfContract = typeOfContract;
    }

    public Candidate(
            Long id,
            String name,
            String email,
            String gender,
            BigDecimal expectedSalary,
            LocalDate createdAt,
            Boolean active,
            LocalDate updatedAt,
            String typeOfContract) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.expectedSalary = expectedSalary;
        this.createdAt = createdAt;
        this.active = active;
        this.updatedAt = updatedAt;
        this.typeOfContract = typeOfContract;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public BigDecimal getExpectedSalary() {
        return expectedSalary;
    }

    public void setExpectedSalary(BigDecimal expectedSalary) {
        this.expectedSalary = expectedSalary;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getTypeOfContract() {
        return typeOfContract;
    }

    public void setTypeOfContract(String typeOfContract) {
        this.typeOfContract = typeOfContract;
    }
}
