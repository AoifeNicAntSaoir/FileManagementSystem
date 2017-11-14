import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aoife Sayers on 13/11/2017.
 */
public class LoggedInUsers {
    private static ArrayList<User> loggedInUsers = new ArrayList<>();

    public static void AddToList(User user) {       //This method is called to populate the list
        loggedInUsers.add(user);
    }

    public static void getLoggedInUsers(){
        System.out.println("All the currently logged in users");
        for(User u: loggedInUsers){
            System.out.println(u.getUsername());
        }
    }
    public static String logOutUser(String username){
        String serverResponse = "";
        int i = 0;
        for (User u : loggedInUsers) {
            if (u.getUsername().equals(username)) {
                System.out.println(i);
                loggedInUsers.remove(i);
                System.out.println("700 " + username + " was logged out");
                serverResponse = "700 " + username + " was logged out";
                return serverResponse;
            }
        }
            serverResponse = "User not logged in";
            i++;
            return serverResponse;
        }
    }
