package com.rbs.interview

import com.rbs.interview.strategies.AvailableStrategy
import com.rbs.interview.strategies.PrimesCalculatorStrategy
import spock.lang.Specification
import spock.lang.Unroll

class PrimesCalculatorServiceSpec extends Specification {


    @Unroll
    def 'PrimesCalculatorService selects the right available strategy from the parameter'() {
        given:
        BigInteger limit = 10
        AvailableStrategy defaultStrategyEnum = AvailableStrategy.BIG_INTEGER_BUILT_IN
        AvailableStrategy additionalStrategyEnum = AvailableStrategy.ERATHOSTENES_SIEVE_CLASSIC

        PrimesCalculatorStrategy defaultStrategy = Mock(PrimesCalculatorStrategy)
        PrimesCalculatorStrategy additionalStrategy = Mock(PrimesCalculatorStrategy)
        when: 'service is instantiated'
        PrimesCalculatorService primesCalculatorService =
                new PrimesCalculatorService(Set.of(defaultStrategy, additionalStrategy), defaultStrategyEnum)
        then: 'strategies are correctly populated internally'
        1 * defaultStrategy.name() >> defaultStrategyEnum
        1 * additionalStrategy.name() >> additionalStrategyEnum
        primesCalculatorService.strategies.keySet() == [defaultStrategyEnum, additionalStrategyEnum] as Set
        primesCalculatorService.strategies.values() as Set == [defaultStrategy, additionalStrategy] as Set
        0 * _
        when: 'service is called with strategy = additionalStrategy'
        primesCalculatorService.primesUntil(limit, Optional.of(additionalStrategyEnum))
        then: 'additionalStrategy is used'
        1 * additionalStrategy.calculatePrimesUntilLimit(limit)
        0 * _
        when: 'service is called without strategy'
        primesCalculatorService.primesUntil(limit, Optional.empty())
        then: 'defaultStrategy is used'
        1 * defaultStrategy.calculatePrimesUntilLimit(limit)
        0 * _
    }
}
