package model;


import java.time.*;
import java.time.format.DateTimeFormatter;


public abstract class Pet {
    protected int petID;
    protected String name;
    protected LocalDate birthdate;
    

    @Override
    public String toString() {
        return String.format("Pet (ID: %d) named %s whos birthdate is %s ", name, petID, birthdate.toString());

    }

    public void setID(int id) {
        this.petID = id;

    }
    public void setName(String name) {
        this.name = name;

    }
    public void setBirthdate(LocalDate birthdate) {
        this.birthdate= birthdate;

    }
    
    
    public int getID() {
        return petID;
    }

    public String getName() {
        return name;
    }

    public String getBirthdate() {
        String pattern = "dd.MM.yyyy";
        String formattedDate = this.birthdate.format(DateTimeFormatter.ofPattern(pattern));
        return formattedDate;
    }

}
