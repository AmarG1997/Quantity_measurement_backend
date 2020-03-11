package com.bridgelabz.quantitymeasurement.service;

import com.bridgelabz.quantitymeasurement.dto.QuantityMeasurementDTO;
import com.bridgelabz.quantitymeasurement.exception.QuantityMeasurementException;

import java.util.List;


public interface IUnitConversion {

    double getConvert(QuantityMeasurementDTO quantityMeasurementDTO) throws QuantityMeasurementException;

    List getEnum(String unitType) throws QuantityMeasurementException;

    List getUnit();
}
