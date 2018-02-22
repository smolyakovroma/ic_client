package com.immerscontroller.ic_client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class MainController {

    @RequestMapping(value = {"/", "/index"})
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/dashboard");
        return modelAndView;
    }

    @RequestMapping(value = "dashboard", method = RequestMethod.GET)
    public ModelAndView dashboard() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("dashboard");
        return modelAndView;
    }

    @RequestMapping(value = "devices", method = RequestMethod.GET)
    public ModelAndView devices() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("devices");
        return modelAndView;
    }

    @RequestMapping(value = "settings", method = RequestMethod.GET)
    public ModelAndView settings() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("settings");
        return modelAndView;
    }
}
