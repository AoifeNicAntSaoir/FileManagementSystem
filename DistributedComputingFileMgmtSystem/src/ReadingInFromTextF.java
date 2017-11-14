import sun.rmi.runtime.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aoife Sayers on 12/11/2017.
 */
public class ReadingInFromTextF {
    public static void main(String[] args) {

        List<User> listOfAllUsers = new ArrayList<>();
        String FILENAME = "C:\\FileManagementSystem\\DistributedComputingFileMgmtSystem\\Users.txt";
        BufferedReader br = null;
        FileReader fr = null;

        try {
            fr = new FileReader(FILENAME);
            br = new BufferedReader(fr);
            String sCurrentLine, username, password;
            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);
                String[] splitMessage = sCurrentLine.split(", ");
                username = splitMessage[0];
                password = splitMessage[1];
               listOfAllUsers.add(new User((String)username, (String)password));
            }
            String randomUser = "Aoife";
            String randomPass = "Hi";
            for(User u : listOfAllUsers){
                if(u.getUsername().equals(randomUser) && u.getPassword().equals(randomPass))
                {
                    User us = new User(randomUser, randomPass);
                    LoggedInUsers.AddToList(us);
                    System.out.println(randomUser + " is now logged in");
                }
            }


            LoggedInUsers.getLoggedInUsers();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();

            }
        }
    }

}
