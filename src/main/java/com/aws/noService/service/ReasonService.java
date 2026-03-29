package com.aws.noService.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Random;

@Service
public class ReasonService {
    private List<String> reasons;
    private final Random random = new Random();

    @PostConstruct
    public void init() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ClassPathResource resource = new ClassPathResource("reasons.json");
        reasons = mapper.readValue(resource.getInputStream(), new TypeReference<List<String>>(){});
    }

    /**
     * Returns a random reason from the loaded reasons list.
     *
     * @return a random reason string
     */
    public String getRandomReason() {
        if (reasons == null || reasons.isEmpty()) {
            throw new IllegalStateException("Reasons list is not initialized");
        }
        return reasons.get(random.nextInt(reasons.size()));
    }

    /**
     * Returns the total number of available reasons.
     *
     * @return the size of the reasons list
     */
    public int getTotalReasons() {
        return reasons != null ? reasons.size() : 0;
    }
}

