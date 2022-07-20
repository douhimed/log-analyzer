package org.sqli.pfe.enset.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sqli.pfe.enset.services.LogServices;
import org.sqli.pfe.enset.utils.dtos.BatchResponseDto;
import org.sqli.pfe.enset.utils.dtos.LogDto;

@RestController
@RequestMapping("/rest/logs")
public class LogRestController {

    @Autowired
    private LogServices logServices;

    @GetMapping("/importlogs")
    public ResponseEntity<BatchResponseDto> startImportLogs() {
        return ResponseEntity.ok(this.logServices.runBatch());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LogDto> getById(@PathVariable long id) {
        return ResponseEntity.ok(this.logServices.findById(id));
    }
}
