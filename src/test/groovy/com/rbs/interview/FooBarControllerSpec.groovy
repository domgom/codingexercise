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
class FooBarControllerSpec extends Specification {

    @Autowired
    TestRestTemplate restTemplate

    def 'Foo test'() {
        when:
        def entity = restTemplate.getForEntity('/foo', Bar)
        then:
        entity.statusCode == HttpStatus.OK
        entity.body.name == 'Dom'
        entity.body.age == 35

    }
}
