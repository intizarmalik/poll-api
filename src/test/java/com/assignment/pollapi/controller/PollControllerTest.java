package com.assignment.pollapi.controller;

import com.assignment.pollapi.dto.*;
import com.assignment.pollapi.service.PollService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(PollController.class)
public class PollControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PollService pollService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreatePoll() throws Exception {
        PollRequestDTO request = new PollRequestDTO();
        request.setQuestion("Your favorite language?");
        request.setOptions(List.of("Java", "Python"));
        request.setExpiryDateTime(LocalDateTime.of(2025, 7, 20, 23, 59));

        PollCreatedResponseDTO.OptionDTO option1 = new PollCreatedResponseDTO.OptionDTO(1L, "Java");
        PollCreatedResponseDTO.OptionDTO option2 = new PollCreatedResponseDTO.OptionDTO(2L, "Python");

        PollCreatedResponseDTO response = new PollCreatedResponseDTO(
                100L,
                "Your favorite language?",
                request.getExpiryDateTime(),
                List.of(option1, option2)
        );

        Mockito.when(pollService.createPoll(Mockito.any())).thenReturn(response);

        mockMvc.perform(post("/api/polls")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value(201))
                .andExpect(jsonPath("$.message").value("Poll created successfully"))
                .andExpect(jsonPath("$.data.id").value(100))  // ✅ access nested "data"
                .andExpect(jsonPath("$.data.options", hasSize(2)))
                .andExpect(jsonPath("$.data.options[0].optionText").value("Java"));
    }

    @Test
    public void testVote() throws Exception {
        VoteRequestDTO vote = new VoteRequestDTO();
        vote.setPollId(100L);
        vote.setOptionId(1L);
        vote.setUserId("user123");

        mockMvc.perform(post("/api/polls/vote")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vote)))
                .andDo(result -> System.out.println(">>> Response: " + result.getResponse().getContentAsString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("Vote recorded"))
                .andExpect(jsonPath("$.data").value("Success"));  // ✅ this line is key
    }

    @Test
    public void testGetResults() throws Exception {
        PollResponseDTO response = new PollResponseDTO();
        response.setPollId(100L);
        response.setQuestion("Your favorite language?");
        response.setExpiryDateTime(LocalDateTime.of(2025, 7, 20, 23, 59));
        response.setResults(Map.of("Java", 5, "Python", 3));

        Mockito.when(pollService.getResults(100L)).thenReturn(response);

        mockMvc.perform(get("/api/polls/100/results"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("Poll results fetched"))
                .andExpect(jsonPath("$.data.pollId").value(100))                // ✅ data wrapper
                .andExpect(jsonPath("$.data.results.Java").value(5))           // ✅ nested map
                .andExpect(jsonPath("$.data.results.Python").value(3));
    }
}
