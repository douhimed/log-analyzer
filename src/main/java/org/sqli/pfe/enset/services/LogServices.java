package org.sqli.pfe.enset.services;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.sqli.pfe.enset.utils.dtos.BatchResponseDto;
import org.sqli.pfe.enset.utils.dtos.LogDto;
import org.sqli.pfe.enset.utils.exceptions.DataNotFoundException;

public interface LogServices {

    BatchResponseDto runBatch();

    Page<LogDto> findAll(Pageable pageable);

    LogDto findById(long id) throws DataNotFoundException;
}
