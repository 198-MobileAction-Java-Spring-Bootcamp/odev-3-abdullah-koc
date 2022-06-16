package com.example.hwthree.vhc.dto;

import lombok.Data;

@Data
public class VhcVehicleDto {
    private Long id;
    private String brand;
    private String model;
    private int year;
    private String plate;
    private String color;
}
