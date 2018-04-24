package strategies

import com.rbs.interview.PrimesApplication
import com.rbs.interview.PrimesResponse
import com.rbs.interview.strategies.EratosthenesSieveClassicStrategy
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Unroll

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = PrimesApplication)
class EratosthenesSieveClassicStrategySpec extends Specification {

    @Autowired
    EratosthenesSieveClassicStrategy eratosthenesClassic

    @Unroll
    def 'Calculates primes until #limit'() {
        when:
        PrimesResponse response = eratosthenesClassic.calculatePrimesUntilLimit(limit)
        then:
        response.initial == limit
        response.primes == primes
        where:
        limit | primes
        6     | [2, 3, 5]
        5     | [2, 3, 5]
        1     | []
    }

    @Unroll
    def 'Calculates primes until #limit with returning IllegalArgumentException'() {
        when:
        eratosthenesClassic.calculatePrimesUntilLimit(limit)
        then:
        thrown(IllegalArgumentException)
        where:
        limit << [-1, 0]
    }

    def 'Calculates primes until 2000'() {
        given:
        BigInteger limit = 2000
        when:
        PrimesResponse response = eratosthenesClassic.calculatePrimesUntilLimit(limit)
        then:
        response.initial == limit
        response.primes.size() == 303
        response.primes.last() == 1999
    }
}
