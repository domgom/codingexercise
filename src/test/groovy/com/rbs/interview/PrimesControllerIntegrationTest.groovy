package com.rbs.interview

import com.rbs.interview.strategies.AvailableStrategy
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import spock.lang.Specification
import spock.lang.Unroll

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT)
class PrimesControllerIntegrationTest extends Specification {

    @Autowired
    TestRestTemplate restTemplate

    @Unroll
    def 'Primes until #limit with default strategy'() {
        when:
        def entity = restTemplate.getForEntity('/primes/' + limit, PrimesResponse)
        then:
        entity.statusCode == HttpStatus.OK
        entity.body.initial == limit
        entity.body.primes == [2, 3, 5]
        where:
        limit = 6

    }

    @Unroll
    def 'Primes until #limit with selected strategies'() {
        when:
        def entity = restTemplate.getForEntity('/primes/' + limit + '?strategy=' + strategy, PrimesResponse)
        then:
        entity.statusCode == HttpStatus.OK
        entity.body.initial == limit
        entity.body.primes == [2, 3, 5, 7, 11]
        where:
        limit | strategy
        12    | ''  // Uses default
        12    | AvailableStrategy.BIG_INTEGER_BUILT_IN.name()
        12    | AvailableStrategy.ERATHOSTENES_SIEVE_CLASSIC.name()

    }
}
