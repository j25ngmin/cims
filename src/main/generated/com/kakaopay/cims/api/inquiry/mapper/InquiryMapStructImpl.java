package com.kakaopay.cims.api.inquiry.mapper;

import com.kakaopay.cims.api.inquiry.dto.InquiryDto;
import com.kakaopay.cims.api.inquiry.entity.InquiryEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-23T22:38:11+0900",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 17.0.4.1 (Eclipse Adoptium)"
)
public class InquiryMapStructImpl implements InquiryMapStruct {

    @Override
    public InquiryDto toDto(InquiryEntity productEntity) {
        if ( productEntity == null ) {
            return null;
        }

        InquiryDto inquiryDto = new InquiryDto();

        inquiryDto.setInquirySeq( productEntity.getInquirySeq() );
        inquiryDto.setInquiryUserId( productEntity.getInquiryUserId() );
        inquiryDto.setTitle( productEntity.getTitle() );
        inquiryDto.setStatus( productEntity.getStatus() );
        inquiryDto.setInquiryContent( productEntity.getInquiryContent() );
        inquiryDto.setInquiryDate( productEntity.getInquiryDate() );
        inquiryDto.setAnswerUserId( productEntity.getAnswerUserId() );
        inquiryDto.setAnswerContent( productEntity.getAnswerContent() );
        inquiryDto.setAnswerDate( productEntity.getAnswerDate() );
        inquiryDto.setCreateUserId( productEntity.getCreateUserId() );
        inquiryDto.setCreateDate( productEntity.getCreateDate() );
        inquiryDto.setModifyUserId( productEntity.getModifyUserId() );
        inquiryDto.setModifyDate( productEntity.getModifyDate() );

        return inquiryDto;
    }

    @Override
    public InquiryEntity toEntity(InquiryDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        InquiryEntity inquiryEntity = new InquiryEntity();

        inquiryEntity.setInquirySeq( productDto.getInquirySeq() );
        inquiryEntity.setInquiryUserId( productDto.getInquiryUserId() );
        inquiryEntity.setTitle( productDto.getTitle() );
        inquiryEntity.setStatus( productDto.getStatus() );
        inquiryEntity.setInquiryContent( productDto.getInquiryContent() );
        inquiryEntity.setInquiryDate( productDto.getInquiryDate() );
        inquiryEntity.setAnswerUserId( productDto.getAnswerUserId() );
        inquiryEntity.setAnswerContent( productDto.getAnswerContent() );
        inquiryEntity.setAnswerDate( productDto.getAnswerDate() );
        inquiryEntity.setCreateUserId( productDto.getCreateUserId() );
        inquiryEntity.setCreateDate( productDto.getCreateDate() );
        inquiryEntity.setModifyUserId( productDto.getModifyUserId() );
        inquiryEntity.setModifyDate( productDto.getModifyDate() );

        return inquiryEntity;
    }
}
