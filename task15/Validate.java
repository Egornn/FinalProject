package task15;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import task15.*;

public class Validate {

    
    private boolean isName (String name){
        for (int i = 0; i < name.length(); i++) {
            if (!Character.UnicodeBlock.of(name.charAt(i)).equals(Character.UnicodeBlock.CYRILLIC) &&
    !Character.UnicodeBlock.of(name.charAt(i)).equals(Character.UnicodeBlock.BASIC_LATIN)) 
 {
                throw new Excep(String.format("Wrong name, only Latin and Cyrylic allowed"));
            }
        }
        return true;
    }
    
    private boolean isDate (String birthdate)  {

        LocalDate date;
        Integer [] shortMonth = {4, 6, 9, 11};
        int day;
        
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            date = LocalDate.parse(birthdate, formatter);
            day = date.getDayOfMonth();

        } catch (DateTimeParseException e) {
            throw new Excep("Wrong data format");
        }
        
        if ((Arrays.asList(shortMonth).contains(date.getMonthValue()) && day > 30) ||
                (date.isLeapYear() && date.getMonthValue() == 2 && day > 29) ||
                (!date.isLeapYear() && date.getMonthValue() == 2 && day > 28)) {
            
            throw new Excep("Wrong birthdate, not existant");
            
        } else 
            return true;
    }


    public void validate (String [] data){

        StringBuilder build = new StringBuilder();
        boolean isTrue = true;

        for (int i=0; i < data.length; i++){
            try{
                if (i==0) 
                    isName(data[i]);
                if (i==1)
                    isDate(data[i]);
                
            } catch (Excep e){
                build.append ("\n");
                build.append(e.getMessage());
                isTrue = false;
            }
        }
        if (isTrue == false){
            throw new Excep(build.toString());
        } 
    }

    
}
