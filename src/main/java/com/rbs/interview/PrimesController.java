package com.rbs.interview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

import static java.math.BigInteger.valueOf;


@RestController
class PrimesController {

    @Autowired
    PrimeCalculatorService primeCalculatorService;

    @GetMapping(value = "/primes/{limit}")
    PrimeResponse primesUntil(@PathVariable("limit") BigInteger limit) {
        return new PrimeResponse(limit, List.of(valueOf(2), valueOf(3)));
    }
}
