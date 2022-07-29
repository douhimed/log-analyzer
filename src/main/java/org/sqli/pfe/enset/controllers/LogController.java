package org.sqli.pfe.enset.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.sqli.pfe.enset.services.LogServices;
import org.sqli.pfe.enset.utils.common.CommonUtils;
import org.sqli.pfe.enset.utils.dtos.LogDto;
import org.sqli.pfe.enset.utils.dtos.SearchParamDto;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping({"/", "/logs"})
public class LogController {

    private static final String LOGS_TEMPLATES = "/logs";

    @Autowired
    private LogServices logServices;

    @GetMapping
    public String getAllLogs(Model model,
                             @RequestParam(name = "page", defaultValue = "1") int page,
                             HttpServletRequest request,
                             @RequestParam(required = false) String login,
                             @RequestParam(required = false) String thread) {
        final Page<LogDto> logDtoPage = getPageOfLogs(login, thread, PageRequest.of(page - 1, 12));
        mapperModel(model, page, logDtoPage, request, CommonUtils.isNotBlank(thread) || CommonUtils.isNotBlank(login));
        return calculPage(page, logDtoPage);
    }

    private Page<LogDto> getPageOfLogs(String login, String thread, PageRequest pageable) {
        return CommonUtils.isBlank(thread) && CommonUtils.isBlank(login)
                ? this.logServices.getAllLogs(pageable)
                : this.logServices.getBySearchParams(pageable, SearchParamDto.builder().login(login).thread(thread).build());
    }

    private String calculPage(int page, Page<LogDto> logDtoPage) {
        return LOGS_TEMPLATES + (page - 1 >= logDtoPage.getTotalPages() ? "/not_found" : "/index");
    }

    private void mapperModel(Model model, int page, Page<LogDto> logDtoPage, HttpServletRequest request, boolean estModeSearch) {
        model.addAttribute("logsPage", logDtoPage);
        model.addAttribute("search", estModeSearch);
        model.addAttribute("currentPage", page - 1);
        model.addAttribute("size", 16);
        model.addAttribute("pages", new int[logDtoPage.getTotalPages()]);
        model.addAttribute("url", ServletUriComponentsBuilder.fromRequestUri(request).replacePath(null).build().toUriString());
    }

}
