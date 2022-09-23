package com.kakaopay.cims.api.inquiry.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RepositoryRestResource
public class InquiryRepositoryImpl implements InquiryRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    
}
