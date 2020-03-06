package com.bridgelabz.quantitymeasurement.service;

import com.bridgelabz.quantitymeasurement.dto.QuantityMeasurementDTO;
import org.springframework.stereotype.Service;

@Service
public class Conversion {

    public double getConvert(QuantityMeasurementDTO quantityMeasurementDTO) {
        return quantityMeasurementDTO.value1 * quantityMeasurementDTO.firstUnit.val / quantityMeasurementDTO.secondUnit.val;
    }
}
