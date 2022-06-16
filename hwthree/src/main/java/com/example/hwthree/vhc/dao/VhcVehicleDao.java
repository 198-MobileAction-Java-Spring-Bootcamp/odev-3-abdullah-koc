package com.example.hwthree.vhc.dao;

import com.example.hwthree.usr.entity.UsrUser;
import com.example.hwthree.vhc.entity.VhcVehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VhcVehicleDao extends JpaRepository<VhcVehicle, Long> {

    VhcVehicle findByPlate(String plate);

    List<VhcVehicle> findByBrandAndModel(String brand, String model);

    List<VhcVehicle> findByUsrUser(UsrUser usrUser);
}
