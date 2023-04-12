package service;

import java.util.List;

import model.Pet;

public interface  ShelterInterface <U>{
    List <U> getAll();
    U getId(int id);
    int create(Pet pet);
    int update(Pet item);  
    void delete (int item);  
    
}
