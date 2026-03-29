package com.aws.noService.controller;

import com.aws.noService.service.ReasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReasonController {
    private final ReasonService reasonService;

    @Autowired
    public ReasonController(ReasonService reasonService) {
        this.reasonService = reasonService;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String ping() {
        return "Do you meant to say no?";
    }

    @RequestMapping(path = "/no", method = RequestMethod.GET)
    public String getRandomReason() {
        return reasonService.getRandomReason();
    }

    @RequestMapping(path = "/no/count", method = RequestMethod.GET)
    public int getTotalReasons() {
        return reasonService.getTotalReasons();
    }
}
