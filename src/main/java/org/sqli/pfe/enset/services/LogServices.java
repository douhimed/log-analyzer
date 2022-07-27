package org.sqli.pfe.enset.services;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.sqli.pfe.enset.utils.dtos.BatchResponseDto;
import org.sqli.pfe.enset.utils.dtos.LogDto;
import org.sqli.pfe.enset.utils.dtos.SearchParamDto;
import org.sqli.pfe.enset.utils.exceptions.DataNotFoundException;

public interface LogServices {

    BatchResponseDto runBatch();

    Page<LogDto> getAllLogs(Pageable pageable);
    Page<LogDto> getBySearchParams(Pageable pageable, SearchParamDto searchParamDto);

    LogDto findById(long id) throws DataNotFoundException;
}
