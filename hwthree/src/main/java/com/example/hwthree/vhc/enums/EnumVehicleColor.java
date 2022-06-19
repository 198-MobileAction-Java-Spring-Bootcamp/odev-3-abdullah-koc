package com.example.hwthree.vhc.enums;

public enum EnumVehicleColor {

    RED ("Red"),
    GREEN ("Green"),
    BLUE ("Blue"),
    YELLOW ("Yellow"),
    BLACK ("Black"),
    WHITE ("White"),
    GREY ("Grey"),
    PINK ("Pink"),
    PURPLE ("Purple"),
    ORANGE ("Orange"),
    BROWN ("Brown"),
    UNKNOWN ("Unknown");


    private String color;

    EnumVehicleColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color;
    }

}
