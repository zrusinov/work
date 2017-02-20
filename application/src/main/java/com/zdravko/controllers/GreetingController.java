package com.zdravko.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;

/**
 * Created by C5204250 on 4/26/2016.
 */
@Controller
public class GreetingController {

    private static final Logger log = LoggerFactory.getLogger(GreetingController.class);
    // Example: http://localhost:8181/greeting?name=User
    //This annotation ensure that HTTP request is mapped to this method
    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
                           Model model) {

        File InternalDirectory = new java.io.File("./");
        log.info("result " + InternalDirectory.isDirectory());

        model.addAttribute("name", name);
        return "greeting";
    }

    // Example: http://localhost:8181/index.html
    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public String index() {
        return "index";
    }
}
