package com.dish.ofm.service.jwa.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindSummerService {
    private static final Logger logger = LoggerFactory.getLogger(FindSummerService.class);

    public FindSummerService() {
    }

    public List<List<Integer>> findSummer(
        int min,
        int max,
        int total
    )  {
        return null;
    }
}
