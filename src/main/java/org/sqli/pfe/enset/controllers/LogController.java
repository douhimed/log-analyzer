package org.sqli.pfe.enset.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.sqli.pfe.enset.models.entities.LogEntity;
import org.sqli.pfe.enset.repositories.LogRepository;
import org.sqli.pfe.enset.services.LogServices;
import org.sqli.pfe.enset.utils.dtos.LogDto;

import java.util.List;

@Controller
@RequestMapping({"/", "/logs"})
public class LogController {

    private static final String LOGS_TEMPLATES = "/logs";

    @Autowired
    private LogServices logServices;
    @GetMapping
    public String getAllLogs(Model model) {
        List<LogDto> logsDtos = this.logServices.findAll();
        model.addAttribute("logs", logsDtos);
        return LOGS_TEMPLATES + "/index";
    }

}
