import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class bufferedReader{



   public static void main(String [] args){


       Event_1 birthday = new Event_1("Birthday", "2016-11-19 07:00", "Gatlinburg", "Hiking");
       Event_1 graduation = new Event_1("Graduation", "2017-12-10 10:00", "New Orleans", "UNO Graduation");

       BufferedReader reader = null;
       //Display header
       //System.out.printf("%-10s%-12s%-12s%10s%n", "Name", "Date", "Location", "Description");

       try
       {
           reader = new BufferedReader(new FileReader("Calendar_1.ser"));
           String line;
           while((line = (String)reader.readLine())!=null)
           {
               System.out.println(line);
           }
           //read event data
           //Event_1 bday = (Event_1) reader.readLine();
         //  Event_1 grad = (Event_1) objectInput.readObject();

           //System.out.println(bday);
           //System.out.println(grad);

           //print event data -- still trying to get this to work
           //System.out.printf("%-10d%-12s%-12s%10.2f%n", bday.getName(), bday.getDate(), bday.getLocation(), bday.getDescription());
           //System.out.printf("%-10d%-12s%-12s%10.2f%n", grad.getName(), grad.getDate(), grad.getLocation(), grad.getDescription());

           reader.close();
       }
       catch(IOException e)
       {
           System.out.println("Could not read Calendar_1.ser");
       } 
   }

}