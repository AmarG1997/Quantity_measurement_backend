package com.bridgelabz.quantitymeasurement.controller;

import com.bridgelabz.quantitymeasurement.dto.QuantityMeasurementDTO;
import com.bridgelabz.quantitymeasurement.service.Conversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
