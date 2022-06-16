package com.example.hwthree.vhc.converter;


import com.example.hwthree.usr.service.entityservice.UsrUserEntityService;
import com.example.hwthree.vhc.dto.VhcVehicleDto;
import com.example.hwthree.vhc.dto.VhcVehicleUserAddDto;
import com.example.hwthree.vhc.entity.VhcVehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public abstract class VhcVehicleMapper {

    @Autowired
    UsrUserEntityService usrUserEntityService;

    @Mapping(
            target = "usrUser",
            expression = "java(usrUserEntityService.findByUsername(vhcVehicleUserAddDto.getUsrUserUsername()))"
    )
    public abstract VhcVehicle convertToVhcVehicle(VhcVehicleUserAddDto vhcVehicleUserAddDto);

    public abstract VhcVehicleDto convertToVhcVehicleDto(VhcVehicle vhcVehicle);

}
