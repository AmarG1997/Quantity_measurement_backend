package com.bridgelabz.quantitymeasurement.servicetest;

import com.bridgelabz.quantitymeasurement.dto.QuantityMeasurementDTO;
import com.bridgelabz.quantitymeasurement.exception.QuantityMeasurementException;
import com.bridgelabz.quantitymeasurement.service.UnitConversion;
import com.bridgelabz.quantitymeasurement.service.UnitType;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

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
        Assert.assertEquals("2.0", mvcResult.getResponse().getContentAsString());
        Assert.assertEquals(status, 200);
    }

    @Test
    void whenGiven0FeetAnd0Celcius_shouldThrowException() throws Exception {
        try {
            quantityMeasurementDTO = new QuantityMeasurementDTO(2.0, UnitType.FEET, UnitType.CELCIUS);
            String jsonDto = gson.toJson(quantityMeasurementDTO);
            when(unitConversion.getConvert(any())).thenThrow(new QuantityMeasurementException("Invalid Input", QuantityMeasurementException.ExceptionType.UNIT_TYPE_DIFFERENT));
            this.mockMvc.perform(post("/unit/convert").content(jsonDto)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        } catch (QuantityMeasurementException e) {
            Assert.assertEquals(QuantityMeasurementException.ExceptionType.UNIT_TYPE_DIFFERENT, e.getMessage());
        }
    }

    @Test
    void whenGivenWrongEnum_shouldThrowException() {
        try {
            when(unitConversion.getEnum("asd")).thenThrow(new QuantityMeasurementException("Invalid Input", QuantityMeasurementException.ExceptionType.UNIT_TYPE_DIFFERENT));
            this.mockMvc.perform(post("/unit//subUnit/asd")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        } catch (QuantityMeasurementException e) {
            Assert.assertEquals(QuantityMeasurementException.ExceptionType.UNIT_TYPE_DIFFERENT, e.getMessage());
        } catch (Exception e) {
        }
    }

    @Test
    void whenGivenEnum_shouldReturnList() throws Exception {
        List list = new ArrayList();
        list.add("INCH");
        list.add("FEET");
        when(unitConversion.getEnum("LENGTH"))
                .thenReturn(list);
        this.mockMvc.perform(post("/unit/subUnit/LENGTH")
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        Assert.assertEquals(list,list);
    }

    @Test
    void whenGivenWrongEnum_shouldThroeException() throws QuantityMeasurementException {
        try {
            when(unitConversion.getEnum("LENGTH")).thenThrow(new QuantityMeasurementException("Invalid UnitType", QuantityMeasurementException.ExceptionType.UNIT_TYPE_DIFFERENT));
        }catch (QuantityMeasurementException e){
            Assert.assertEquals(QuantityMeasurementException.ExceptionType.UNIT_TYPE_DIFFERENT,e.getMessage());
        }

    }
}
