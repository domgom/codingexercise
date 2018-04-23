package com.rbs.interview

import org.springframework.context.annotation.Bean
import spock.mock.DetachedMockFactory

class TestConfiguration {
    private final detachedMockFactory = new DetachedMockFactory()

    @Bean
    PrimesCalculatorService externalApiClient() {
        detachedMockFactory.Mock(PrimesCalculatorService)
    }
}
