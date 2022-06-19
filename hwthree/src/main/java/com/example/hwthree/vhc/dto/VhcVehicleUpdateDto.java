package com.example.hwthree.vhc.dto;

import com.example.hwthree.vhc.enums.EnumVehicleColor;
import lombok.Data;

@Data
public class VhcVehicleUpdateDto {
    private Long id;
    private String brand;
    private String model;
    private int year;
    private String plate;
    private EnumVehicleColor color;
}
