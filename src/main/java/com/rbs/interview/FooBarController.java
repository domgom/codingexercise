package com.rbs.interview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
class FooBarController {

    @Autowired
    Baz baz;

    @GetMapping(value = "/foo")
    Bar foo() {
        return new Bar("Dom", 35);
    }
}
