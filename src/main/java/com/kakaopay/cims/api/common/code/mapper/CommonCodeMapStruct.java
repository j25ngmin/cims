package com.kakaopay.cims.api.common.code.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.kakaopay.cims.api.common.code.dto.CommonCodeDto;
import com.kakaopay.cims.api.common.code.entity.CommonCodeEntity;
import com.kakaopay.cims.core.util.EntityMapStruct;

@Mapper
public interface CommonCodeMapStruct extends EntityMapStruct<CommonCodeDto, CommonCodeEntity> {
    CommonCodeMapStruct INSTANCE = Mappers.getMapper(CommonCodeMapStruct.class);

}
