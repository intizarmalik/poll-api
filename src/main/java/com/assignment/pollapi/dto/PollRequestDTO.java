package com.assignment.pollapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public class PollRequestDTO {
    @NotBlank(message = "Question must not be blank")
    @Schema(description = "Poll question", example = "What's your favorite language?")
    private String question;

    @NotEmpty(message = "Poll must have at least one option")
    @Schema(description = "Options list", example = "[\"Java\", \"Python\"]")
    @Valid
    private List<@NotBlank(message = "Each option must be non-empty") String> options;

    @NotNull(message = "Expiry date/time must be provided")
    @Schema(description = "Poll expiry", example = "2025-07-20T23:59:59")
    private LocalDateTime expiryDateTime;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public LocalDateTime getExpiryDateTime() {
        return expiryDateTime;
    }

    public void setExpiryDateTime(LocalDateTime expiryDateTime) {
        this.expiryDateTime = expiryDateTime;
    }
}
