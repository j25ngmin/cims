package com.kakaopay.cims.api.user.mapper;

import com.kakaopay.cims.api.user.dto.UserDto;
import com.kakaopay.cims.api.user.entity.UserEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-23T22:38:11+0900",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 17.0.4.1 (Eclipse Adoptium)"
)
public class UserMapStructImpl implements UserMapStruct {

    @Override
    public UserEntity toEntity(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setUserSeq( dto.getUserSeq() );
        userEntity.setUserId( dto.getUserId() );
        userEntity.setUserName( dto.getUserName() );
        userEntity.setPassword( dto.getPassword() );
        userEntity.setUserType( dto.getUserType() );
        userEntity.setCreateUserId( dto.getCreateUserId() );
        userEntity.setCreateDate( dto.getCreateDate() );
        userEntity.setModifyUserId( dto.getModifyUserId() );
        userEntity.setModifyDate( dto.getModifyDate() );

        return userEntity;
    }

    @Override
    public UserDto toDto(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setUserSeq( userEntity.getUserSeq() );
        userDto.setUserId( userEntity.getUserId() );
        userDto.setUserName( userEntity.getUserName() );
        userDto.setUserType( userEntity.getUserType() );
        userDto.setCreateUserId( userEntity.getCreateUserId() );
        userDto.setCreateDate( userEntity.getCreateDate() );
        userDto.setModifyUserId( userEntity.getModifyUserId() );
        userDto.setModifyDate( userEntity.getModifyDate() );

        return userDto;
    }
}
