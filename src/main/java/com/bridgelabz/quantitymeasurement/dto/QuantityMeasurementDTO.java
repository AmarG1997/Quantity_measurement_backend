package com.bridgelabz.quantitymeasurement.dto;

import com.bridgelabz.quantitymeasurement.service.UnitType;

import javax.validation.constraints.NotNull;

public class QuantityMeasurementDTO {

    public double value1;
    @NotNull(message = "Enter UnitType")
    public UnitType firstUnit;
    @NotNull(message = "Enter UnitType")
    public UnitType secondUnit;

    public QuantityMeasurementDTO(double value1, UnitType firstUnit, UnitType secondUnit) {
        this.value1 = value1;
        this.firstUnit = firstUnit;
        this.secondUnit = secondUnit;
    }
}
