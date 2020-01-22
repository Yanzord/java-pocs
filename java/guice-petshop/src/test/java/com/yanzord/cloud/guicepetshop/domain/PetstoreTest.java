package com.yanzord.cloud.guicepetshop.domain;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.yanzord.cloud.guicepetshop.config.PetstoreAppModule;
import com.yanzord.cloud.guicepetshop.exception.InvalidJobException;
import com.yanzord.cloud.guicepetshop.exception.InvalidPetException;
import com.yanzord.cloud.guicepetshop.service.PetstoreService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class PetstoreTest {

    private Injector injector = Guice.createInjector(new PetstoreAppModule());
    private PetstoreService petstore = injector.getInstance(PetstoreService.class);

    @Test
    public void shouldRegisterPetTest() {
        Pet bar = new Pet("RegisterBarTest", "Foo", 1);

        Boolean expectedReturn = petstore.registerPet(bar);

        assertTrue(expectedReturn);
    }

    @Test
    public void shouldGetPetsByAgeTest() {
        Pet bar = new Pet("BarAgeTest", "Foo", 0);
        petstore.registerPet(bar);

        List<Pet> pets = petstore.getPetsByAge(0);

        Pet actualPet = pets.get(0);
        String expectedPetName = "BarAgeTest";
        Integer expectedPetAge = 0;

        assertEquals(expectedPetName, actualPet.getName());
        assertEquals(expectedPetAge, actualPet.getAge());
    }

    @Test
    public void shouldDoBathTest() throws InvalidPetException, InvalidJobException {
        Pet bar = new Pet("BarBathTest", "Foo", 2);
        petstore.registerPet(bar);

        String actual = petstore.doJob(bar.getId(), "bath");
        String expected = "Giving bath: BarBathTest";

        assertEquals(expected, actual);
    }

    @Test
    public void shouldDoBathWithPerfumeTest() throws InvalidPetException, InvalidJobException {
        Pet bar = new Pet("BarBathWithPerfumeTest", "Foo", 3);
        petstore.registerPet(bar);

        String actual = petstore.doJob(bar.getId(), "bath with perfume");
        String expected = "Giving bath with perfume: BarBathWithPerfumeTest";

        assertEquals(expected, actual);
    }

    @Test
    public void shouldDoDryBathTest() throws InvalidPetException, InvalidJobException {
        Pet bar = new Pet("BarDryBathTest", "Foo", 4);
        petstore.registerPet(bar);

        String actual = petstore.doJob(bar.getId(), "dry bath");
        String expected = "Giving dry bath: BarDryBathTest";

        assertEquals(expected, actual);
    }

    @Test
    public void shouldDoDryBathWithPerfumeTest() throws InvalidPetException, InvalidJobException {
        Pet bar = new Pet("BarDryBathWithPerfumeTest", "Foo", 5);
        petstore.registerPet(bar);

        String actual = petstore.doJob(bar.getId(), "dry bath with perfume");
        String expected = "Giving dry bath with perfume: BarDryBathWithPerfumeTest";

        assertEquals(expected, actual);
    }

    @Test
    public void shouldDoShortHaircutTest() throws InvalidPetException, InvalidJobException {
        Pet bar = new Pet("BarShortHaircutTest", "Foo", 6);
        petstore.registerPet(bar);

        String actual = petstore.doJob(bar.getId(), "short haircut");
        String expected = "Cutting short haircut: BarShortHaircutTest";

        assertEquals(expected, actual);
    }

    @Test
    public void shouldDoLongHaircutTest() throws InvalidPetException, InvalidJobException {
        Pet bar = new Pet("BarLongHaircutTest", "Foo", 7);
        petstore.registerPet(bar);

        String actual = petstore.doJob(bar.getId(), "long haircut");
        String expected = "Cutting long haircut: BarLongHaircutTest";

        assertEquals(expected, actual);
    }

    @Test(expected = InvalidPetException.class)
    public void shouldNotDoJobWhenPetIdIsInvalid() throws InvalidPetException, InvalidJobException {
        petstore.doJob("1", "long haircut");
    }

    @Test(expected = InvalidJobException.class)
    public void shouldNotDoJobWhenJobIsInvalid() throws InvalidPetException, InvalidJobException {
        Pet bar = new Pet("BarInvalidJobTest", "Foo", 8);
        petstore.registerPet(bar);

        String nonexistentJob = "just bath with haircut";

        petstore.doJob(bar.getId(), nonexistentJob);
    }

    @Test
    public void shouldRemovePetTest() {
        Pet bar = new Pet("BarRemovePetTest", "Foo", 9);
        petstore.registerPet(bar);

        Boolean expectedReturn = petstore.removePet(bar.getId());

        assertTrue(expectedReturn);
    }

    @Test
    public void shouldReturnTop10PetsRevenue() throws InvalidPetException, InvalidJobException {
        Pet foo = new Pet("FooTop10Revenue", "Bar", 10);
        Pet bar = new Pet("BarTop10Revenue", "Foo", 11);

        petstore.registerPet(foo);
        petstore.registerPet(bar);

        petstore.doJob(foo.getId(), "dry bath with perfume");
        petstore.doJob(foo.getId(), "dry bath with perfume");
        petstore.doJob(bar.getId(), "dry bath with perfume");
        petstore.doJob(bar.getId(), "dry bath with perfume");
        petstore.doJob(bar.getId(), "dry bath with perfume");

        Map<Pet, Double> expectedTop10PetsRevenue = petstore.getTop10PetsRevenue();
        List<Double> values = new ArrayList<>(expectedTop10PetsRevenue.values());

        Double firstPlaceRevenueValue = values.get(0);
        Double secondPlaceRevenueValue = values.get(1);

        Double expectedFooRevenueValue = 70.0;
        Double actualFooRevenueValue = expectedTop10PetsRevenue.get(foo);

        Double expectedBarRevenueValue = 105.0;
        Double actualBarRevenueValue = expectedTop10PetsRevenue.get(bar);

        assertTrue(firstPlaceRevenueValue > secondPlaceRevenueValue);
        assertEquals(expectedFooRevenueValue, actualFooRevenueValue);
        assertEquals(expectedBarRevenueValue, actualBarRevenueValue);
    }
}
