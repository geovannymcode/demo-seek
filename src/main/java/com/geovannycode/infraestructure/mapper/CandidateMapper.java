package com.geovannycode.infraestructure.mapper;

import com.geovannycode.domain.model.Candidate;
import com.geovannycode.infraestructure.request.CandidateRequest;
import com.geovannycode.infraestructure.response.CandidateResponse;
import java.math.BigDecimal;

public class CandidateMapper {

    public static Candidate toCandidate(CandidateRequest candidateRequest) {
        return new Candidate(
                candidateRequest.name(),
                candidateRequest.email(),
                candidateRequest.gender(),
                new BigDecimal(candidateRequest.expectedSalary()),
                candidateRequest.typeOfContract());
    }

    public static CandidateResponse toResponse(Candidate candidate) {
        return new CandidateResponse(
                candidate.getName(),
                candidate.getEmail(),
                candidate.getGender(),
                candidate.getExpectedSalary().toString(),
                candidate.getTypeOfContract());
    }
}
