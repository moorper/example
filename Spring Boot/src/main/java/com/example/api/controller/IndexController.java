package com.example.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * IndexController
 *
 * @author Where
 * @date 2019-01-31
 */
@RestController
@RequestMapping(produces = "application/json; charset=utf-8")
public class IndexController {
    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public String ping() {
        return "pong";
    }
}
