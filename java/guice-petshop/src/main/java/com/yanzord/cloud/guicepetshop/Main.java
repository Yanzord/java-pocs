package com.yanzord.cloud.guicepetshop;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.yanzord.cloud.guicepetshop.config.PetstoreAppModule;
import com.yanzord.cloud.guicepetshop.domain.Pet;
import com.yanzord.cloud.guicepetshop.exception.InvalidJobException;
import com.yanzord.cloud.guicepetshop.exception.InvalidPetException;
import com.yanzord.cloud.guicepetshop.service.PetstoreService;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Main.class.getName());

        Injector injector = Guice.createInjector(new PetstoreAppModule());
        PetstoreService petstore = injector.getInstance(PetstoreService.class);

        Pet sosa = new Pet("Sosa", "Dachshund", 2);
        Pet espeto = new Pet("Espeto", "Vira-lata", 2);

        if (petstore.registerPet(sosa))
            System.out.println("Pet registered successfully: " + sosa);

        petstore.registerPet(espeto);

        Optional<List<Pet>> petsByAge = Optional.of(petstore.getPetsByAge(2));
        petsByAge.ifPresent(System.out::println);

        try {
            System.out.println(petstore.doJob(sosa.getId(), "bath"));
            System.out.println(petstore.doJob(espeto.getId(), "short haircut"));

            System.out.println(petstore.doJob(sosa.getId(), "long bath"));

        } catch (InvalidPetException | InvalidJobException e) {
            logger.info(e.getMessage());
        }

        petstore.getTop10PetsRevenue().forEach((k, v) -> {
            System.out.println(k + "\nRevenue: " + v + "\n\n");
        });

        if(petstore.removePet(espeto.getId())) {
            System.out.println("Pet removed successfully");
        }
    }
}
