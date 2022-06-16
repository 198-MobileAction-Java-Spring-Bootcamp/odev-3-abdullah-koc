package com.example.hwthree.vhc.dto;

import lombok.Data;

@Data
public class VhcVehicleUserAddDto {
    private String brand;
    private String model;
    private int year;
    private String plate;
    private String color;
    private String usrUserUsername;
}
