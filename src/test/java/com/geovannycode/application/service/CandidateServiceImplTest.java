package com.geovannycode.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.geovannycode.application.service.impl.CandidateServiceImpl;
import com.geovannycode.domain.model.Candidate;
import com.geovannycode.infraestructure.repository.CandidateRepository;
import com.geovannycode.infraestructure.request.CandidateRequest;
import com.geovannycode.infraestructure.response.CandidateResponse;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CandidateServiceImplTest {

    @Mock
    private CandidateRepository candidateRepository;

    @InjectMocks
    private CandidateServiceImpl candidateService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCandidates() {
        Candidate candidate = new Candidate();
        candidate.setName("Geovanny Mendoza");
        candidate.setEmail("geovanny.mendoza@example.com");
        candidate.setGender("Male");
        candidate.setExpectedSalary(new BigDecimal("50000"));
        candidate.setTypeOfContract("Full-time");
        when(candidateRepository.findAll()).thenReturn(Arrays.asList(candidate));

        List<CandidateResponse> candidates = candidateService.getCandidates();

        assertNotNull(candidates);
        assertEquals(1, candidates.size());
        verify(candidateRepository, times(1)).findAll();
    }

    @Test
    void testCreateCandidate() {
        CandidateRequest request =
                new CandidateRequest("Geovanny Mendoza", "geovanny.mendoza@example.com", "Male", "50000", "Full-time");

        Candidate candidate = new Candidate();
        candidate.setName("Geovanny Mendoza");
        candidate.setEmail("geovanny.mendoza@example.com");
        candidate.setGender("Male");
        candidate.setExpectedSalary(new BigDecimal("50000"));
        candidate.setTypeOfContract("Full-time");

        when(candidateRepository.save(any(Candidate.class))).thenReturn(candidate);

        CandidateResponse response = candidateService.createCandidate(request);

        assertNotNull(response);
        verify(candidateRepository, times(1)).save(any(Candidate.class));
    }

    @Test
    void testUpdateCandidate() {
        Long id = 1L;
        CandidateRequest request =
                new CandidateRequest("Geovanny Mendoza", "geovanny.mendoza@example.com", "Male", "50000", "Full-time");

        Candidate candidate = new Candidate();
        when(candidateRepository.findById(id)).thenReturn(Optional.of(candidate));
        when(candidateRepository.save(any(Candidate.class))).thenReturn(candidate);

        CandidateResponse response = candidateService.updateCandidate(request, id);

        assertNotNull(response);
        assertEquals("Geovanny Mendoza", candidate.getName());
        verify(candidateRepository, times(1)).findById(id);
        verify(candidateRepository, times(1)).save(candidate);
    }

    @Test
    void testDeleteCandidate() {
        Long id = 1L;

        doNothing().when(candidateRepository).deactivateCandidate(id);

        candidateService.deleteCandidate(id);

        verify(candidateRepository, times(1)).deactivateCandidate(id);
    }

    @Test
    void testGetCandidate() {
        Long id = 1L;
        Candidate candidate = new Candidate();
        candidate.setName("Geovanny Mendoza");
        candidate.setEmail("geovanny.mendoza@example.com");
        candidate.setGender("Male");
        candidate.setExpectedSalary(new BigDecimal("50000"));
        candidate.setTypeOfContract("Full-time");
        when(candidateRepository.findById(id)).thenReturn(Optional.of(candidate));

        CandidateResponse response = candidateService.getCandidateById(id);

        assertNotNull(response);
        verify(candidateRepository, times(1)).findById(id);
    }
}
