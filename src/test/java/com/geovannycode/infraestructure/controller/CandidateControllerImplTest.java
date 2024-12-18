package com.geovannycode.infraestructure.controller;

import com.geovannycode.application.service.CandidateService;
import com.geovannycode.infraestructure.Controller.impl.CandidateControllerImpl;
import com.geovannycode.infraestructure.request.CandidateRequest;
import com.geovannycode.infraestructure.response.CandidateResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CandidateControllerImplTest {
    @Mock
    private CandidateService candidateService;

    @InjectMocks
    private CandidateControllerImpl candidateController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCandidates() {
        CandidateResponse candidate1 = new CandidateResponse("Geovanny Mendoza", "geovannymendoza@example.com", "male","100", "Full-Time");
        CandidateResponse candidate2 = new CandidateResponse("Elena Aguirre", "elenaaguirre@example.com", "female","10000", "Part-Time");

        when(candidateService.getCandidates()).thenReturn(Arrays.asList(candidate1, candidate2));

        ResponseEntity<List<CandidateResponse>> response = candidateController.getCandidates();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        assertEquals("Geovanny Mendoza", response.getBody().get(0).name());

        verify(candidateService, times(1)).getCandidates();
    }

    @Test
    public void testGetCandidate() {

        CandidateResponse candidateResponse = new CandidateResponse("Geovanny Mendoza", "geovannymendoza@example.com", "Male", "10000", "Full-Time");
        when(candidateService.getCandidateById(1L)).thenReturn(candidateResponse);

        ResponseEntity<CandidateResponse> response = candidateController.getCandidateById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Geovanny Mendoza", response.getBody().name());
        verify(candidateService, times(1)).getCandidateById(1L);
    }

    @Test
    public void testCreateCandidate() {
        CandidateRequest candidateRequest = new CandidateRequest("Geovanny Mendoza", "geovannymendoza@example.com", "Male", "1000",  "Full-Time");
        CandidateResponse candidateResponse = new CandidateResponse("Geovanny Mendoza", "geovannymendoza@example.com", "Male","10000","Full-Time");

        when(candidateService.createCandidate(candidateRequest)).thenReturn(candidateResponse);

        ResponseEntity<CandidateResponse> response = candidateController.createCandidate(candidateRequest);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Geovanny Mendoza", response.getBody().name());
        verify(candidateService, times(1)).createCandidate(candidateRequest);
    }

    @Test
    public void testUpdateCandidate() {
        CandidateRequest candidateRequest = new CandidateRequest("Geovanny Mendoza", "geovannymendoza@example.com", "Male", "1000",  "Full-Time");
        CandidateResponse candidateResponse = new CandidateResponse("Geovanny Mendoza", "geovannymendoza@example.com", "Male","10000","Full-Time");
        when(candidateService.updateCandidate(candidateRequest, 1L)).thenReturn(candidateResponse);
        ResponseEntity<CandidateResponse> response = candidateController.updateCandidate(candidateRequest, 1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Geovanny Mendoza", response.getBody().name());

        verify(candidateService, times(1)).updateCandidate(candidateRequest, 1L);
    }

    @Test
    public void testDeleteCandidate() {

        ResponseEntity<Void> response = candidateController.deleteCandidate(1L);
        assertEquals(204, response.getStatusCodeValue());
        verify(candidateService, times(1)).deleteCandidate(1L);
    }
}
