package com.geovannycode.infraestructure.response;

public record CandidateResponse(
        String name, String email, String gender, String expectedSalary, String typeOfContract) {}
