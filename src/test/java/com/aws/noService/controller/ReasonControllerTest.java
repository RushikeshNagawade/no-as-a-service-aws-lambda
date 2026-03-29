package com.aws.noService.controller;

import com.aws.noService.service.ReasonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReasonController.class)
@DisplayName("ReasonController Tests")
class ReasonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReasonService reasonService;

    @BeforeEach
    void setUp() {
        reset(reasonService);
    }

    @Test
    @DisplayName("GET / should return root endpoint message")
    void testPingEndpoint() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("Do you meant to say no?"));
    }

    @Test
    @DisplayName("GET /no should return a random reason")
    void testGetRandomReasonEndpoint() throws Exception {
        String expectedReason = "In a different season of life, I might say yes—but not right now.";
        when(reasonService.getRandomReason()).thenReturn(expectedReason);

        mockMvc.perform(get("/no"))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedReason));

        verify(reasonService, times(1)).getRandomReason();
    }

    @Test
    @DisplayName("GET /no should call service multiple times independently")
    void testGetRandomReasonMultipleCalls() throws Exception {
        String reason1 = "Reason 1";
        String reason2 = "Reason 2";

        when(reasonService.getRandomReason())
                .thenReturn(reason1)
                .thenReturn(reason2);

        mockMvc.perform(get("/no"))
                .andExpect(status().isOk())
                .andExpect(content().string(reason1));

        mockMvc.perform(get("/no"))
                .andExpect(status().isOk())
                .andExpect(content().string(reason2));

        verify(reasonService, times(2)).getRandomReason();
    }

    @Test
    @DisplayName("GET /no/count should return total number of reasons")
    void testGetTotalReasonsEndpoint() throws Exception {
        when(reasonService.getTotalReasons()).thenReturn(50);

        mockMvc.perform(get("/no/count"))
                .andExpect(status().isOk())
                .andExpect(content().json("50"));

        verify(reasonService, times(1)).getTotalReasons();
    }

    @Test
    @DisplayName("GET /no/count should return correct count")
    void testGetTotalReasonsEndpointWithDifferentCounts() throws Exception {
        when(reasonService.getTotalReasons()).thenReturn(100);

        mockMvc.perform(get("/no/count"))
                .andExpect(status().isOk())
                .andExpect(content().json("100"));
    }

    @Test
    @DisplayName("GET / endpoint should be accessible via GET method")
    void testPingEndpointMethodType() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /no endpoint should be accessible via GET method")
    void testGetRandomReasonEndpointMethodType() throws Exception {
        when(reasonService.getRandomReason()).thenReturn("Test reason");

        mockMvc.perform(get("/no"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /no/count endpoint should be accessible via GET method")
    void testGetTotalReasonsEndpointMethodType() throws Exception {
        when(reasonService.getTotalReasons()).thenReturn(42);

        mockMvc.perform(get("/no/count"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Service dependency should be properly injected")
    void testServiceInjection() throws Exception {
        when(reasonService.getRandomReason()).thenReturn("Injected reason");

        mockMvc.perform(get("/no"))
                .andExpect(status().isOk())
                .andExpect(content().string("Injected reason"));

        verify(reasonService).getRandomReason();
    }
}

