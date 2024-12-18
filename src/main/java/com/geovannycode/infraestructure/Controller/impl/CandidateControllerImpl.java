package com.geovannycode.infraestructure.Controller.impl;

import com.geovannycode.application.service.CandidateService;
import com.geovannycode.infraestructure.Controller.CandidateController;
import com.geovannycode.infraestructure.request.CandidateRequest;
import com.geovannycode.infraestructure.response.CandidateResponse;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/candidate")
public class CandidateControllerImpl implements CandidateController {

    private final CandidateService candidateService;

    public CandidateControllerImpl(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @Override
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<CandidateResponse>> getCandidates() {
        List<CandidateResponse> candidates = candidateService.getCandidates();
        return ResponseEntity.ok(candidates);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<CandidateResponse> getCandidateById(@PathVariable Long id) {
        CandidateResponse candidate = candidateService.getCandidateById(id);
        return ResponseEntity.ok(candidate);
    }

    @Override
    @PostMapping
    public ResponseEntity<CandidateResponse> createCandidate(@RequestBody CandidateRequest candidateRequest) {
        CandidateResponse candidate = candidateService.createCandidate(candidateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(candidate);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<CandidateResponse> updateCandidate(
            @RequestBody CandidateRequest candidateRequest, @PathVariable Long id) {
        CandidateResponse candidate = candidateService.updateCandidate(candidateRequest, id);
        return ResponseEntity.ok(candidate);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidate(@PathVariable Long id) {
        candidateService.deleteCandidate(id);
        return ResponseEntity.noContent().build();
    }
}
