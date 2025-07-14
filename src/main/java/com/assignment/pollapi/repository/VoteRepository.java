package com.assignment.pollapi.repository;

import com.assignment.pollapi.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    boolean existsByPollIdAndUserId(Long pollId, String userId);
}
