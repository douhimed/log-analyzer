package org.sqli.pfe.enset.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/logs")
public class LogController {

    private static final String LOGS_TEMPLATES = "/logs";

    @GetMapping
    public ModelAndView getAllLogs() {
        return new ModelAndView(LOGS_TEMPLATES + "/index");
    }

}
