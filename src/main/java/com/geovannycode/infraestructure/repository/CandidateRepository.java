package com.geovannycode.infraestructure.repository;

import com.geovannycode.domain.model.Candidate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    List<Candidate> findByActiveTrue();

    Optional<Candidate> findByIdAndActiveTrue(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Candidate p SET p.active = false WHERE p.id = :id")
    void deactivateCandidate(Long id);
}
