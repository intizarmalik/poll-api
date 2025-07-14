package com.assignment.pollapi.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PollCreatedResponseDTO {
    private Long id;
    private String question;
    private LocalDateTime expiryDateTime;
    private List<OptionDTO> options;
    public PollCreatedResponseDTO() {}
    public PollCreatedResponseDTO(Long id, String question, LocalDateTime expiryDateTime, List<OptionDTO> options) {
        this.id = id;
        this.question = question;
        this.expiryDateTime = expiryDateTime;
        this.options = options;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public LocalDateTime getExpiryDateTime() {
        return expiryDateTime;
    }

    public void setExpiryDateTime(LocalDateTime expiryDateTime) {
        this.expiryDateTime = expiryDateTime;
    }

    public List<OptionDTO> getOptions() {
        return options;
    }

    public void setOptions(List<OptionDTO> options) {
        this.options = options;
    }

    public static class OptionDTO {
        private Long id;
        private String optionText;
        public OptionDTO(){}
        public OptionDTO(Long id, String optionText) {
            this.id = id;
            this.optionText = optionText;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getOptionText() {
            return optionText;
        }

        public void setOptionText(String optionText) {
            this.optionText = optionText;
        }
    }
}
