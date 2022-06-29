package org.sqli.pfe.enset.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/testLog")
    public ResponseEntity<String> testApp() {
        return ResponseEntity.ok("App Started");
    }

}
