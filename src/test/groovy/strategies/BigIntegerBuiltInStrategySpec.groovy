package strategies

import com.rbs.interview.PrimesResponse
import com.rbs.interview.strategies.BigIntegerBuiltInStrategy
import spock.lang.Specification
import spock.lang.Unroll

class BigIntegerBuiltInStrategySpec extends Specification {

    BigIntegerBuiltInStrategy builtInStrategy = new BigIntegerBuiltInStrategy()

    @Unroll
    def 'Calculates primes until #limit'() {
        when:
        PrimesResponse response = builtInStrategy.calculatePrimesUntilLimit(limit)
        then:
        response.initial == limit
        response.primes == primes
        where:
        limit | primes
        0     | []
        -1    | []
    }
}
