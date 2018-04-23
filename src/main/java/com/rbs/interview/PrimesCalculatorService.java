package com.rbs.interview;

import com.rbs.interview.strategies.PrimesCalculatorStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Map;
import java.util.Optional;

@Component
public class PrimesCalculatorService {

    @Autowired
    private Map<String, PrimesCalculatorStrategy> strategies;

    public PrimesResponse primesUntil(BigInteger limit, Optional<String> strategy) {
        return strategies.get(strategy.orElse("singleThreadStrategy"))
                .calculatePrimesUntilLimit(limit);
    }
}
