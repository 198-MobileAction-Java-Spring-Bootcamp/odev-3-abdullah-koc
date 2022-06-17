package com.example.hwthree.jwt.service;

import com.example.hwthree.jwt.dto.JwtLoginRequestDto;
import com.example.hwthree.jwt.enums.JwtConstant;
import com.example.hwthree.jwt.security.JwtTokenGenerator;
import com.example.hwthree.usr.dto.UsrUserChangePasswordDto;
import com.example.hwthree.usr.dto.UsrUserDto;
import com.example.hwthree.usr.dto.UsrUserRegisterDto;
import com.example.hwthree.usr.service.UsrUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UsrUserService usrUserService;
    private final JwtTokenGenerator jwtTokenGenerator;


    /*
     * This method registers the user
     * @param UsrUserRegisterDto
     * @return UsrUserDto
     */
    public UsrUserDto register(UsrUserRegisterDto usrUserRegisterDto) {
        return usrUserService.registerUser(usrUserRegisterDto);
    }

    /*
     * This method logs in the user
     * @param JwtLoginRequestDto
     * @return token
     */
    public String login(JwtLoginRequestDto jwtLoginRequestDto) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                jwtLoginRequestDto.getUsername(), jwtLoginRequestDto.getPassword()
        );

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenGenerator.generateJwtToken(authentication);
        String fullToken = JwtConstant.BEARER.getConstant() + token;
        return fullToken;
    }

}
