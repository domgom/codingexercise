package com.rbs.interview;

import com.rbs.interview.strategies.AvailableStrategy;
import com.rbs.interview.strategies.PrimesCalculatorStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;

@Component
public class PrimesCalculatorService {

    private static final AvailableStrategy DEFAULT_STRATEGY = AvailableStrategy.BIG_INTEGER_BUILT_IN;
    private final Map<AvailableStrategy, PrimesCalculatorStrategy> strategies;

    public PrimesCalculatorService(@Autowired Set<PrimesCalculatorStrategy> strategies) {
        this.strategies = strategies.stream().collect(Collectors.toMap(PrimesCalculatorStrategy::name, identity()));
    }

    public PrimesResponse primesUntil(BigInteger limit, Optional<AvailableStrategy> strategy) {
        return strategies.get(strategy.orElse(DEFAULT_STRATEGY))
                .calculatePrimesUntilLimit(limit);
    }
}
