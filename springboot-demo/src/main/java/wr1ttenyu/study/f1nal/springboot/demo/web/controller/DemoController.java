package wr1ttenyu.study.f1nal.springboot.demo.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/demo")
public class DemoController {

    private static Logger LOGGER = LoggerFactory.getLogger(DemoController.class);

    @RequestMapping("/hello")
    public String hello(@RequestParam("name") String name) {
        LOGGER.info("hello Controller 被执行了");
        return "hello, " + name;
    }
}
