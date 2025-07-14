package com.assignment.pollapi.repository;

import com.assignment.pollapi.entity.PollOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PollOptionRepository extends JpaRepository<PollOption, Long> {}

