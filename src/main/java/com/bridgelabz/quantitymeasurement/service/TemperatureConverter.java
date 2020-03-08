package com.bridgelabz.quantitymeasurement.service;

import com.bridgelabz.quantitymeasurement.dto.QuantityMeasurementDTO;

public class TemperatureConverter {

    public double getTempConvert(QuantityMeasurementDTO quantityMeasurementDTO) {
        if (quantityMeasurementDTO.firstUnit.equals(quantityMeasurementDTO.secondUnit)){
            return quantityMeasurementDTO.value1;
        }
        if (quantityMeasurementDTO.firstUnit.equals(UnitType.FARHANHIT)){
            double val = quantityMeasurementDTO.value1 - 32;
            double result = val * 5 / 9;
            return result;
        }
        return ((quantityMeasurementDTO.value1*9/5)+32);
    }
}
