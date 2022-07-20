package org.sqli.pfe.enset.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sqli.pfe.enset.services.LogServices;
import org.sqli.pfe.enset.utils.dtos.BatchResponseDto;

@RestController
@RequestMapping("/jobs")
public class JobRestController {

    @Autowired
    private LogServices logServices;

    @GetMapping("/importlogs")
    public ResponseEntity<BatchResponseDto> startImportLogs() {
        return ResponseEntity.ok(this.logServices.runBatch());
    }

}
