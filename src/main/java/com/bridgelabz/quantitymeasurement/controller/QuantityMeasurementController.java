package com.bridgelabz.quantitymeasurement.controller;

import com.bridgelabz.quantitymeasurement.dto.QuantityMeasurementDTO;
import com.bridgelabz.quantitymeasurement.service.Conversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class QuantityMeasurementController {

    @Autowired
    Conversion conversion;

    @PostMapping("/unitConverter")
    public double getResult(@RequestBody QuantityMeasurementDTO quantityMeasurementDTO) {
        double val = conversion.getConvert(quantityMeasurementDTO);
        return val;
    }

    @GetMapping("{unit}")
    public List getUnitValues(@PathVariable String unit){
        System.out.println("in backend-->"+unit);
        List anEnum = conversion.getEnum(unit);
        System.out.println("in database "+anEnum);
        return anEnum;
    }

    @GetMapping("/getUnit")
    public List getUnit(){
        List unit = conversion.getUnit();
        System.out.println("REsponse from backend"+unit);
        return unit;
    }
}
