package com.kakaopay.cims.api.user.service;



import org.mapstruct.factory.Mappers;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.kakaopay.cims.api.user.dto.UserDto;
import com.kakaopay.cims.api.user.entity.UserEntity;
import com.kakaopay.cims.api.user.mapper.UserMapStruct;
import com.kakaopay.cims.api.user.repository.UserRepository;
import com.kakaopay.cims.core.security.AuthorizedUser;
import com.kakaopay.cims.core.util.CommonUtil;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private UserMapStruct userMapStruct = Mappers.getMapper(UserMapStruct.class);

    /***
     * 사용자 목록 조회
     * @return
     */
    public Page<UserDto> findAllUsers(Specification<UserEntity> specs, Pageable pageable) {
    	Page<UserEntity> userEntities = userRepository.findAll(Specification.where(specs), pageable);
    	Page<UserDto> userDtos = userEntities.map(userMapStruct::toDto);
        return userDtos;
    }
        
    /***
     * 사용자 단건 조회
     * @return
     */
    public UserDto findById(Long userSeq) {
    	UserEntity checkUserEntity = userRepository.findById(userSeq).orElseThrow(() -> new ResourceNotFoundException());
        UserDto userDto = userMapStruct.toDto(checkUserEntity);
        return userDto;
    }
    
    /***
     * 사용자 아이디 조회
     * @return
     */
    public UserDto findByUserId(String userId) {
    	UserEntity checkUserEntity = userRepository.findByUserId(userId).orElseThrow(() -> new ResourceNotFoundException());
        UserDto userDto = userMapStruct.toDto(checkUserEntity);
        return userDto;
    }
    
    /**
     * 사용자 아이디 조회 (Spring Security)
     * @param userId
     * @return
     */
    public AuthorizedUser findAuthCustomByUserId(String userId) {
    	AuthorizedUser authorizedUser = userRepository.findAuthCustomByUserId(userId);
        return authorizedUser;
    }
    
    /***
     * 사용자 등록
     * @return
     */
    public UserDto saveUser(UserDto userDto) {
    	UserEntity userEntity = userMapStruct.toEntity(userDto);
        UserDto userDtos = userMapStruct.toDto(userRepository.save(userEntity));
        return userDtos;
    }
    
    /***
     * 사용자 수정
     * @return
     */
    public UserDto updateUser(Long userSeq, UserDto userDto) {
    	UserDto updateUserDto = findById(userSeq);
    	BeanUtils.copyProperties(userDto, updateUserDto, CommonUtil.getNullPropertyNames(userDto));
    	UserEntity userEntity = userMapStruct.toEntity(updateUserDto);
    	userRepository.save(userEntity);
        UserDto userDtos = userMapStruct.toDto(userRepository.save(userEntity));
        return userDtos;
    }
    
    /***
     * 사용자 삭제
     * @return
     */
    public void deleteUser(Long userSeq) {
    	UserDto updateUserDto = findById(userSeq);
    	UserEntity userEntity = userMapStruct.toEntity(updateUserDto);
    	userRepository.delete(userEntity);
    }

}
