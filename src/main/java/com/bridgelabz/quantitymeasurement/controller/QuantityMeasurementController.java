package com.bridgelabz.quantitymeasurement.controller;

import com.bridgelabz.quantitymeasurement.dto.QuantityMeasurementDTO;
import com.bridgelabz.quantitymeasurement.exception.QuantityMeasurementException;
import com.bridgelabz.quantitymeasurement.service.Conversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class QuantityMeasurementController {

    @Autowired
    Conversion conversion;

    @PostMapping("/unitConverter")
    public double getResult(@RequestBody @Valid QuantityMeasurementDTO quantityMeasurementDTO , BindingResult result) throws QuantityMeasurementException {
        if (result.hasErrors()){
            throw new QuantityMeasurementException("Enter A Valid Unit Types");
        }
        double val = conversion.getConvert(quantityMeasurementDTO);
        return val;
    }

    @GetMapping("{unit}")
    public List getSubUnitValues(@PathVariable String unit){
        List anEnum = conversion.getEnum(unit);
        return anEnum;
    }

    @GetMapping("/getUnit")
    public List getUnit(){
        List unit = conversion.getUnit();
        return unit;
    }
}
