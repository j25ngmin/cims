package com.kakaopay.cims.api.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.kakaopay.cims.api.user.dto.UserDto;
import com.kakaopay.cims.api.user.entity.UserEntity;
import com.kakaopay.cims.core.util.EntityMapStruct;

@Mapper
public interface UserMapStruct extends EntityMapStruct<UserDto, UserEntity> {
    UserMapStruct INSTANCE = Mappers.getMapper(UserMapStruct.class);

    @Mapping(target = "password", ignore = true)
    UserDto toDto(UserEntity userEntity);
}
