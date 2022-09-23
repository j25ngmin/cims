package com.kakaopay.cims.api.common.code.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.kakaopay.cims.api.common.code.entity.CommonCodeEntity;
import com.kakaopay.cims.api.user.entity.UserEntity;

@Repository
public interface CommonCodeRepository extends JpaRepository<CommonCodeEntity, Integer>, QuerydslPredicateExecutor<UserEntity>, CommonCodeRepositoryCustom {


}
