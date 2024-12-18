package com.geovannycode.application.service;

import com.geovannycode.infraestructure.request.CandidateRequest;
import com.geovannycode.infraestructure.response.CandidateResponse;
import java.util.List;

public interface CandidateService {
    List<CandidateResponse> getCandidates();

    CandidateResponse getCandidateById(Long id);

    CandidateResponse createCandidate(CandidateRequest candidateRequest);

    CandidateResponse updateCandidate(CandidateRequest candidateRequest, Long id);

    void deleteCandidate(Long id);
}
