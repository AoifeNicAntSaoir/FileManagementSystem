import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Aoife Sayers on 11/11/2017.
 */
public class Testing {
    public static void main(String[] args) {

        List<User> friends = new ArrayList<>();
        friends.add(new User("Aoife", "hi"));
        friends.add(new User("Caspar", "boo"));

        Iterator<User> iter = friends.iterator();
       for(User u: friends){
           int i=0;
           if(u.getUsername()=="Aoife"){
               iter.remove();
           }
        }

        friends.remove(0);
        for(User u:friends){
            System.out.println(u.getUsername() + " " + u.getPassword());
        }
    }
}
