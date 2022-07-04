package org.sqli.pfe.enset.services;

import org.sqli.pfe.enset.utils.dtos.BatchResponseDto;
import org.sqli.pfe.enset.utils.dtos.LogDto;

import java.util.List;

public interface LogServices {

    BatchResponseDto runBatch();

    List<LogDto> findAll();

}
