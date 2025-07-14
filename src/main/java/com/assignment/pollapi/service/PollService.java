package com.assignment.pollapi.service;

import com.assignment.pollapi.dto.PollCreatedResponseDTO;
import com.assignment.pollapi.dto.PollRequestDTO;
import com.assignment.pollapi.dto.PollResponseDTO;
import com.assignment.pollapi.dto.VoteRequestDTO;
import com.assignment.pollapi.entity.Poll;

public interface PollService {
    PollCreatedResponseDTO createPoll(PollRequestDTO dto);
    void vote(VoteRequestDTO dto);
    PollResponseDTO getResults(Long pollId);
}
