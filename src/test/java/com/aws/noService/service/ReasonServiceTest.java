package com.aws.noService.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ReasonService Tests")
class ReasonServiceTest {

    private ReasonService reasonService;

    @BeforeEach
    void setUp() {
        reasonService = new ReasonService();
    }

    @Test
    @DisplayName("Should initialize reasons list successfully")
    void testInitialization() throws Exception {
        reasonService.init();
        assertNotNull(reasonService.getTotalReasons());
        assertTrue(reasonService.getTotalReasons() > 0);
    }

    @Test
    @DisplayName("Should return a random reason from the list")
    void testGetRandomReason() throws Exception {
        reasonService.init();
        String reason = reasonService.getRandomReason();

        assertNotNull(reason);
        assertFalse(reason.isEmpty());
        assertTrue(reason.length() > 0);
    }

    @Test
    @DisplayName("Should return different reasons on multiple calls")
    void testGetRandomReasonVariety() throws Exception {
        reasonService.init();

        String reason1 = reasonService.getRandomReason();
        String reason2 = reasonService.getRandomReason();
        String reason3 = reasonService.getRandomReason();

        assertNotNull(reason1);
        assertNotNull(reason2);
        assertNotNull(reason3);

        // At least one should be different (with high probability)
        boolean hasDifference = !reason1.equals(reason2) || !reason2.equals(reason3);
        assertTrue(hasDifference);
    }

    @Test
    @DisplayName("Should return correct total reasons count")
    void testGetTotalReasons() throws Exception {
        reasonService.init();
        int totalReasons = reasonService.getTotalReasons();

        assertTrue(totalReasons > 0);
        assertEquals(totalReasons, reasonService.getTotalReasons());
    }

    @Test
    @DisplayName("Should throw exception when getting reason before initialization")
    void testGetRandomReasonBeforeInit() {
        assertThrows(IllegalStateException.class, () -> {
            reasonService.getRandomReason();
        });
    }

    @Test
    @DisplayName("Should return 0 total reasons before initialization")
    void testGetTotalReasonsBeforeInit() {
        assertEquals(0, reasonService.getTotalReasons());
    }
}

