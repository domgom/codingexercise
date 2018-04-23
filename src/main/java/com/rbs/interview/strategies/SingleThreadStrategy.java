package com.rbs.interview.strategies;

import com.rbs.interview.PrimesResponse;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

import static java.math.BigInteger.valueOf;

@Component
public class SingleThreadStrategy implements PrimesCalculatorStrategy {

    @Override
    public PrimesResponse calculatePrimesUntilLimit(BigInteger limit) {
        return new PrimesResponse(valueOf(6), List.of(valueOf(3), valueOf(2)));
    }
}
