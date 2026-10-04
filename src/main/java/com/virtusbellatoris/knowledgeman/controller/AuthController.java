package com.virtusbellatoris.knowledgeman.controller;

import com.virtusbellatoris.knowledgeman.DTO.LoginDTO;
import com.virtusbellatoris.knowledgeman.security.JWTService;
import com.virtusbellatoris.knowledgeman.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth/login")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public String loginMe(@RequestBody LoginDTO loginDTO) {
        return userService.logInMe(loginDTO);
    }



}
