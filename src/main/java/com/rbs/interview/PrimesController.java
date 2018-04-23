package com.rbs.interview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.Optional;


@RestController
class PrimesController {

    @Autowired
    PrimesCalculatorService primesCalculatorService;

    @GetMapping(value = "/primes/{limit}")
    PrimesResponse primesUntil(@PathVariable("limit") BigInteger limit, @RequestParam("strategy") Optional<String> strategy) {
        return primesCalculatorService.primesUntil(limit, strategy);
    }
}
