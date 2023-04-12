package ui;

import java.util.*;
import model.*;

public class ConsoleView implements View<Pet> {
    Scanner cons;

    public ConsoleView() {
        cons = new Scanner(System.in, "ibm866");
    }

    @Override
    public String getName() {
        System.out.print("Name: ");
        return cons.nextLine();
    }

    @Override
    public String getBirthdate() {
        System.out.println("Date in format DD.MM.YYYY");
        return cons.nextLine();
    }

    @Override
    public <U> void printAll(List<U> list, Class<U> subClass) {
        if (!list.isEmpty()) {
            if (subClass == Pet.class)
                System.out.println("List of our pets:");
            for (U pet : list) {
                System.out.println(pet);
            }
        }

    }
    @Override
    public void showMessage(String s) {
        System.out.println(s);
    }

}
