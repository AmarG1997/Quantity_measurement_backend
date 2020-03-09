package com.bridgelabz.quantitymeasurement.service;

import com.bridgelabz.quantitymeasurement.dto.QuantityMeasurementDTO;
import com.bridgelabz.quantitymeasurement.exception.QuantityMeasurementException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UnitConversion implements IUnitConversion {

    @Override
    public double getConvert(QuantityMeasurementDTO quantityMeasurementDTO) throws QuantityMeasurementException {
        if ((quantityMeasurementDTO.firstUnit.equals(UnitType.FARHANHIT)
                || quantityMeasurementDTO.firstUnit.equals(UnitType.CELCIUS)) && quantityMeasurementDTO.firstUnit.unitType.equals(quantityMeasurementDTO.secondUnit.unitType)) {
            return new TemperatureConverter().getTempConvert(quantityMeasurementDTO);
        }
        if (quantityMeasurementDTO.firstUnit.unitType.equals(quantityMeasurementDTO.secondUnit.unitType)) {
            return quantityMeasurementDTO.value1 * quantityMeasurementDTO.firstUnit.val / quantityMeasurementDTO.secondUnit.val;
        }
        throw new QuantityMeasurementException("Invalid Unit Type");
    }

    @Override
    public List getEnum(String unitType) {
        List<UnitType> collect = Arrays.stream(UnitType.values())
                .filter(enumValues -> enumValues.unitType.equals(unitType))
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public List getUnit() {
        List<String> unit = Arrays.stream(UnitType.values())
                .map(listOfUnit -> listOfUnit.unitType)
                .distinct()
                .collect(Collectors.toList());

        return unit;
    }
}
