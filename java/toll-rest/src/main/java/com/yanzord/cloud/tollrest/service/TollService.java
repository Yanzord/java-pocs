package com.yanzord.cloud.tollrest.service;

import com.yanzord.cloud.tollrest.exception.AxlesException;
import com.yanzord.cloud.tollrest.exception.InvalidVehicleException;
import com.yanzord.cloud.tollrest.model.Vehicle;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TollService {

    private Map<String, Vehicle> vehicles;

    public TollService(Map<String, Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public Double doTollPayment(String vehicle, Double value) throws InvalidVehicleException, AxlesException {
        if(vehicle.equals("truck")) {
            throw new AxlesException();
        }

        Optional<Vehicle> vehicleFound = Optional.ofNullable(vehicles.get(vehicle));

        if(!vehicleFound.isPresent()) {
            throw new InvalidVehicleException("Vehicle inserted doesn't exist.");
        }

        return value - vehicleFound.get().getTollValue();
    }

    public Double doTollPayment(String vehicle, Double value, Integer axleNumber) throws InvalidVehicleException {
        if(!vehicle.equals("truck")) {
            throw new InvalidVehicleException("Vehicle must be a truck to have axles.");
        }

        Optional<Vehicle> vehicleFound = Optional.ofNullable(vehicles.get(vehicle));

        return value - (vehicleFound.get().getTollValue() * axleNumber);
    }

    public Map<String, Double> getTollValuesByVehicle() {
        Map<String, Double> tollValueByVehicle = new HashMap<>();

        vehicles.forEach((k, v) -> tollValueByVehicle.put(k, v.getTollValue()));

        return tollValueByVehicle;
    }
}
