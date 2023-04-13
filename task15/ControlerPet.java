package task15;

import java.time.*;
import java.time.format.DateTimeFormatter;

import model.*;
import service.*;
import ui.*;

public class ControlerPet {
    private ShelterInterface<Pet> shelter;
    private Creator petCreator;
    private final View<Pet> view;
    private Validate validate;

    public ControlerPet(ShelterInterface<Pet> shelter) {
        this.shelter = shelter;
        this.petCreator = new PetCreator();
        this.view = new ConsoleView();
        this.validate = new Validate();
    }

    public void addPet(PetType type) {

        String[] data = new String[] { view.getName(), view.getBirthdate() };
        validate.validate(data);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate birthday = LocalDate.parse(data[1], formatter);
        try {
            int res = shelter.create(petCreator.createPet(type, data[0], birthday));
            view.showMessage(String.format("%d added successfully", res));
        } catch (RuntimeException e) {
            view.showMessage(e.getMessage());
        }

    }
    
    public void updatePet(int id) {

        Pet pet = getId(id);
        String[] data = new String[] { view.getName(), view.getBirthdate() };

        validate.validate(data);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate birthday = LocalDate.parse(data[1], formatter);
        pet.setName(data[0]);
        pet.setBirthdate(birthday);
        try {
            int res = shelter.update(pet);
            view.showMessage(String.format("%d updated \n", res));
        } catch (RuntimeException e) {
            view.showMessage(e.getMessage());
        }

    }

    public  void getAllPet() {
        try {
            view.printAll(shelter.getAll(), Pet.class);
        } catch (RuntimeException e) {
            view.showMessage(e.getMessage());
        }
    }

    public boolean teachPet(int id, String command) {
        try {

            if (((Shelter) shelter).getCommandsById(id, 1).contains(command))
                view.showMessage("already trained");
            else {
                if (!((Shelter) shelter).getCommandsById(id, 2).contains(command))
                    view.showMessage("can't do");
                else {
                    ((Shelter) shelter).train(id, command);
                    view.showMessage("successfully done \n");
                    return true;
                }
            }
        } catch (RuntimeException e) {
            view.showMessage(e.getMessage());
        }
        return false;
    }

    public Pet getId(int id) {
        try {
            return shelter.getId(id);
        } catch (RuntimeException e) {
            view.showMessage(e.getMessage());
        }
        return null;
    }

    public void delete(int id) {
        try {
            shelter.delete(id);
            view.showMessage("запись удалена");
        } catch (RuntimeException e) {
            view.showMessage(e.getMessage());
        }
    }

    public void getCommands(int id) {
        try {
            view.printAll(((Shelter) shelter).getCommandsById(id, 1), String.class);
        } catch (RuntimeException e) {
            view.showMessage(e.getMessage());
        }
    }
}
