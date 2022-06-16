package com.example.hwthree.vhc.service.entityservice;

import com.example.hwthree.gen.service.BaseEntityService;
import com.example.hwthree.usr.entity.UsrUser;
import com.example.hwthree.vhc.dao.VhcVehicleDao;
import com.example.hwthree.vhc.entity.VhcVehicle;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VhcVehicleEntityService extends BaseEntityService<VhcVehicle, VhcVehicleDao> {
    public VhcVehicleEntityService(VhcVehicleDao dao) {
        super(dao);
    }

    public VhcVehicle findByPlate(String plate){
        return getDao().findByPlate(plate);
    }

    public List<VhcVehicle> findByBrandAndModel(String brand, String model){
        return getDao().findByBrandAndModel(brand, model);
    }

    public List<VhcVehicle> findByUsrUser(UsrUser usrUser){
        return getDao().findByUsrUser(usrUser);
    }
}
