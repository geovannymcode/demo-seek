package com.geovannycode.infraestructure.exception;

public class CandidateNotFoundException extends RuntimeException {
    public CandidateNotFoundException(String message) {
        super(message);
    }

    public static CandidateNotFoundException forID(Long id) {
        return new CandidateNotFoundException("Candidate with Id " + id + " not found");
    }
}
