package com.yanzord.cloud.tollrest.controller;

import com.yanzord.cloud.tollrest.exception.AxlesException;
import com.yanzord.cloud.tollrest.exception.InvalidVehicleException;
import com.yanzord.cloud.tollrest.service.TollService;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

@Service
@ApplicationPath("toll")
public class TollServiceController extends Application {
    private TollService tollService;

    public TollServiceController(TollService tollService) {
        this.tollService = tollService;
    }

    @GET
    @Produces("application/json")
    public Response getTollValuesByVehicle() {
        return Response.ok(tollService.getTollValuesByVehicle()).build();
    }

    @GET
    @Path("/{vehicle}/{value}")
    @Produces("application/json")
    public Response doTollPayment(@PathParam("vehicle") String vehicle, @PathParam("value") Double value) {
        try {
            return Response.ok(tollService.doTollPayment(vehicle, value)).build();
        } catch (InvalidVehicleException | AxlesException e) {
            return Response.status(404).build();
        }
    }

    @GET
    @Path("/{vehicle}/{value}/{axleNumber}")
    @Produces("application/json")
    public Response doTollPayment(@PathParam("vehicle") String vehicle, @PathParam("value") Double value, @PathParam("axleNumber") Integer axleNumber) {
        try {
            return Response.ok(tollService.doTollPayment(vehicle, value, axleNumber)).build();
        } catch (InvalidVehicleException e) {
            return Response.status(404).build();
        }
    }
}
