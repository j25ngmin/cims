package com.kakaopay.cims.api.user.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.kakaopay.cims.api.user.entity.QUserEntity;
import com.kakaopay.cims.core.security.AuthorizedUser;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RepositoryRestResource
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    
    public AuthorizedUser findAuthCustomByUserId(String userId) {
        QUserEntity qUser = new QUserEntity("userEntity");
        
        AuthorizedUser authorizedUser = queryFactory.select(Projections.fields(AuthorizedUser.class
        		, qUser.userId
        		, qUser.userName
        		, qUser.userType
        		, qUser.password
        		, new CaseBuilder()
        		  .when(qUser.userType.eq("001"))
        		  .then(true).otherwise(false).as("isRoleAdmin")
        		))
        		.from(qUser)
        		.where(qUser.userId.eq(userId))
        		.fetchFirst();
        
        return authorizedUser;
    }

}
