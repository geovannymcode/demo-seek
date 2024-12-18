package com.geovannycode.infraestructure.Controller;

import com.geovannycode.infraestructure.request.CandidateRequest;
import com.geovannycode.infraestructure.response.CandidateResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface CandidateController {

    @Operation(summary = "Get all candidates")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Candidates retrieved successfully",
                        content = @Content(schema = @Schema(implementation = CandidateResponse[].class))),
                @ApiResponse(
                        responseCode = "500",
                        description = "Internal Server Error",
                        content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
            })
    public ResponseEntity<List<CandidateResponse>> getCandidates();

    @Operation(summary = "Get a candidate by ID")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Candidate retrieved successfully",
                        content = @Content(schema = @Schema(implementation = CandidateResponse.class))),
                @ApiResponse(
                        responseCode = "404",
                        description = "Candidate not found",
                        content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
                @ApiResponse(
                        responseCode = "500",
                        description = "Internal Server Error",
                        content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
            })
    public ResponseEntity<CandidateResponse> getCandidateById(@PathVariable("id") Long id);

    @Operation(summary = "Create a new candidate")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Candidate created successfully",
                        content = @Content(schema = @Schema(implementation = CandidateResponse.class))),
                @ApiResponse(
                        responseCode = "400",
                        description = "Bad Request",
                        content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
                @ApiResponse(
                        responseCode = "404",
                        description = "Candidate not found",
                        content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
                @ApiResponse(
                        responseCode = "500",
                        description = "Internal Server Error",
                        content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
            })
    public ResponseEntity<CandidateResponse> createCandidate(@RequestBody CandidateRequest candidateRequest);

    @Operation(summary = "Update a candidate by ID")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Candidate updated successfully",
                        content = @Content(schema = @Schema(implementation = CandidateResponse.class))),
                @ApiResponse(
                        responseCode = "400",
                        description = "Bad Request",
                        content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
                @ApiResponse(
                        responseCode = "404",
                        description = "Candidate not found",
                        content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
                @ApiResponse(
                        responseCode = "500",
                        description = "Internal Server Error",
                        content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
            })
    public ResponseEntity<CandidateResponse> updateCandidate(@RequestBody CandidateRequest candidateRequest, Long id);

    @Operation(summary = "Delete a candidate by ID")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Candidate deleted successfully",
                        content = @Content(schema = @Schema(implementation = CandidateResponse.class))),
                @ApiResponse(
                        responseCode = "404",
                        description = "Candidate not found",
                        content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
                @ApiResponse(
                        responseCode = "500",
                        description = "Internal Server Error",
                        content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
            })
    public ResponseEntity<Void> deleteCandidate(@PathVariable("id") Long id);
}
