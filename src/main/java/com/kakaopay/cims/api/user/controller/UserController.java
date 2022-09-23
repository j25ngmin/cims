package com.kakaopay.cims.api.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.kakaopay.cims.api.user.dto.UserDto;
import com.kakaopay.cims.api.user.entity.UserEntity;
import com.kakaopay.cims.api.user.service.UserService;
import com.sipios.springsearch.anotation.SearchSpec;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("${api.prefix.uri}/${api.version}")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Value("${api.version}")
    private String version;

    
    /***
     * 사용자 목록 조회
     * @param keywords
     * @param userNo
     * @return
     */
	@GetMapping("/users")
    public ResponseEntity<Object> getUsers(@SearchSpec Specification<UserEntity> specs, @PageableDefault(size=10) Pageable pageable){

		log.info("specs : " + specs);
		
        Page<UserDto> UserDtos = userService.findAllUsers(specs, pageable);

        if(UserDtos == null || UserDtos.isEmpty()) {
            return new ResponseEntity<Object>(UserDtos, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Object>(UserDtos, HttpStatus.OK);
    }
    
  
    
    /***
     * 사용자 단건 조회
     * @param keywords
     * @param userNo
     * @return
     */
    @GetMapping("/users/{userSeq}")
    //@RequestMapping(value="/users/{userSeq}", method = RequestMethod.GET)
    public ResponseEntity<UserDto> getUserById(@PathVariable("userSeq") Long userSeq) {
        UserDto userDto = userService.findById(userSeq);
        return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
    }
    
    /***
     * 사용자 등록
     * @param keywords
     * @param userNo
     * @return
     */
	@PostMapping("/users")
    public ResponseEntity<Void> saveUser(@RequestBody UserDto userDto, UriComponentsBuilder ucBuilder) {

    	UserDto saveUserDto = userService.saveUser(userDto);
    	HttpHeaders headers = new HttpHeaders();
    	Long userSeq = saveUserDto.getUserSeq();

        headers.setLocation(ucBuilder.path(version + "/users/{userSeq}").buildAndExpand(userSeq).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
    /***
     * 사용자 수정
     * @param keywords
     * @param userNo
     * @return
     */
    @PutMapping("/users/{userSeq}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long userSeq, @RequestBody UserDto userDto) {
    	UserDto updateUserDto = userService.updateUser(userSeq, userDto);
        return new ResponseEntity<UserDto>(updateUserDto, HttpStatus.OK);
    }
    
    /***
     * 사용자 삭제
     * @param keywords
     * @param userNo
     * @return
     */
    @DeleteMapping("/users/{userSeq}")
    public ResponseEntity<Void> deleteUsers(@PathVariable Long userSeq) {
    	userService.deleteUser(userSeq);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
