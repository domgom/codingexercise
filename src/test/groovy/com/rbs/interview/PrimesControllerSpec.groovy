package com.rbs.interview

import com.rbs.interview.strategies.AvailableStrategy
import spock.lang.Specification
import spock.lang.Unroll

class PrimesControllerSpec extends Specification {

    PrimesCalculatorService primesCalculatorService = Mock(PrimesCalculatorService)
    PrimesController primesController = new PrimesController(primesCalculatorService: primesCalculatorService)

    @Unroll
    def 'PrimesController.primesUntil( #limit ) with strategy #strategy'() {
        when:
        PrimesResponse response = primesController.primesUntil(limit, strategy)
        then:
        1 * primesCalculatorService.primesUntil(limit, strategy) >> new PrimesResponse(limit, [2, 3, 5])
        0 * _
        response.primes == [2, 3, 5]
        response.initial == limit
        where:
        limit | strategy
        6     | Optional.empty()
        6     | Optional.of(AvailableStrategy.ERATHOSTENES_SIEVE_CLASSIC)
        6     | Optional.of(AvailableStrategy.BIG_INTEGER_BUILT_IN)


    }
}
