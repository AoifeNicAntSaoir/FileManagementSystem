import java.io.*;

/**
 * This module contains the presentaton logic of an Echo Client.
 * @author M. L. Liu
 */
public class EchoClient1 {
   static final String endMessage = ".";
   public static void main(String[] args) {
      InputStreamReader is = new InputStreamReader(System.in);
      BufferedReader br = new BufferedReader(is);
      try {
         System.out.println("Welcome to the Echo client");
         String hostName = "localhost";
         String portNum = "7";          // default port number
         EchoClientHelper1 helper = new EchoClientHelper1(hostName, portNum);
         boolean done = false;
         String message, echo;
         while (!done) {
            System.out.println("Enter firstname");
            String firstname = br.readLine();
            System.out.println("Enter password");
            String password = br.readLine();
            message = "1" + ", " + firstname + ", " + password;
            if ((message.trim()).equals (endMessage)){
               done = true;
               helper.done( );
            }
            else {
               echo = helper.getLoginDetails( message);
               System.out.println(echo);
            }
          } // end while
      } // end try  
      catch (Exception ex) {
         ex.printStackTrace( );
      } // end catch
   } //end main
} // end class      
