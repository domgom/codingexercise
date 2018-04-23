package com.rbs.interview.strategies;

import com.rbs.interview.PrimesResponse;

import java.math.BigInteger;

public interface PrimesCalculatorStrategy {

    PrimesResponse calculatePrimesUntilLimit(BigInteger limit);
}
