package com.example.hwthree.vhc.controller;

import com.example.hwthree.vhc.dto.VhcVehicleAddDto;
import com.example.hwthree.vhc.dto.VhcVehicleUpdateDto;
import com.example.hwthree.vhc.service.VhcVehicleService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vehicles")
@RequiredArgsConstructor
public class VhcVehicleController {

    private final VhcVehicleService vhcVehicleService;

    @Operation(
            tags = "Vehicle Controller",
            description = "Adds vehicle"
    )
    @PostMapping
    public ResponseEntity addVehicle(@RequestBody VhcVehicleAddDto vhcVehicleAddDto){
        return ResponseEntity.ok(vhcVehicleService.addVehicle(vhcVehicleAddDto));
    }

    @Operation(
            tags = "Vehicle Controller",
            description = "Deletes vehicle by plate"
    )
    @DeleteMapping("/delete/{plate}")
    public void deleteVehicle(@PathVariable String plate){
        vhcVehicleService.deleteVehicle(plate);
    }

    @Operation(
            tags = "Vehicle Controller",
            description = "Updates vehicle"
    )
    @PutMapping
    public ResponseEntity updateVehicle(@RequestBody VhcVehicleUpdateDto vhcVehicleUpdateDto){
        return ResponseEntity.ok(vhcVehicleService.updateVehicle(vhcVehicleUpdateDto));
    }

    @Operation(
            tags = "Vehicle Controller",
            description = "Gets vehicles by brand and model"
    )
    @GetMapping("/{brand}/{model}")
    public ResponseEntity getVehiclesByBrandAndModel(@PathVariable String brand, @PathVariable String model){
        return ResponseEntity.ok(vhcVehicleService.getVehiclesByBrandAndModel(brand, model));
    }
}
