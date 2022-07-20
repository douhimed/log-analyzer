package org.sqli.pfe.enset.utils.mappers;

import org.sqli.pfe.enset.models.entities.LogEntity;
import org.sqli.pfe.enset.utils.CommonUtils;
import org.sqli.pfe.enset.utils.constants.CommonConstants;
import org.sqli.pfe.enset.utils.dtos.BodyInfosDto;
import org.sqli.pfe.enset.utils.enums.LogPathEnum;
import org.sqli.pfe.enset.utils.enums.ResponseStatutEnum;

public final class LogInfoMapper {

    public static BodyInfosDto from(LogEntity logEntity) {
        final String statutValue = logEntity.getValueAtPath(LogPathEnum.STATUS);

        return BodyInfosDto.builder()
                .statut(CommonUtils.isBlank(statutValue) ? CommonConstants.REQUEST : statutValue)
                .statutEnum(ResponseStatutEnum.fromStatus(statutValue))
                .method(logEntity.getValueAtPath(LogPathEnum.METHOD))
                .userName(logEntity.getValueAtPath(LogPathEnum.USER_NAME))
                .build();

    }
}
