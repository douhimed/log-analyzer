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
                .statusValue(CommonUtils.isBlank(statutValue) ? CommonConstants.REQUEST : statutValue)
                .responseStatutEnum(ResponseStatutEnum.fromStatus(statutValue))
                .build();

    }
}
