package com.cj;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResponseController {

    @RequestMapping("/ask/{name}")
    public String ask(@PathVariable("name") String name) {
        return "hello".concat(name);
    }

}
