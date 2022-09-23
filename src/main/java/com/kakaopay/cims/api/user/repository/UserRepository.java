package com.kakaopay.cims.api.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.kakaopay.cims.api.user.entity.UserEntity;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity>, UserRepositoryCustom {

	Optional<UserEntity> findByUserId(String userId);


}
