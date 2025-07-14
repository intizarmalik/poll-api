package com.assignment.pollapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class VoteRequestDTO {
    @NotNull(message = "Poll ID is required")
    @Schema(description = "ID of the poll", example = "1")
    private Long pollId;

    @NotNull(message = "Option ID is required")
    @Schema(description = "Option to vote for", example = "2")
    private Long optionId;

    @NotBlank(message = "User ID is required")
    @Schema(description = "User identifier", example = "user123")
    private String userId;

    public Long getPollId() {
        return pollId;
    }

    public void setPollId(Long pollId) {
        this.pollId = pollId;
    }

    public Long getOptionId() {
        return optionId;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

