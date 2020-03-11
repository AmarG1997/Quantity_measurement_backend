package com.bridgelabz.quantitymeasurement.controller;

import com.bridgelabz.quantitymeasurement.dto.QuantityMeasurementDTO;
import com.bridgelabz.quantitymeasurement.exception.QuantityMeasurementException;
import com.bridgelabz.quantitymeasurement.service.IUnitConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController()
@RequestMapping("/unit")
public class QuantityMeasurementController {

    @Autowired
    IUnitConversion unitConversion;

    @PostMapping("/convert")
    public double getResult(@RequestBody @Valid QuantityMeasurementDTO quantityMeasurementDTO , BindingResult result) throws QuantityMeasurementException {
        if (result.hasErrors()){
            throw new QuantityMeasurementException("Enter A Valid Unit Types");
        }
        double val = unitConversion.getConvert(quantityMeasurementDTO);
        return val;
    }

    @GetMapping("/subUnit/{unit}")
    public List getSubUnitValues(@PathVariable String unit) throws QuantityMeasurementException {
        List anEnum = unitConversion.getEnum(unit);
        return anEnum;
    }

    @GetMapping("/unitType")
    public List getUnit(){
        List unit = unitConversion.getUnit();
        return unit;
    }
}
