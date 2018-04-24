package strategies

import com.rbs.interview.PrimesApplication
import com.rbs.interview.PrimesResponse
import com.rbs.interview.strategies.EratosthenesSieveClassicStrategy
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = PrimesApplication)
class EratosthenesSieveClassicStrategySpec extends Specification {

    @Autowired
    EratosthenesSieveClassicStrategy eratosthenesClassic

    def 'Calculates primes until 6'() {
        given:
        BigInteger limit = 6
        when:
        PrimesResponse response = eratosthenesClassic.calculatePrimesUntilLimit(limit)
        then:
        response.initial == limit
        response.primes == [2, 3, 5]
    }

    def 'Calculates primes until 5 (included)'() {
        given:
        BigInteger limit = 5
        when:
        PrimesResponse response = eratosthenesClassic.calculatePrimesUntilLimit(limit)
        then:
        response.initial == limit
        response.primes == [2, 3, 5]
    }

    def 'Calculates primes until 1 with no results'() {
        given:
        BigInteger limit = 1
        when:
        PrimesResponse response = eratosthenesClassic.calculatePrimesUntilLimit(limit)
        then:
        response.initial == limit
        response.primes == []
    }

    def 'Calculates primes until 0 with no results'() {
        given:
        BigInteger limit = 0
        when:
        eratosthenesClassic.calculatePrimesUntilLimit(limit)
        then:
        thrown(IllegalArgumentException)
    }

    def 'Calculates primes until -1 with returning IllegalArgumentException'() {
        given:
        BigInteger limit = -1
        when:
        eratosthenesClassic.calculatePrimesUntilLimit(limit)
        then:
        thrown(IllegalArgumentException)
    }

    def 'Calculates primes until 2000'() {
        given:
        BigInteger limit = 2000
        when:
        PrimesResponse response = eratosthenesClassic.calculatePrimesUntilLimit(limit)
        then:
        response.initial == limit
        response.primes.size() ==  303
        response.primes.last() == 1999
    }
}
