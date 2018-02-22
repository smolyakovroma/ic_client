package com.immerscontroller.ic_client.controller.rest;

import com.immerscontroller.ic_client.domain.Test;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Test test() {
        return new Test(1,"HELLO");
    }

    @RequestMapping(value = "/test2/{name}", method = RequestMethod.GET)
    public Test test2(@PathVariable String name) {
        return new Test(2, "Hello "+name);
    }

}
