package com.bridgelabz.quantitymeasurement.servicetest;

import com.bridgelabz.quantitymeasurement.dto.QuantityMeasurementDTO;
import com.bridgelabz.quantitymeasurement.exception.QuantityMeasurementException;
import com.bridgelabz.quantitymeasurement.service.IUnitConversion;
import com.bridgelabz.quantitymeasurement.service.UnitConversion;
import com.bridgelabz.quantitymeasurement.service.UnitType;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureMockMvc
@SpringBootTest
public class QuantityMeasurementServiceTest {

    QuantityMeasurementDTO quantityMeasurementDTO;

    @MockBean
    UnitConversion unitConversion;

    @Autowired
    MockMvc mockMvc;

    Gson gson = new Gson();

    @Test
    void whenGiven0FeetAnd0Feet_shouldReturn0Feet() throws Exception {
        quantityMeasurementDTO = new QuantityMeasurementDTO(2.0, UnitType.FEET, UnitType.FEET);
        String jsonDto = gson.toJson(quantityMeasurementDTO);
        when(unitConversion.getConvert(any())).thenReturn(2.0);
        MvcResult mvcResult = this.mockMvc.perform(post("/unit/convert").content(jsonDto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("2.0",mvcResult.getResponse().getContentAsString());
        Assert.assertEquals(status,200);
    }
}
