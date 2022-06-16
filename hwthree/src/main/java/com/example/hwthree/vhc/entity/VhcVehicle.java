package com.example.hwthree.vhc.entity;


import com.example.hwthree.gen.entity.BaseEntity;
import com.example.hwthree.usr.entity.UsrUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "VHC_VEHICLE")
@Getter
@Setter
public class VhcVehicle extends BaseEntity {

    @Id
    @SequenceGenerator(name = "VhcVehicle", sequenceName = "VHC_VEHICLE_ID_SEQ")
    @GeneratedValue(generator = "VhcVehicle")
    private Long id;

    @Column(name = "BRAND", length = 30, nullable = false)
    private String brand;

    @Column(name = "MODEL", length = 30, nullable = false)
    private String model;

    @Column(name = "YEAR", nullable = false)
    private int year;

    @Column(name = "PLATE", length = 30, nullable = false)
    private String plate;

    @Column(name = "COLOR", length = 30, nullable = false)
    private String color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USR_USER")
    private UsrUser usrUser;
}
