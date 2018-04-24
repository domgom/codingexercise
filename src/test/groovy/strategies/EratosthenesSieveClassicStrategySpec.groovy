package strategies

import com.rbs.interview.strategies.EratosthenesSieveClassicStrategy
import spock.lang.Specification
import spock.lang.Unroll

class EratosthenesSieveClassicStrategySpec extends Specification {

    EratosthenesSieveClassicStrategy eratosthenesClassic = new EratosthenesSieveClassicStrategy()

    @Unroll
    def 'Calculates primes until #limit with returning IllegalArgumentException'() {
        when:
        eratosthenesClassic.calculatePrimesUntilLimit(limit)
        then:
        thrown(IllegalArgumentException)
        where:
        limit << [-1, 0]
    }
}
