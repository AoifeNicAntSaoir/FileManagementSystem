import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aoife Sayers on 13/11/2017.
 */
public class LoggedInUsers {
    public static ArrayList<User> loggedInUsers = new ArrayList<>();

    public static void AddToList(User user) {       //This method is called to populate the list
        loggedInUsers.add(user);
    }

    public static void getLoggedInUsers(){
        System.out.println("All the currently logged in users");
        for(User u: loggedInUsers){
            System.out.println(u.getUsername());
        }
    }

    public static void logOutUser(String username, String password){
        loggedInUsers.remove(new User(username, password));
    }
}
