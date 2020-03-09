package com.bridgelabz.quantitymeasurement.controllertest;

import com.bridgelabz.quantitymeasurement.dto.QuantityMeasurementDTO;
import com.bridgelabz.quantitymeasurement.service.UnitConversion;
import com.bridgelabz.quantitymeasurement.service.UnitType;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class QuantityMeasurementControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Mock
    UnitConversion unitConverter;

    QuantityMeasurementDTO quantityMeasurementDTO;
    Gson gson = new Gson();

    @Test
    void givenARequest_thenReturnOkRequestStatus() throws Exception {
        quantityMeasurementDTO = new QuantityMeasurementDTO(2.00, UnitType.CM, UnitType.CM);
        String jsonDto = gson.toJson(quantityMeasurementDTO);
        MvcResult mvcResult = this.mockMvc.perform(post("/unit/convert").content(jsonDto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
    }

    @Test
    void givenARequest_thenReturnBadRequestStatus() throws Exception {
        quantityMeasurementDTO = new QuantityMeasurementDTO(2.00, null, UnitType.CM);
        when(unitConverter.getConvert(quantityMeasurementDTO)).thenReturn(2.0);
        MvcResult mvcResult = this.mockMvc.perform(post("/unit/convert").content(quantityMeasurementDTO.toString())
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(400, status);
    }

    @Test
    void whenGivenDifferentUnitType_shouldReturnException() throws Exception {
        quantityMeasurementDTO=new QuantityMeasurementDTO(2.0,UnitType.INCH,UnitType.FARHANHIT);
        String jsonDto = gson.toJson(quantityMeasurementDTO);
        when(unitConverter.getConvert(quantityMeasurementDTO)).thenReturn(2.0);
        MvcResult mvcResult = this.mockMvc.perform(post("/unit/convert").content(jsonDto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(400,status);
    }

    @Test
    void givenARequest_thenReturnOkRequestStatusCode() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/unit/unitType")
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
    }

    @Test
    void givenAWrongTypeRequest_thenReturnBadStatusCode() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(post("/unit/unitType")
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(405, status);
    }

    @Test
    void givenARequest_thenReturnOkStatusCode() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/unit/subUnit/LENGTH")
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
    }

    @Test
    void givenABadUrl_thenReturnBadRequestCode() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(404, status);
    }
}
