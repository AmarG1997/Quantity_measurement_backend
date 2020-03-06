package com.bridgelabz.quantitymeasurement.servicetest;

import com.bridgelabz.quantitymeasurement.dto.QuantityMeasurementDTO;
import com.bridgelabz.quantitymeasurement.service.Conversion;
import com.bridgelabz.quantitymeasurement.service.UnitType;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class QuantityMeasurementServiceTest {

    @Mock
    Conversion unitConverter;

    QuantityMeasurementDTO quantityMeasurementDTO;

    @Test
    void whenGivenValueAndTwoSameUnit_shouldReturn2Inch() {
        quantityMeasurementDTO = new QuantityMeasurementDTO(2.00, UnitType.FEET, UnitType.INCH);
        when(unitConverter.getConvert(any())).thenReturn(2.0);
    }
}
