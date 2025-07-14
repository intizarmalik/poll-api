package com.assignment.pollapi.controller;

import com.assignment.pollapi.dto.*;
import com.assignment.pollapi.service.PollService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/polls")
@Tag(name = "Poll API", description = "Operations related to poll management")
public class PollController {

    private final PollService pollService;

    // Use constructor-based injection
    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    /**
     * Create a new poll
     */
    @Operation(summary = "Create a new poll", description = "Creates a poll with question, options and expiry time.")
    @PostMapping
    public ResponseEntity<ApiResponse<PollCreatedResponseDTO>> createPoll(@RequestBody  @Valid  PollRequestDTO dto) {
        PollCreatedResponseDTO created = pollService.createPoll(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(201, "Poll created successfully", created));
    }

    /**
     * Cast a vote
     */
    @Operation(summary = "Vote in a poll", description = "Cast your vote for a specific option in a poll.")
    @PostMapping("/vote")
    public ResponseEntity<ApiResponse<String>> vote(@RequestBody @Valid VoteRequestDTO dto) {
        pollService.vote(dto);
        return ResponseEntity.ok(new ApiResponse<>(200, "Vote recorded", "Success"));
    }

    /**
     * Get poll results
     */
    @Operation(summary = "Get poll results", description = "Returns total votes per option for a given poll.")
    @GetMapping("/{pollId}/results")
    public ResponseEntity<ApiResponse<PollResponseDTO>> getResults(@PathVariable Long pollId) {
        PollResponseDTO result = pollService.getResults(pollId);
        return ResponseEntity.ok(new ApiResponse<>(200, "Poll results fetched", result));
    }
}
