import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Aoife Sayers on 11/11/2017.
 */
public class Testing {
    public static void main(String[] args) throws IOException {
        List<User> listOfAllUsers = new ArrayList<>();
        String FILENAME = "C:\\FileManagementSystem\\DistributedComputingFileMgmtSystem\\Users.txt";
        BufferedReader br = null;
        BufferedReader bRead;
        FileReader fr = null;
        String serverResponse = "";
        try {
            fr = new FileReader(FILENAME);
            br = new BufferedReader(fr);
            InputStreamReader is = new InputStreamReader(System.in);
            bRead = new BufferedReader(is);
            String sCurrentLine;
            String uname = "", pass = "";
            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);
                String[] splitMessage = sCurrentLine.split(", ");
                uname = splitMessage[0];
                uname = uname.trim();
                pass = splitMessage[1];
                pass = pass.trim();
                listOfAllUsers.add(new User((String) uname, (String) pass));
            }

            String un = "Aoife";
            String pa = "k";
            String response = findUsers(listOfAllUsers, un, pa);

            System.out.println(response);
            br.close();
            fr.close();

            /*System.out.println("Enter username");
            String username = bRead.readLine();
            System.out.println("Enter password");
            String password = bRead.readLine();
            System.out.println(username + " " + password);
            String res = areTheyTheSame(listOfAllUsers, username, password);
            System.out.println(res);*/




        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public static String findUsers(List<User> userList, String username, String password){

        String serverResponse = "501: Credentials entered incorrect/ user does not exist";
        for(User u: userList)
        {
            System.out.println(username + "" + u.getUsername());
            System.out.println(password + "" + u.getPassword());
            if(username.equals(u.getUsername()) &&  password.equals(u.getPassword()))
            {
                serverResponse =  "500: " + username + "found & logged in";
                LoggedInUsers.AddToList(new User(username, password));
                return serverResponse;
            }
        }

        return serverResponse;
    }
}
