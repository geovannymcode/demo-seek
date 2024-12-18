package com.geovannycode.application.service.impl;

import com.geovannycode.application.service.CandidateService;
import com.geovannycode.domain.model.Candidate;
import com.geovannycode.infraestructure.exception.CandidateNotFoundException;
import com.geovannycode.infraestructure.mapper.CandidateMapper;
import com.geovannycode.infraestructure.repository.CandidateRepository;
import com.geovannycode.infraestructure.request.CandidateRequest;
import com.geovannycode.infraestructure.response.CandidateResponse;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;

    public CandidateServiceImpl(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Override
    public List<CandidateResponse> getCandidates() {
        return candidateRepository.findAll().stream()
                .map(CandidateMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CandidateResponse getCandidateById(Long id) {
        Candidate candidate =
                this.candidateRepository.findById(id).orElseThrow(() -> CandidateNotFoundException.forID(id));
        return CandidateMapper.toResponse(candidate);
    }

    @Override
    public CandidateResponse createCandidate(CandidateRequest candidateRequest) {
        return CandidateMapper.toResponse(candidateRepository.save(CandidateMapper.toCandidate(candidateRequest)));
    }

    @Override
    public CandidateResponse updateCandidate(CandidateRequest candidateRequest, Long id) {
        Candidate candidate = candidateRepository.findById(id).orElseThrow(() -> CandidateNotFoundException.forID(id));
        candidate.setName(candidateRequest.name());
        candidate.setEmail(candidateRequest.email());
        candidate.setGender(candidateRequest.gender());
        candidate.setExpectedSalary(new BigDecimal(candidateRequest.expectedSalary()));
        candidate.setTypeOfContract(candidateRequest.typeOfContract());
        candidate.setUpdatedAt(LocalDate.now());
        return CandidateMapper.toResponse(candidateRepository.save(candidate));
    }

    @Override
    public void deleteCandidate(Long id) {
        candidateRepository.deactivateCandidate(id);
    }
}
