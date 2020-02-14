package com.yanzord.cloud.guicepetshop.service;

import com.yanzord.cloud.guicepetshop.domain.Pet;
import com.yanzord.cloud.guicepetshop.domain.job.PetstoreJob;
import com.yanzord.cloud.guicepetshop.exception.InvalidJobException;
import com.yanzord.cloud.guicepetshop.exception.InvalidPetException;

import javax.inject.Inject;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class PetstoreService {

    private Map<String, PetstoreJob> petstoreServices;
    private List<Pet> registeredPets;
    private Map<Pet, List<PetstoreJob>> petstoreJobHistory;

    @Inject
    public PetstoreService(Map<String, PetstoreJob> petstoreServices) {
        this.petstoreServices = petstoreServices;
        this.registeredPets = new ArrayList<>();
        this.petstoreJobHistory = new HashMap<>();
    }

    public Boolean registerPet(Pet pet) {
        registeredPets.add(pet);
        return true;
    }

    public Boolean removePet(String id) {
        registeredPets.removeIf(pet -> pet.getId().equals(id));
        return true;
    }

    public String doJob(String id, String requestedJob) throws InvalidPetException, InvalidJobException {
        Optional<Pet> petFound = registeredPets.stream()
                .filter(pet -> pet.getId().equals(id))
                .findAny();

        if(!petFound.isPresent()) {
            throw new InvalidPetException();
        }

        PetstoreJob job = petstoreServices.get(requestedJob);

        if(job == null) {
            throw new InvalidJobException();
        }

        add(petFound.get(), job);

        return job.execute(petFound.get());
    }

    public void add(Pet pet, PetstoreJob service) {
        List<PetstoreJob> services = petstoreJobHistory.getOrDefault(pet, new ArrayList<>());

        services.add(service);

        petstoreJobHistory.put(pet, services);
    }

    public List<Pet> getPetsByAge(Integer age) {
        return registeredPets.stream()
                .filter(pet -> pet.getAge().equals(age))
                .collect(Collectors.toList());
    }

    public Map<Pet, Double> getTop10PetsRevenue() {
        Map<Pet, Double> revenueByPet = new HashMap<>();

        petstoreJobHistory.forEach((k, v) -> {
            Double revenue = v.stream()
                    .map(PetstoreJob::getValue)
                    .reduce(0.0, Double::sum);

            revenueByPet.put(k, revenue);
        });

        return revenueByPet.entrySet()
                .stream()
                .sorted(Entry.<Pet, Double>comparingByValue().reversed())
                .limit(10)
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }
}
