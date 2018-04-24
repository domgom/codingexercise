package strategies

import com.rbs.interview.PrimesResponse
import com.rbs.interview.strategies.BigIntegerBuiltInStrategy
import com.rbs.interview.strategies.EratosthenesSieveClassicStrategy
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

/*
* Common test class for all the strategies. Individual differences in the implementation handled at each strategy Spec.
 */
class AllStrategiesSpec extends Specification {

    @Shared
    BigIntegerBuiltInStrategy builtInStrategy = new BigIntegerBuiltInStrategy()

    @Shared
    EratosthenesSieveClassicStrategy eratosthenesSieve = new EratosthenesSieveClassicStrategy()

    @Unroll
    def 'Calculates primes until #limit'() {
        when:
        PrimesResponse response = strategy.calculatePrimesUntilLimit(limit)
        then:
        response.initial == limit
        response.primes == primes
        where:
        limit | primes    | strategy
        6     | [2, 3, 5] | builtInStrategy
        5     | [2, 3, 5] | builtInStrategy
        1     | []        | builtInStrategy
        6     | [2, 3, 5] | eratosthenesSieve
        5     | [2, 3, 5] | eratosthenesSieve
        1     | []        | eratosthenesSieve

    }

    def 'Calculates primes until 2000'() {
        given:
        BigInteger limit = 2000
        when:
        PrimesResponse response = strategy.calculatePrimesUntilLimit(limit)
        then:
        response.initial == limit
        response.primes.size() == 303
        response.primes.last() == 1999
        where:
        strategy << [builtInStrategy, eratosthenesSieve]
    }
}
