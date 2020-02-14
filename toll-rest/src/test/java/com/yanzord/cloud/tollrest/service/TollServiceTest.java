package com.yanzord.cloud.tollrest.service;

import com.yanzord.cloud.tollrest.config.AppConfig;
import com.yanzord.cloud.tollrest.exception.AxlesException;
import com.yanzord.cloud.tollrest.exception.InvalidVehicleException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class TollServiceTest {
    @Autowired
    TollService tollService;

    private static final Double DELTA = 0.1;

    @Test
    public void shouldChargeBusTollTest() throws InvalidVehicleException, AxlesException {
        Double expected = 0.41;
        Double actual = tollService.doTollPayment("bus", 2d);

        assertEquals(expected, actual, DELTA);
    }

    @Test
    public void shouldChargeBikeTollTest() throws InvalidVehicleException, AxlesException {
        Double expected = 0.51;
        Double actual = tollService.doTollPayment("bike", 1d);

        assertEquals(expected, actual, DELTA);
    }

    @Test
    public void shouldChargeMotorcycleTollTest() throws InvalidVehicleException, AxlesException {
        Double expected = 1d;
        Double actual = tollService.doTollPayment("motorcycle", 2d);

        assertEquals(expected, actual, DELTA);
    }

    @Test
    public void shouldChargeBeetleTollTest() throws InvalidVehicleException, AxlesException {
        Double expected = 0.89;
        Double actual = tollService.doTollPayment("beetle", 3d);

        assertEquals(expected, actual, DELTA);
    }

    @Test(expected = AxlesException.class)
    public void shouldNotChargeTruckWithNoAxlesTollTest() throws InvalidVehicleException, AxlesException {
        tollService.doTollPayment("truck", 10d);
    }

    @Test
    public void shouldChargeTruckWithAxlesTollTest() throws InvalidVehicleException {
        Double expected = 2.1;
        Double actual = tollService.doTollPayment("truck", 10d, 2);

        assertEquals(expected, actual, DELTA);
    }

    @Test(expected = InvalidVehicleException.class)
    public void shouldNotChargeTollWhenVehicleDoesntExistTest() throws InvalidVehicleException, AxlesException {
        tollService.doTollPayment("test", 1d);
    }

    @Test(expected = InvalidVehicleException.class)
    public void shouldNotChargeTollWithAxleWhenVehicleIsntTruckTest() throws InvalidVehicleException {
        tollService.doTollPayment("motorcycle", 1d, 2);
    }
}
