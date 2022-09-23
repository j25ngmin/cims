package com.kakaopay.cims.api.common.code.mapper;

import com.kakaopay.cims.api.common.code.dto.CommonCodeDto;
import com.kakaopay.cims.api.common.code.entity.CommonCodeEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-23T22:38:11+0900",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 17.0.4.1 (Eclipse Adoptium)"
)
public class CommonCodeMapStructImpl implements CommonCodeMapStruct {

    @Override
    public CommonCodeEntity toEntity(CommonCodeDto dto) {
        if ( dto == null ) {
            return null;
        }

        CommonCodeEntity commonCodeEntity = new CommonCodeEntity();

        commonCodeEntity.setCommonCodeSeq( dto.getCommonCodeSeq() );
        commonCodeEntity.setGroupCode( dto.getGroupCode() );
        commonCodeEntity.setCode( dto.getCode() );
        commonCodeEntity.setCreateUserId( dto.getCreateUserId() );
        commonCodeEntity.setCreateDate( dto.getCreateDate() );
        commonCodeEntity.setModifyUserId( dto.getModifyUserId() );
        commonCodeEntity.setModifyDate( dto.getModifyDate() );

        return commonCodeEntity;
    }

    @Override
    public CommonCodeDto toDto(CommonCodeEntity entity) {
        if ( entity == null ) {
            return null;
        }

        CommonCodeDto commonCodeDto = new CommonCodeDto();

        commonCodeDto.setCommonCodeSeq( entity.getCommonCodeSeq() );
        commonCodeDto.setGroupCode( entity.getGroupCode() );
        commonCodeDto.setCode( entity.getCode() );
        commonCodeDto.setCreateUserId( entity.getCreateUserId() );
        commonCodeDto.setCreateDate( entity.getCreateDate() );
        commonCodeDto.setModifyUserId( entity.getModifyUserId() );
        commonCodeDto.setModifyDate( entity.getModifyDate() );

        return commonCodeDto;
    }
}
