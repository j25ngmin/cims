package com.kakaopay.cims.api.inquiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.kakaopay.cims.api.inquiry.entity.InquiryEntity;

@RepositoryRestResource
public interface InquiryRepository extends JpaRepository<InquiryEntity, Long>, JpaSpecificationExecutor<InquiryEntity>, InquiryRepositoryCustom {


}
