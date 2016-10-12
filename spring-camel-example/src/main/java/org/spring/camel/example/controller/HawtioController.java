package org.spring.camel.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class HawtioController {

    @RequestMapping(value = "/", method = GET)
    public String redirectToHawtioUi() {
        return "redirect:/hawtio/index.html";
    }

}
