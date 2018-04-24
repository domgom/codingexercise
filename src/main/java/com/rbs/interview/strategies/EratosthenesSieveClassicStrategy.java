package com.rbs.interview.strategies;

import com.rbs.interview.PrimesResponse;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/**
 * Sieve of Erathostenes with a single thread
 */
@Component
public class EratosthenesSieveClassicStrategy implements PrimesCalculatorStrategy {


    @Override
    public AvailableStrategy name() {
        return AvailableStrategy.ERATHOSTENES_SIEVE_CLASSIC;
    }

    @Override
    public PrimesResponse calculatePrimesUntilLimit(BigInteger bigLimit) {
        // We are constrained by the BitSet size to be Integer.MAX_VALUE
        int limit = bigLimit.intValueExact();
        if (limit <= 0) {
            throw new IllegalArgumentException("Limit has to be > 0");
        }
        long startTime = System.currentTimeMillis();
        // Initialises positions filled with 0
        BitSet isPrime = new BitSet(limit + 1);
        // All numbers are supposed prime before the sieve
        isPrime.flip(0, limit + 1);
        // 0 and 1 are prime
        isPrime.clear(0);
        isPrime.clear(1);


        for (int i = 2; i * i <= limit; i += 1) {
            //If the number is in the list is because it wasn't marked as composite
            if (isPrime.get(i)) {
                // We mark all the multiples of the prime
                for (int j = 2; i * j <= limit; j += 1) {
                    isPrime.clear(i * j);
                }
            }
        }

        // Initialized with a predetermined size to avoid memory reassignments
        List<BigInteger> primes = new ArrayList<>(estimatedSize(limit));

        for (int i = 1; i <= limit; i++) {
            if (isPrime.get(i)) {
                primes.add(BigInteger.valueOf(i));
            }
        }

        long stopTime = System.currentTimeMillis();
        System.out.println((stopTime - startTime) + " milliseconds.");

        return new PrimesResponse(bigLimit, primes);
    }

    private int estimatedSize(int limit) {
        if (limit < 2) {
            return 0;
        }
        //The Prime Number Theorem: The number of primes not exceeding x is asymptotic to x/log x.
        return (int) Math.ceil(limit / Math.log(limit));
    }

}
