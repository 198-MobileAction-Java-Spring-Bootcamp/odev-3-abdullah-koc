package com.example.hwthree.jwt.security;

import com.example.hwthree.usr.entity.UsrUser;
import com.example.hwthree.usr.service.entityservice.UsrUserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UsrUserEntityService usrUserEntityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UsrUser usrUser = usrUserEntityService.findByUsername(username);
        return JwtUserDetails.create(usrUser);
    }

    public UserDetails loadUserById(Long id) throws UsernameNotFoundException {

        UsrUser usrUser = usrUserEntityService.findById(id).orElseThrow();
        return JwtUserDetails.create(usrUser);
    }

}
