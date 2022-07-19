package org.sqli.pfe.enset.utils.mappers;
import org.sqli.pfe.enset.models.entities.LogEntity;
import org.sqli.pfe.enset.utils.CommonUtils;
import org.sqli.pfe.enset.utils.dtos.BodyInfosDto;
import org.sqli.pfe.enset.utils.dtos.LogDto;
import org.sqli.pfe.enset.utils.enums.LogPathEnum;
import org.sqli.pfe.enset.utils.enums.ResponseStatutEnum;
import org.sqli.pfe.enset.utils.json.JsonUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

public final class LogMapper {
    private static final SimpleDateFormat DATE_FORMAT_PARSER = new SimpleDateFormat("yy-MM-dd HH:mm:ss");

    public static LogDto from(LogEntity entity) {
        return LogDto.builder()
                .id(entity.getId())
                .thread(entity.getThread())
                .login(entity.getLogin())
                .body(entity.getBody())
                .date(entity.getDate().toString())
                .bodyInfosDto(test(CommonUtils.from(JsonUtils.getNodeValueAtPath(entity.getBody(), LogPathEnum.STATUS.getValue()))))
                .build();
    }

    private static BodyInfosDto test(int statutValue) {
        return BodyInfosDto.builder()
                .statusValue(statutValue)
                .responseStatutEnum(ResponseStatutEnum.fromStatus(statutValue))
                .build();
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

    public static List<LogDto> from(List<LogEntity> entities) {
        return entities.stream().map(LogMapper::from).collect(Collectors.toList());
    }
}

