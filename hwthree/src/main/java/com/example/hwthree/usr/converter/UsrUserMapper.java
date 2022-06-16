package com.example.hwthree.usr.converter;

import com.example.hwthree.usr.dto.UsrUserDto;
import com.example.hwthree.usr.dto.UsrUserRegisterDto;
import com.example.hwthree.usr.entity.UsrUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsrUserMapper{

    UsrUserMapper INSTANCE = Mappers.getMapper(UsrUserMapper.class);

    UsrUser convertToUsrUser(UsrUserRegisterDto usrUserRegisterDto);

    UsrUserDto convertToUsrUserDto(UsrUser usrUser);
}
