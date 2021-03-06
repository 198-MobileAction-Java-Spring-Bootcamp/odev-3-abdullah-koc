package com.example.hwthree.vhc.service;

import com.example.hwthree.usr.entity.UsrUser;
import com.example.hwthree.vhc.converter.VhcVehicleMapper;
import com.example.hwthree.vhc.dto.*;
import com.example.hwthree.vhc.entity.VhcVehicle;
import com.example.hwthree.vhc.service.entityservice.VhcVehicleEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class VhcVehicleService {

    private final VhcVehicleEntityService vhcVehicleEntityService;
    private final VhcVehicleMapper vhcVehicleMapper;

    /*
     * This method adds vehicle to the user
     * @param vhcVehicleAddDto - vhcVehicleAddDto object
     * @return - vhcVehicleDto object
     */
    public VhcVehicleDto addVehicle(VhcVehicleAddDto vhcVehicleAddDto) {

        VhcVehicleUserAddDto vhcVehicleUserAddDto = new VhcVehicleUserAddDto();
        vhcVehicleUserAddDto.setBrand(vhcVehicleAddDto.getBrand());
        vhcVehicleUserAddDto.setModel(vhcVehicleAddDto.getModel());
        vhcVehicleUserAddDto.setYear(vhcVehicleAddDto.getYear());
        vhcVehicleUserAddDto.setPlate(vhcVehicleAddDto.getPlate());
        vhcVehicleUserAddDto.setColor(vhcVehicleAddDto.getColor());
        vhcVehicleUserAddDto.setUsrUserUsername(getUsername());

        VhcVehicle vhcVehicle = vhcVehicleMapper.convertToVhcVehicle(vhcVehicleUserAddDto);
        if (vhcVehicle.getUsrUser().getUsername() == null) {
            throw new RuntimeException("This user does not exist");
        }

        //plate cannot contain spaces or turkish characters
        if (vhcVehicle.getPlate().contains(" ") || vhcVehicle.getPlate().contains("ı") || vhcVehicle.getPlate().contains("ğ") || vhcVehicle.getPlate().contains("ü") || vhcVehicle.getPlate().contains("ş") || vhcVehicle.getPlate().contains("ö") || vhcVehicle.getPlate().contains("ç")) {
            throw new IllegalArgumentException("Plate cannot contain spaces or turkish characters");
        }

        //plate can contain only uppercase letters and numbers
        if (!vhcVehicle.getPlate().matches("[A-Z0-9]+")) {
            throw new IllegalArgumentException("Plate can contain only uppercase letters and numbers");
        }

        if (vhcVehicleEntityService.findByPlate(vhcVehicle.getPlate()) != null) {
            throw new RuntimeException("This vehicle already exists.");
        }

        vhcVehicle = vhcVehicleEntityService.save(vhcVehicle);

        return vhcVehicleMapper.convertToVhcVehicleDto(vhcVehicle);
    }

    /*
     * This method deletes vehicle from the user
     * @param plate - plate of the vehicle
     */
    public void deleteVehicle(String plate) {
        VhcVehicle vhcVehicle = vhcVehicleEntityService.findByPlate(plate);
        if (vhcVehicle == null) {
            throw new RuntimeException("This vehicle does not exist.");
        }
        vhcVehicleEntityService.delete(vhcVehicle);
    }

    /*
     * This method updates vehicle
     * @param vhcVehicleUpdateDto - vhcVehicleUpdateDto object
     * @return - vhcVehicleDto object
     */
    public VhcVehicleDto updateVehicle(VhcVehicleUpdateDto vhcVehicleUpdateDto) {
        VhcVehicle vhcVehicle = vhcVehicleEntityService.findById(vhcVehicleUpdateDto.getId()).orElseThrow();
        if (vhcVehicle == null) {
            throw new RuntimeException("This vehicle does not exist.");
        }

        VhcVehicle vhcVehicle1 = vhcVehicleEntityService.findByPlate(vhcVehicleUpdateDto.getPlate());

        if(vhcVehicle1 != null && !vhcVehicle1.getId().equals(vhcVehicle.getId())) {
            throw new RuntimeException("This vehicle already exists.");
        }

        vhcVehicle.setBrand(vhcVehicleUpdateDto.getBrand());
        vhcVehicle.setModel(vhcVehicleUpdateDto.getModel());
        vhcVehicle.setYear(vhcVehicleUpdateDto.getYear());
        vhcVehicle.setPlate(vhcVehicleUpdateDto.getPlate());
        vhcVehicle.setColor(vhcVehicleUpdateDto.getColor());

        vhcVehicle = vhcVehicleEntityService.save(vhcVehicle);
        return vhcVehicleMapper.convertToVhcVehicleDto(vhcVehicle);
    }

    /*
     * This method returns all vehicles by brand and model
     * @param brand - brand of the vehicle
     * @param model - model of the vehicle
     * @return - list of vhcVehicleDto objects
     */
    public List<VhcVehicleDto> getVehiclesByBrandAndModel(String brand, String model) {

        List<VhcVehicle> vhcVehicles = vhcVehicleEntityService.findByBrandAndModel(brand, model);
        List<VhcVehicleDto> vhcVehicleDtoList = new ArrayList<>();

        for (VhcVehicle vhcVehicle : vhcVehicles) {
            vhcVehicleDtoList.add(vhcVehicleMapper.convertToVhcVehicleDto(vhcVehicle));
        }
        return vhcVehicleDtoList;
    }


    /*
     * This method gets the username by login credentials
     */
    private String getUsername() {
        UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return details.getUsername();
    }

}
