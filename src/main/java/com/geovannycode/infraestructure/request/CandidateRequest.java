package com.geovannycode.infraestructure.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CandidateRequest(
        @NotBlank(message = "name is required") @Size(max = 100, message = "name must be less than 100 characters")
                String name,
        @NotBlank(message = "email is required")
                @Email(message = "email should be valid")
                @Size(max = 250, message = "email must be less than 250 characters")
                String email,
        @Size(max = 10, message = "Gender must be less than or equal to 10 characters") String gender,
        @NotNull(message = "Expected salary is mandatory") @DecimalMin(value = "0.0", inclusive = false, message = "Expected salary must be greater than zero")
                @Digits(integer = 10, fraction = 2, message = "Expected salary must be a valid monetary amount")
                String expectedSalary,
        @Size(max = 50, message = "Type of contract must be less than or equal to 50 characters")
                String typeOfContract) {}
