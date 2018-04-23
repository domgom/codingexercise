package com.rbs.interview

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.annotation.Import
import org.springframework.http.HttpStatus
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT


@SpringBootTest(webEnvironment = RANDOM_PORT)
@Import(TestConfiguration)
class PrimesControllerSpec extends Specification {

    @Autowired
    TestRestTemplate restTemplate

    def 'Primes until 6'() {
        given:
        BigInteger limit = 6
        when:
        def entity = restTemplate.getForEntity('/primes/' + limit, PrimesResponse)
        then:
        entity.statusCode == HttpStatus.OK
        entity.body.initial == limit
        entity.body.primes == [2, 3, 5]

    }
}
