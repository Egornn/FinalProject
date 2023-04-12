package model;

public class PetCreator extends Creator {
    @Override
    protected Pet createSpecificPet(PetType type) {
        switch (type) {
            case Cat:
                return new Cat();
            case Dog:
                return new Dog();
            case Rat:
                return new Rat();
        }
        return null;
        
    }
    
}
