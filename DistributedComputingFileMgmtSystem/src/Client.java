import java.io.*;

/**
 * This module contains the presentaton logic of an Echo Client.
 * @author M. L. Liu
 */
public class Client {
   static final String endMessage = ".";
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
            System.out.println("Enter your option\n" +
                                 "\n1. Login" +
                                 "\n2. Logout" +
                                 "\n3. Upload" +
                                 "\n4. Download" +
                                 "\n5. Register");
            String option = br.readLine();

            switch(option) {
               case "1":
               case "2":
                  if(option.equals("1")) {
                     System.out.println("You want to log in");
                  }
                  else if(option.equals("2")){
                     System.out.println("You want to log out");
                  }
                  System.out.println("Enter firstname");
                  String firstname = br.readLine();
                  System.out.println("Enter password");
                  String password = br.readLine();
                  message = "1" + ", " + firstname + ", " + password;
                  serverResult = helper.getLoginDetails( message);
                  System.out.println(serverResult);
                  break;
               case "3":
               case "4":
               case "5":
                  System.out.println("You want to register");
                  System.out.println("Enter firstname");
                  firstname = br.readLine();
                  System.out.println("Enter password");
                  password = br.readLine();
                  message = "5" + ", " + firstname + ", " + password;
                  serverResult = helper.register( message);
                  System.out.println(serverResult);

                  break;



            }
          } // end while
      } // end try  
      catch (Exception ex) {
         ex.printStackTrace( );
      } // end catch
   } //end main
} // end class      
