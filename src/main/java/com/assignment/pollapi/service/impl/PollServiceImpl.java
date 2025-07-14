package com.assignment.pollapi.service.impl;

import com.assignment.pollapi.dto.PollCreatedResponseDTO;
import com.assignment.pollapi.dto.PollRequestDTO;
import com.assignment.pollapi.dto.PollResponseDTO;
import com.assignment.pollapi.dto.VoteRequestDTO;
import com.assignment.pollapi.entity.Poll;
import com.assignment.pollapi.entity.PollOption;
import com.assignment.pollapi.entity.Vote;
import com.assignment.pollapi.repository.PollOptionRepository;
import com.assignment.pollapi.repository.PollRepository;
import com.assignment.pollapi.repository.VoteRepository;
import com.assignment.pollapi.service.PollService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class PollServiceImpl implements PollService {

    private final PollRepository pollRepository;
    private final PollOptionRepository optionRepository;
    private final VoteRepository voteRepository;
    private final ModelMapper dozerMapper;

    @Autowired
    public PollServiceImpl(PollRepository pollRepository,
                           PollOptionRepository optionRepository,
                           VoteRepository voteRepository, ModelMapper dozerMapper) {
        this.pollRepository = pollRepository;
        this.optionRepository = optionRepository;
        this.voteRepository = voteRepository;
        this.dozerMapper  = dozerMapper;
    }

    @Override
    public PollCreatedResponseDTO createPoll(PollRequestDTO dto) {
        Poll poll = new Poll();
        poll.setQuestion(dto.getQuestion());
        poll.setExpiryDateTime(dto.getExpiryDateTime());

        List<PollOption> options = dto.getOptions().stream()
                .map(optText -> {
                    PollOption option = new PollOption();
                    option.setOptionText(optText);
                    option.setPoll(poll);
                    return option;
                })
                .toList();

        poll.setOptions(options);
        Poll saved = pollRepository.save(poll);
        // Map to DTO
        return dozerMapper.map(saved, PollCreatedResponseDTO.class);
    }

    @Override
    public void vote(VoteRequestDTO dto) {
        if (voteRepository.existsByPollIdAndUserId(dto.getPollId(), dto.getUserId())) {
            throw new IllegalArgumentException("User has already voted.");
        }

        Poll poll = pollRepository.findById(dto.getPollId())
                .orElseThrow(() -> new NoSuchElementException("Poll not found."));

        if (poll.getExpiryDateTime().isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Poll has expired.");
        }

        PollOption option = optionRepository.findById(dto.getOptionId())
                .orElseThrow(() -> new NoSuchElementException("Option not found."));

        option.setVoteCount(option.getVoteCount() + 1);
        optionRepository.save(option);

        Vote vote = new Vote();
        vote.setPoll(poll);
        vote.setOption(option);
        vote.setUserId(dto.getUserId());
        voteRepository.save(vote);
    }

    @Override
    public PollResponseDTO getResults(Long pollId) {
        Poll poll = pollRepository.findById(pollId)
                .orElseThrow(() -> new NoSuchElementException("Poll not found."));

        Map<String, Integer> results = poll.getOptions().stream()
                .collect(Collectors.toMap(PollOption::getOptionText, PollOption::getVoteCount));

        PollResponseDTO dto = new PollResponseDTO();
        dto.setPollId(poll.getId());
        dto.setQuestion(poll.getQuestion());
        dto.setExpiryDateTime(poll.getExpiryDateTime());
        dto.setResults(results);
        return dto;
    }
}
