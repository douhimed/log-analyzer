package org.sqli.pfe.enset.utils.mappers;
import org.sqli.pfe.enset.models.entities.LogEntity;
import org.sqli.pfe.enset.utils.dtos.LogDto;
import org.sqli.pfe.enset.utils.enums.LogPathEnum;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public final class LogMapper {

    private static final SimpleDateFormat DATE_FORMAT_PARSER = new SimpleDateFormat("yy-MM-dd HH:mm:ss");

    private static final List<BiConsumer<LogDto, LogEntity>> FIELDS_TO_MAP;

    static {
        FIELDS_TO_MAP = new ArrayList<>();
        FIELDS_TO_MAP.add((dto, entity) -> dto.setCorrelation(entity.getValueAtPath(LogPathEnum.CORRELATION)));
        FIELDS_TO_MAP.add((dto, entity) -> dto.setUrl(entity.getValueAtPath(LogPathEnum.URL)));
    }

    public static LogDto from(LogEntity entity) {
        final LogDto logDto = LogDto.builder()
                .id(entity.getId())
                .thread(entity.getThread())
                .login(entity.getLogin())
                .body(entity.getBody())
                .date(entity.getDate().toString())
                .bodyInfosDto(LogInfoMapper.from(entity))
                .build();

        FIELDS_TO_MAP.forEach(consumer -> consumer.accept(logDto, entity));

        return logDto;
    }

    public static LogEntity from(LogDto dto) throws ParseException {
        return LogEntity.builder()
                .id(dto.getId())
                .thread(dto.getThread())
                .login(dto.getLogin())
                .body(dto.getBody())
                .date(DATE_FORMAT_PARSER.parse(dto.getDate()))
                .build();
    }

}

