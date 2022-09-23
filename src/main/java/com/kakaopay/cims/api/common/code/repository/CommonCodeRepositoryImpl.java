package com.kakaopay.cims.api.common.code.repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class CommonCodeRepositoryImpl extends QuerydslRepositorySupport implements CommonCodeRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public CommonCodeRepositoryImpl() {
        super(CommonCodeRepositoryImpl.class);
    }

    @Override
    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        super.setEntityManager(entityManager);
    }

}
