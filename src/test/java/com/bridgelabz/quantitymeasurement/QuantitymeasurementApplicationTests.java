package com.bridgelabz.quantitymeasurement;

import com.bridgelabz.quantitymeasurement.dto.QuantityMeasurementDTO;
import com.bridgelabz.quantitymeasurement.exception.QuantityMeasurementException;
import com.bridgelabz.quantitymeasurement.service.Conversion;
import com.bridgelabz.quantitymeasurement.service.UnitType;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QuantitymeasurementApplicationTests {

    @Mock
    Conversion unitConverter;

    @Test
    public QuantityMeasurementException whenGivenTwoDifferentUnits_shouldThrowsException() {
        QuantityMeasurementDTO quantityMeasurementDTO = new QuantityMeasurementDTO(2, UnitType.INCH,UnitType.LITRES);
        if (quantityMeasurementDTO.firstUnit.unitType.equals(quantityMeasurementDTO.secondUnit)){
            unitConverter.getConvert(quantityMeasurementDTO);
        }
        return new QuantityMeasurementException("Invalid Unit Type");
    }

}
