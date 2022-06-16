package com.example.hwthree.usr.controller;

import com.example.hwthree.usr.dto.UsrUserChangePasswordDto;
import com.example.hwthree.usr.service.UsrUserService;
import com.example.hwthree.vhc.dto.VhcVehicleDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UsrUserController {

    private final UsrUserService usrUserService;

    @Operation(
            tags = "User Controller",
            description = "Changes password of user"
    )
    @PutMapping("/change_password")
    public void changePassword(@RequestBody UsrUserChangePasswordDto usrUserChangePasswordDto){
        usrUserService.changePassword(usrUserChangePasswordDto);
    }

    @Operation(
            tags = "User Controller",
            description = "Deletes user by username"
    )
    @DeleteMapping
    public void deleteUser(){
        usrUserService.deleteUser();
    }

    @Operation(
            tags = "User Controller",
            description = "Gets vehicles of a user"
    )
    @GetMapping("/vehicles/")
    public ResponseEntity<List<VhcVehicleDto>> getVehiclesOfUser(){
        return ResponseEntity.ok(usrUserService.getVehicles());
    }
}
