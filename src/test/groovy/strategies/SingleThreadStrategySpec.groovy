package strategies

import com.rbs.interview.PrimesApplication
import com.rbs.interview.PrimesResponse
import com.rbs.interview.strategies.SingleThreadStrategy
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = PrimesApplication)
class SingleThreadStrategySpec extends Specification {

    @Autowired
    SingleThreadStrategy singleThreadStrategy

    def 'Calculates primes until 6'() {
        when:
        PrimesResponse response = singleThreadStrategy.calculatePrimesUntilLimit(6)
        then:
        response.initial == 6
        response.primes == [3, 2]
    }
}
