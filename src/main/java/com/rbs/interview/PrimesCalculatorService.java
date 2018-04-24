package com.rbs.interview;

import com.rbs.interview.strategies.AvailableStrategy;
import com.rbs.interview.strategies.PrimesCalculatorStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;

/**
 * Service with the responsibility of receiving an Optional {@link AvailableStrategy} and retrieve it from the
 * Spring context or defaults to {@link PrimesCalculatorService#defaultStrategy}, configurable in application.yaml
 */
@Component
public class PrimesCalculatorService {
    private final AvailableStrategy defaultStrategy;
    private final Map<AvailableStrategy, PrimesCalculatorStrategy> strategies;

    public PrimesCalculatorService(@Autowired Set<PrimesCalculatorStrategy> strategies,
                                   @Value("${default.strategy:BIG_INTEGER_BUILT_IN}") AvailableStrategy defaultStrategy) {
        this.strategies = strategies.stream().collect(Collectors.toMap(PrimesCalculatorStrategy::name, identity()));
        this.defaultStrategy = defaultStrategy;
    }

    public PrimesResponse primesUntil(BigInteger limit, Optional<AvailableStrategy> strategy) {
        return strategies.get(strategy.orElse(defaultStrategy))
                .calculatePrimesUntilLimit(limit);
    }
}
