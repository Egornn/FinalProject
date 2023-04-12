package model;

import java.time.LocalDate;

public abstract class Creator {
    protected abstract Pet createSpecificPet(PetType type);

    public Pet createPet(PetType type, String name, LocalDate date) {
        Pet pet = createSpecificPet(type);
        pet.setName(name);
        pet.setBirthdate(date);
        return pet;
    }
}
