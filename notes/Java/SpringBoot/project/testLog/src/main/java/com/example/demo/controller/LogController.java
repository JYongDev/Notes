package com.example.demo.controller;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class LogController {

    Logger logger = (Logger) LoggerFactory.getLogger(LogController.class);

    @RequestMapping("/log")
    public Object log() {
        String logStr = "This is String from method 'log' ";
        logger.info(logStr);
        return logStr;
    }

    @RequestMapping("/trace")
    public Object trace() {
        String str = "str come from method 'trace' ";
        logger.info(str);
        logger.trace(str);
        logger.error(str);
        logger.warn(str);
        logger.debug(str);
        return str;
    }
}
