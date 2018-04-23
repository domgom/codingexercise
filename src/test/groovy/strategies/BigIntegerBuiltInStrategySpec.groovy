package strategies

import com.rbs.interview.PrimesApplication
import com.rbs.interview.PrimesResponse
import com.rbs.interview.strategies.BigIntegerBuiltInStrategy
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = PrimesApplication)
class BigIntegerBuiltInStrategySpec extends Specification {

    @Autowired
    BigIntegerBuiltInStrategy singleThreadStrategy

    def 'Calculates primes until 6'() {
        given:
        BigInteger limit = 6
        when:
        PrimesResponse response = singleThreadStrategy.calculatePrimesUntilLimit(limit)
        then:
        response.initial == limit
        response.primes == [2, 3, 5]
    }

    def 'Calculates primes until 5 (included)'() {
        given:
        BigInteger limit = 5
        when:
        PrimesResponse response = singleThreadStrategy.calculatePrimesUntilLimit(limit)
        then:
        response.initial == limit
        response.primes == [2, 3, 5]
    }

    def 'Calculates primes until 1 with no results'() {
        given:
        BigInteger limit = 1
        when:
        PrimesResponse response = singleThreadStrategy.calculatePrimesUntilLimit(limit)
        then:
        response.initial == limit
        response.primes == []
    }

    def 'Calculates primes until 0 with no results'() {
        given:
        BigInteger limit = 0
        when:
        PrimesResponse response = singleThreadStrategy.calculatePrimesUntilLimit(limit)
        then:
        response.initial == limit
        response.primes == []
    }

    def 'Calculates primes until -1 with no results'() {
        given:
        BigInteger limit = -1
        when:
        PrimesResponse response = singleThreadStrategy.calculatePrimesUntilLimit(limit)
        then:
        response.initial == limit
        response.primes == []
    }
}
