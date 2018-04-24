package strategies

import com.rbs.interview.PrimesApplication
import com.rbs.interview.PrimesResponse
import com.rbs.interview.strategies.BigIntegerBuiltInStrategy
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Unroll

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = PrimesApplication)
class BigIntegerBuiltInStrategySpec extends Specification {

    @Autowired
    BigIntegerBuiltInStrategy singleThreadStrategy

    @Unroll
    def 'Calculates primes until #limit'() {
        when:
        PrimesResponse response = singleThreadStrategy.calculatePrimesUntilLimit(limit)
        then:
        response.initial == limit
        response.primes == primes
        where:
        limit | primes
        6     | [2, 3, 5]
        5     | [2, 3, 5]
        1     | []
        0     | []
        -1    | []
    }

    def 'Calculates primes until 2000'() {
        given:
        BigInteger limit = 2000
        when:
        PrimesResponse response = singleThreadStrategy.calculatePrimesUntilLimit(limit)
        then:
        response.initial == limit
        response.primes.size() == 303
        response.primes.last() == 1999
    }
}
