package com.kakaopay.cims.api.inquiry.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.kakaopay.cims.api.inquiry.dto.InquiryDto;
import com.kakaopay.cims.api.inquiry.entity.InquiryEntity;
import com.kakaopay.cims.core.util.EntityMapStruct;

@Mapper
public interface InquiryMapStruct extends EntityMapStruct<InquiryDto, InquiryEntity> {
    InquiryMapStruct INSTANCE = Mappers.getMapper(InquiryMapStruct.class);

    InquiryDto toDto(InquiryEntity productEntity);

    InquiryEntity toEntity(InquiryDto productDto);

}
