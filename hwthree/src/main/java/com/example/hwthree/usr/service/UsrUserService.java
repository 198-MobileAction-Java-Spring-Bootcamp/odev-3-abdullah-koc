package com.example.hwthree.usr.service;

import com.example.hwthree.usr.converter.UsrUserMapper;
import com.example.hwthree.usr.dto.UsrUserChangePasswordDto;
import com.example.hwthree.usr.dto.UsrUserDto;
import com.example.hwthree.usr.dto.UsrUserRegisterDto;
import com.example.hwthree.usr.entity.UsrUser;
import com.example.hwthree.usr.service.entityservice.UsrUserEntityService;
import com.example.hwthree.vhc.converter.VhcVehicleMapper;
import com.example.hwthree.vhc.dto.VhcVehicleDto;
import com.example.hwthree.vhc.entity.VhcVehicle;
import com.example.hwthree.vhc.service.VhcVehicleService;
import com.example.hwthree.vhc.service.entityservice.VhcVehicleEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsrUserService {
    private final UsrUserEntityService usrUserEntityService;
    private final PasswordEncoder passwordEncoder;
    private final VhcVehicleService vhcVehicleService;
    private final VhcVehicleEntityService vhcVehicleEntityService;
    private final VhcVehicleMapper vhcVehicleMapper;

    public UsrUserDto registerUser(UsrUserRegisterDto userRegisterDto) {

        if(usrUserEntityService.findByUsername(userRegisterDto.getUsername()) != null){
            throw new RuntimeException("A user with the same username already exists.");
        }

        UsrUser usrUser = UsrUserMapper.INSTANCE.convertToUsrUser(userRegisterDto);

        String password = passwordEncoder.encode(usrUser.getPassword());
        usrUser.setPassword(password);
        usrUser = usrUserEntityService.save(usrUser);

        return UsrUserMapper.INSTANCE.convertToUsrUserDto(usrUser);
    }

    public UsrUser findByUsername(String username){
        return usrUserEntityService.findByUsername(username);
    }

    public List<VhcVehicleDto> getVehicles(){

        UsrUser usrUser = getUser();
        List<VhcVehicleDto> vhcVehicleDtoList = new ArrayList<>();

        for (VhcVehicle vhcVehicle : vhcVehicleEntityService.findByUsrUser(usrUser)) {
            vhcVehicleDtoList.add(vhcVehicleMapper.convertToVhcVehicleDto(vhcVehicle));
        }

        return vhcVehicleDtoList;
    }


    public void changePassword(UsrUserChangePasswordDto usrUserChangePasswordDto){

        UsrUser usrUser = getUser();
        if(usrUser == null){
            throw new RuntimeException("User not found.");
        }

        if(!passwordEncoder.matches(usrUserChangePasswordDto.getOldPassword(), usrUser.getPassword())){
            throw new RuntimeException("Old password is incorrect.");
        }

        if(!usrUserChangePasswordDto.getNewPassword().equals(usrUserChangePasswordDto.getConfirmPassword())){
            throw new RuntimeException("New password and confirm password do not match.");
        }

        usrUser.setPassword(passwordEncoder.encode(usrUserChangePasswordDto.getNewPassword()));
        usrUserEntityService.save(usrUser);
    }

    public void deleteUser(){

        UsrUser usrUser = getUser();

        if(usrUser == null){
            throw new RuntimeException("User not found.");
        }

        List<VhcVehicle> vehicleList = vhcVehicleEntityService.findByUsrUser(usrUser);

        for (VhcVehicle vhcVehicle : vehicleList) {
            vhcVehicleService.deleteVehicle(vhcVehicle.getPlate());
        }

        usrUserEntityService.delete(usrUser);

    }

    private UsrUser getUser(){
        UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UsrUser usrUser = usrUserEntityService.findByUsername(details.getUsername());
        return usrUser;
    }

}
