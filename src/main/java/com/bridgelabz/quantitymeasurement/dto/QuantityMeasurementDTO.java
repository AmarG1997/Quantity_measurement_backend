package com.bridgelabz.quantitymeasurement.dto;

import com.bridgelabz.quantitymeasurement.service.UnitType;

import javax.validation.constraints.NotNull;


public class QuantityMeasurementDTO {

    @NotNull
    public double value1;

    @NotNull
    public UnitType firstUnit;

    @NotNull
    public UnitType secondUnit;

    public QuantityMeasurementDTO(double value1, UnitType firstUnit, UnitType secondUnit) {
        this.value1 = value1;
        this.firstUnit = firstUnit;
        this.secondUnit = secondUnit;
    }
}
