package ui;


import java.util.Scanner;
import model.*;
import task15.*;
import model.*;


public class ConsoleMenu {
   
    ControlerPet controler;

    public ConsoleMenu(ControlerPet controler) {
        this.controler = controler;
    }

    public void start() {

        System.out.print("\033[H\033[J");
        try (Scanner in = new Scanner(System.in, "ibm866"); Count count = new Count()) {

            boolean flag = true;
            int id;
            while (flag) {

                System.out.println(
                        "\n1 - Full list\n2 - Add pet\n3 - Change \n4 - Look\n5 - Teach\n6 - Delete\n0 - Exit");
                String key = in.next();
                switch (key) {
                    case "1":
                        controler.getAllPet();
                        break;
                    case "2":
                        PetType type = menuChoice(in);
                        if (type != null) {
                            try {
                                controler.addPet(type);
                                count.add();
                                System.out.println("ok");
                            } catch (Excep e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        break;
                    case "3":
                        while (true) {
                            id = menuChoicePet(in);
                            if (id != 0)
                                try {
                                    controler.updatePet(id);
                                } catch (Excep e) {
                                    System.out.println(e.getMessage());
                                }
                            else
                                break;
                        }
                        break;
                    case "4":
                        while (true) {
                            id = menuChoicePet(in);
                            if (id != 0)
                                controler.getCommands(id);
                            else
                                break;
                        }
                        break;
                    case "5":
                        id = menuChoicePet(in);
                        if (id != 0)
                            menuTrainPet(id, in);
                        break;
                    case "6":
                        id = menuChoicePet(in);
                        if (id != 0)
                            controler.delete(id);
                        break;
                    case "0":
                        flag = false;
                        break;
                    default:
                        System.out.println("bad chooice");
                        break;
                }
            }
        }
    }

    private PetType menuChoice(Scanner in) {
        System.out.println("Who to add :\n1 - Cat \n2 - Dog\n3 - Rat\n0 - Back");

        while (true) {
            String key = in.next();
            switch (key) {
                case "1":
                    return PetType.Cat;
                case "2":
                    return PetType.Dog;
                case "3":
                    return PetType.Rat;
                case "0":
                    return null;
                default:
                    System.out.println("Wrong input");
                    break;
            }
        }
    }

    private int menuChoicePet(Scanner in) {
        System.out.println("\nid of a pet (0 to go back)? ");
        while (true) {
            int id = in.nextInt();
            in.nextLine();
            if (id == 0)
                return id;
            if (controler.getId(id) == null) {
                System.out.println("id is wrong, type 0 to back");
            } else
                return id;

        }
    }

    private void menuTrainPet(int petId, Scanner in) {
        Scanner sc = in;
        while (true) {
            System.out.println("New command, 0 to go back: ");
            String command = sc.nextLine();
            if (command.length() == 1 && command.equals("0"))
                return;
            if (controler.teachPet(petId, command))
                System.out.println("done");
        }
    }
}
