package com.rbs.interview;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@JsonFormat()
public class PrimeResponse {

    @JsonProperty("Initial")
    @JsonFormat(shape = STRING)
    private BigInteger initial;
    @JsonProperty("Primes")
    private List<BigInteger> primes;

    public PrimeResponse() {

    }

    public PrimeResponse(BigInteger initial, List<BigInteger> primes) {
        this.initial = initial;
        this.primes = primes;
    }

    public BigInteger getInitial() {
        return initial;
    }

    public void setInitial(BigInteger initial) {
        this.initial = initial;
    }

    public List<BigInteger> getPrimes() {
        return primes;
    }

    public void setPrimes(List<BigInteger> primes) {
        this.primes = primes;
    }
}
