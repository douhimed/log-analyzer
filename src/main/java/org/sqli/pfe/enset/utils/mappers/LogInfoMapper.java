package org.sqli.pfe.enset.utils.mappers;

import org.sqli.pfe.enset.models.entities.LogEntity;
import org.sqli.pfe.enset.utils.common.CommonUtils;
import org.sqli.pfe.enset.utils.constants.CommonConstants;
import org.sqli.pfe.enset.utils.dtos.BodyInfosDto;
import org.sqli.pfe.enset.utils.enums.LogPathEnum;
import org.sqli.pfe.enset.utils.enums.ResponseStatutEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public final class LogInfoMapper {

    private static final List<BiConsumer<BodyInfosDto, LogEntity>> FIELDS_TO_MAP;

    static {
        FIELDS_TO_MAP = new ArrayList<>();
        FIELDS_TO_MAP.add((dto, entity) -> dto.setMethod(entity.getValueAtPath(LogPathEnum.METHOD)));
        FIELDS_TO_MAP.add((dto, entity) -> dto.setUserName(entity.getValueAtPath(LogPathEnum.USER_NAME)));
        FIELDS_TO_MAP.add((dto, entity) -> dto.setIdentifiant(entity.getValueAtPath(LogPathEnum.IDENTIFIANT)));
        FIELDS_TO_MAP.add((dto, entity) -> dto.setOrganisationId(entity.getValueAtPath(LogPathEnum.ORGANISATION_ID)));
    }

    public static BodyInfosDto from(LogEntity logEntity) {
        final String statutValue = logEntity.getValueAtPath(LogPathEnum.STATUS);

        final BodyInfosDto bodyInfosDto = BodyInfosDto.builder()
                .statut(CommonUtils.isBlank(statutValue) ? CommonConstants.REQUEST : statutValue)
                .statutEnum(ResponseStatutEnum.fromStatus(statutValue))
                .build();

        FIELDS_TO_MAP.forEach(consumer -> consumer.accept(bodyInfosDto, logEntity));

        return bodyInfosDto;

    }
}
