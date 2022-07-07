package org.sqli.pfe.enset.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.sqli.pfe.enset.services.LogServices;
import org.sqli.pfe.enset.utils.dtos.LogDto;

@Controller
@RequestMapping({"/", "/logs"})
public class LogController {

    private static final String LOGS_TEMPLATES = "/logs";

    @Autowired
    private LogServices logServices;
    @GetMapping
    public String getAllLogs(Model model,
                             @RequestParam(name="page",defaultValue ="1") int page){
        Page<LogDto> logDtoPage = this.logServices.findAll(PageRequest.of(page - 1,16));
        model.addAttribute("logsPage", logDtoPage);
        model.addAttribute("currentPage",page - 1);
        model.addAttribute("size",16);
        model.addAttribute("pages",new int[logDtoPage.getTotalPages()]);
        return LOGS_TEMPLATES + "/index";
    }

}
