package model;

public enum PetType {
    
    Cat,
    Dog,
    Rat;

    public static PetType getType (int typeId){
        switch (typeId){
            case 1:
                return PetType.Cat;
            case 2:
                return PetType.Dog;
            case 3:
                return PetType.Rat;
            default:
                return null;
        }
    }
}
