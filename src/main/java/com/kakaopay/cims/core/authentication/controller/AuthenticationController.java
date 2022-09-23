package com.kakaopay.cims.core.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kakaopay.cims.core.authentication.service.AuthenticationService;
import com.kakaopay.cims.core.security.AuthorizedUser;

@RestController
@RequestMapping("${api.version}")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@Validated @RequestBody AuthorizedUser authorizedEmployee) {
        String token = authenticationService.login(authorizedEmployee.getUserId(), authorizedEmployee.getPassword());
        // TODO ACcessToken, RefreshToken, ExpireDate
        return new ResponseEntity<String>(token, HttpStatus.OK);
    }

  /*  @RequestMapping(value = "/auth/logout", method = RequestMethod.GET)
    public ResponseEntity<Void> logOut(@RequestParam("adId") String adId, @RequestParam("password") String password) {
        return new ResponseEntity<Void>(HttpStatus.OK);
    }*/

}