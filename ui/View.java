package ui;

import java.util.List;

public interface View <T>{
    
    String getName();
    String getBirthdate();
    <U> void printAll (List <U> list, Class <U> subclass);
    void showMessage (String s);

}
