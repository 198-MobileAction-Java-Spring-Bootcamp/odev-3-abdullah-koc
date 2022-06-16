package com.example.hwthree.jwt.controller;

import com.example.hwthree.jwt.dto.JwtLoginRequestDto;
import com.example.hwthree.jwt.service.AuthenticationService;
import com.example.hwthree.usr.dto.UsrUserDto;
import com.example.hwthree.usr.dto.UsrUserRegisterDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Operation(
            tags = "Authentication Controller",
            description = "Registers user"
    )
    @PostMapping("/register")
    public ResponseEntity save(@Valid @RequestBody UsrUserRegisterDto usrUserRegisterDto){

        UsrUserDto usrUserDto = authenticationService.register(usrUserRegisterDto);

        return ResponseEntity.ok(usrUserDto);
    }

    @Operation(
            tags = "Authentication Controller",
            description = "Logs in user"
    )
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody JwtLoginRequestDto jwtLoginRequestDto){

        String login = authenticationService.login(jwtLoginRequestDto);

        return ResponseEntity.ok(login);
    }
}
