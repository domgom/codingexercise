package com.rbs.interview.strategies;

import com.rbs.interview.PrimesResponse;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static java.math.BigInteger.TWO;

/**
 * Uses the internal {@link BigInteger} method {@link BigInteger#isProbablePrime(int)} to iterate among the next
 * candidates and {@link BigInteger#isProbablePrime(int)} to run the verification.
 * Delegates most of the implementation for convenience at the expense of further sieve  optimisation.
 */
@Component
public class BigIntegerBuiltInStrategy implements PrimesCalculatorStrategy {

    private static final int CERTAINTY = 100;

    @Override
    public AvailableStrategy name() {
        return AvailableStrategy.BIG_INTEGER_BUILT_IN;
    }

    @Override
    public PrimesResponse calculatePrimesUntilLimit(BigInteger limit) {
        List<BigInteger> primes = new ArrayList<>();

        BigInteger index = TWO;

        while (index.compareTo(limit) != 1) {
            if (isPrime(index)) {
                primes.add(index);
            }
            index = index.nextProbablePrime();
        }
        return new PrimesResponse(limit, primes);
    }

    private boolean isPrime(BigInteger candidate) {
        return candidate.isProbablePrime(CERTAINTY);
    }
}
