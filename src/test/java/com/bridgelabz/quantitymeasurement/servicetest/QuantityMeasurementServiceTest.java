package com.bridgelabz.quantitymeasurement.servicetest;

import com.bridgelabz.quantitymeasurement.dto.QuantityMeasurementDTO;
import com.bridgelabz.quantitymeasurement.exception.QuantityMeasurementException;
import com.bridgelabz.quantitymeasurement.service.UnitConversion;
import com.bridgelabz.quantitymeasurement.service.UnitType;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class QuantityMeasurementServiceTest {

    QuantityMeasurementDTO quantityMeasurementDTO;
    UnitConversion uUnitConversion = new UnitConversion();
    @Test
    void whenGivenValueAndTwoSameUnit_shouldReturn2Inch() {
        quantityMeasurementDTO = new QuantityMeasurementDTO(2.00, UnitType.FEET, UnitType.GALLON);
        try {
            double convert = uUnitConversion.getConvert(quantityMeasurementDTO);
            Assert.assertEquals(convert,"Invalid Unit Type");
        }catch (QuantityMeasurementException e){
        }
    }

    @Test
    void whenGivenFeetAndFeet_shouldReturnEnterValue() throws QuantityMeasurementException {
        quantityMeasurementDTO = new QuantityMeasurementDTO(1,UnitType.FEET,UnitType.FEET);
        double convert = uUnitConversion.getConvert(quantityMeasurementDTO);
        Assert.assertEquals(convert,1,0);
    }
}
