import model.*;
import resource.*;
import service.*;
import task15.*;
import ui.*;
public class Program {
    public static void main(String[] args) throws Exception {

        ShelterInterface <Pet> shelter = new Shelter();
        ControlerPet controler = new ControlerPet(shelter);
        new ConsoleMenu (controler).start();
    }
}
