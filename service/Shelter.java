package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import model.*;


public class Shelter implements ShelterInterface{
    private Creator PetCreator;
    private Statement sqlStatement;
    private result result;
    private String sqlString;

    public Shelter() {
        this.PetCreator = new PetCreator();
    }

    @Override
    public List<Pet> getAll() {
        
        List<Pet> shelter = new ArrayList<Pet>();
        Pet pet;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection dbConnection = getConnection()) {
                sqlStatement = dbConnection.createStatement();
                sqlString = "SELECT GenusId, id, name, Birthdate FROM pet_list ORDER BY id";
                ResultSet result = sqlStatement.executeQuery(sqlString);
                while (result.next()) {
                    PetType type = PetType.getType(result.getInt(1));
                    int id = result.getInt(2);
                    String name = result.getString(3);
                    LocalDate birthdate = result.getDate(4).toLocalDate();
                    pet = PetCreator.createPet(type, name, birthdate);
                    pet.setID(id);
                    shelter.add(pet);
                }
                return shelter;

            }
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            Logger.getLogger(Shelter.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
        return null;
    }

    @Override
    public Object getId(int idPet) {
        Pet pet = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection dbConnection = getConnection()) {

                sqlString = "SELECT GenusId, id, PetName, Birthday FROM pet_list WHERE id = ?";
                PreparedStatement prepSt = dbConnection.prepareStatement(sqlString);
                prepSt.setInt(1, idPet);
                ResultSet result = prepSt.executeQuery();

                if (result.next()) {

                    PetType type = PetType.getType(result.getInt(1));
                    int id = result.getInt(2);
                    String name = result.getString(3);
                    LocalDate birthday = result.getDate(4).toLocalDate();

                    pet = PetCreator.createPet(type, name, birthday);
                    pet.setID(id);
                }
                return pet;
            }
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            Logger.getLogger(Shelter.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
            
        }
    }
    
    @Override
    public int create(Pet pet) {
        int row = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection dbConnection = getConnection()) {

                sqlString = "INSERT INTO pet_list (Name, Birthdate, GenusId) SELECT ?, ?, (SELECT id FROM pet_types WHERE Genus_name = ?)";
                PreparedStatement prepSt = dbConnection.prepareStatement(sqlString);
                prepSt.setString(1, pet.getName());
                prepSt.setDate(2, Date.valueOf(pet.getBirthdate()));
                prepSt.setString(3, pet.getClass().getSimpleName());

                row = prepSt.executeUpdate();
                return row;
            }
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            Logger.getLogger(Shelter.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
    }


    public void train(int id, String command) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection dbConnection = getConnection()) {
                String sqlString = "INSERT INTO pet_command (id, CommandId) SELECT ?, (SELECT Id FROM commands WHERE Command_name = ?)";
                PreparedStatement prepSt = dbConnection.prepareStatement(sqlString);
                prepSt.setInt(1, id);
                prepSt.setString(2, command);
                prepSt.executeUpdate();
            }

        } catch (ClassNotFoundException | IOException | SQLException ex) {
            Logger.getLogger(Shelter.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
    }
    
    public List<String> getCommandsById (int id, int commands_type){   
        List <String> commands = new ArrayList <>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection dbConnection = getConnection()) {
                if (commands_type == 1){
                    sqlString = "SELECT Command_name FROM pet_command pc JOIN commands c ON pc.CommandId = c.id WHERE pc.id = ?";
                } else {
                    sqlString = "SELECT Command_name FROM commands c JOIN Genus_command gc ON c.id = gc.CommandId WHERE gc.GenusId = (SELECT GenusId FROM pet_list WHERE id = ?)";
                }
                PreparedStatement prepSt = dbConnection.prepareStatement(sqlString);
                prepSt.setInt(1, id);
                ResultSet result = prepSt.executeQuery();
                while (result.next()) {
                    commands.add(result.getString(1));
                }
                return commands;
            }
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            Logger.getLogger(Shelter.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        } 
    }



        
    @Override
    public int update(Pet pet) {
        int rows;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection dbConnection = getConnection()) {
                sqlString = "UPDATE pet_list SET PetName = ?, Birthdate = ? WHERE id = ?";
                PreparedStatement prepSt = dbConnection.prepareStatement(sqlString);

                prepSt.setString(1, pet.getName());
                prepSt.setDate(2, Date.valueOf(pet.getBirthdate())); 
                prepSt.setInt(3,pet.getID());
                
                rows = prepSt.executeUpdate();
                return rows;
            }
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            Logger.getLogger(Shelter.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        } 
    }

    @Override
    public void delete (int id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection dbConnection = getConnection()) {
                sqlString = "DELETE FROM pet_list WHERE id = ?";
                PreparedStatement prepSt = dbConnection.prepareStatement(sqlString);
                prepSt.setInt(1,id);
                prepSt.executeUpdate();
            }
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            Logger.getLogger(Shelter.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        } 
    }

    public static Connection getConnection() throws SQLException, IOException {

        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("Folder/resource/database.properties")) {

            props.load(fis);
            String url = props.getProperty("url");
            String username = props.getProperty("username");
            String password = props.getProperty("password");

            return DriverManager.getConnection(url, username, password);
        }
    }
}

}
