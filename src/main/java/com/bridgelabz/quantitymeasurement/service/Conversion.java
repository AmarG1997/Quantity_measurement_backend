package com.bridgelabz.quantitymeasurement.service;

import com.bridgelabz.quantitymeasurement.dto.QuantityMeasurementDTO;
import com.bridgelabz.quantitymeasurement.exception.QuantityMeasurementException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Conversion {

    public double getConvert(QuantityMeasurementDTO quantityMeasurementDTO) {
        if (quantityMeasurementDTO.firstUnit.equals(UnitType.FARHANHIT)
                || quantityMeasurementDTO.firstUnit.equals(UnitType.CELCIUS)) {
            return new TemperatureConverter().getTempConvert(quantityMeasurementDTO);
        }
        return quantityMeasurementDTO.value1 * quantityMeasurementDTO.firstUnit.val / quantityMeasurementDTO.secondUnit.val;
    }

    public List getEnum(String unitType) {
        List<UnitType> collect = Arrays.stream(UnitType.values())
                .filter(enumValues -> enumValues.unitType.equals(unitType))
                .collect(Collectors.toList());
        return collect;
    }

    public List getUnit() {
        List<String> unit = Arrays.stream(UnitType.values())
                .map(listOfUnit -> listOfUnit.unitType)
                .distinct()
                .collect(Collectors.toList());

        return unit;
    }

}