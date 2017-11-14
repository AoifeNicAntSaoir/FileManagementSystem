import java.io.*;

/**
 * This module contains the presentaton logic of an Echo Client.
 * @author M. L. Liu
 */
public class Client {
   public static void main(String[] args) {
      InputStreamReader is = new InputStreamReader(System.in);
      BufferedReader br = new BufferedReader(is);

      try {
         System.out.println("Welcome to the File Management System client");
         String hostName = "localhost";
         String portNum = "7";          // default port number
         ClientHelper helper = new ClientHelper(hostName, portNum);
         boolean done = false;
         String message, serverResult;
         
         while (!done) {
            System.out.println("--------Enter your option-----------------" +
                                 "\n1. Login" +
                                 "\n2. Logout" +
                                 "\n3. Upload" +
                                 "\n4. Download" +
                                 "\n5. Register" +
                                 "\n6 Quit");
            String option = br.readLine();

            switch(option) {
               case "1":
                  System.out.println("You want to log in");
                  System.out.println("Enter username");
                  String username = br.readLine();
                  System.out.println("Enter password");
                  String password = br.readLine();
                  if(username.equals("") || username.isEmpty() ||
                          password.equals("") || password.isEmpty()) {
                     throw new EmptyArgsException("You left Empty Fields");
                  }
                  message = "1" + ", " + username + ", " + password;
                  serverResult = helper.send( message);
                  System.out.println(serverResult);
                  break;
               case "2":
                  System.out.println("You want to log out");
                  System.out.println("Enter username");
                  username = br.readLine();
                  System.out.println("Enter password");
                  password = br.readLine();
                  if(username.equals("") || username.isEmpty() ||
                          password.equals("") || password.isEmpty()) {
                     throw new EmptyArgsException("You left Empty Fields");
                  }
                  message = "2" + ", " + username + ", " + password;
                  System.out.println(message);
                  serverResult = helper.send( message);
                  System.out.println(serverResult);
               case "3":
               case "4":
               case "5":
                  System.out.println("You want to register");
                  System.out.println("Enter username");
                  username = br.readLine();
                  System.out.println("Enter password");
                  password = br.readLine();
                  //Check empty fields
                  if(username.equals("") || password.equals(""))
                     throw new EmptyArgsException("Missing fields");
                  message = "5" + ", " + username + ", " + password;
                  //Register
                  serverResult = helper.send( message);
                  System.out.println(serverResult);
                  break;
               case "6":
                  System.out.println("Quitting!");
                  helper.done();
                  done = true;
                  break;
               default:
                  System.out.println("Invalid option! Try again");
                  break;
            }
          } // end while
      } // end try  
      catch (Exception ex) {
         ex.printStackTrace( );
      } // end catch


   } //end main
} // end class      
