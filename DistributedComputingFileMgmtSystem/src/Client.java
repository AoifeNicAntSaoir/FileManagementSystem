import com.sun.javaws.exceptions.InvalidArgumentException;
import sun.rmi.runtime.Log;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

            switch (option) {
               case "1":
                  System.out.println("You want to log in");
                  System.out.println("Enter username");
                  String username = br.readLine();
                  System.out.println("Enter password");
                  String password = br.readLine();
                  if (username.equals("") || username.isEmpty() ||
                          password.equals("") || password.isEmpty()) {
                     throw new EmptyArgsException("You left Empty Fields");
                  }
                  message = "1" + ", " + username + ", " + password;
                  serverResult = helper.send(message);
                  System.out.println(serverResult);
                  break;
               case "2":
                  System.out.println("You want to log out");
                  System.out.println("Enter username");
                  username = br.readLine();
                  System.out.println("Enter password");
                  password = br.readLine();
                  if (username.equals("") || username.isEmpty() ||
                          password.equals("") || password.isEmpty()) {
                     throw new EmptyArgsException("You left Empty Fields");
                  }
                  message = "2" + ", " + username + ", " + password;
                  System.out.println(message);
                  serverResult = helper.send(message);
                  System.out.println(serverResult);
                  break;
               case "3":
                  //Check if the user is logged in
                  System.out.println("You want to upload");
                  LoggedInUsers.AddToList(new User("AoifeSayers", "Hi"));
                  LoggedInUsers.getLoggedInUsers();
                  System.out.println("Enter username");
                  username = br.readLine();
                  if (LoggedInUsers.isLoggedIn(username)==false){
                     System.out.println(username + " is not logged in");
                     break;
                  }
                  System.out.println("Please ensure the file you want to upload is in C:\\FileManagementSystem\\DistributedComputingFileMgmtSystem\\" +
                          "\nEnter file name: ");
                  String homePath = "C:\\FileManagementSystem\\DistributedComputingFileMgmtSystem\\";
                  String filePath = br.readLine();
                  Path path = Paths.get(homePath + filePath);
                  byte[] data = Files.readAllBytes(path);
                  String byteDataString = new String(data);
                  //Save File as
                  System.out.println("The File Management System supports the following file types: " +
                          "\n.jpg, .jpeg, .txt, .png, .pdf, .html, .doc, .docs");
                  System.out.println("Enter server file name");
                  String fileName = br.readLine();
                  System.out.println("File you want to upload: " + fileName);
                  String fileType = fileName.substring(fileName.length() - 4);
                  boolean isValidFile = validateFileType(fileType);
                  if (isValidFile == false) {
                     throw new EmptyArgsException("Invalid file type");
                  }
                  System.out.println("Your file type" + fileType);
                  serverResult = helper.send("3" + ", " +  username + ", " + fileName + ", " + byteDataString);

                  break;
               case "4":
                  System.out.println("You want to download");


                  break;
               case "5":
                  System.out.println("You want to register");
                  System.out.println("Enter username");
                  username = br.readLine();
                  System.out.println("Enter password");
                  password = br.readLine();
                  //Check empty fields
                  if (username.equals("") || password.equals(""))
                     throw new EmptyArgsException("Missing fields");
                  message = "5" + ", " + username + ", " + password;
                  //Register
                  serverResult = helper.send(message);
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
         ex.printStackTrace();
      } // end catch
   } //end main

   public static boolean validateFileType(String fileType) {
      if (fileType.equalsIgnoreCase(".jpg") || fileType.equalsIgnoreCase(".jpeg") || fileType.equalsIgnoreCase(".txt") ||
              fileType.equalsIgnoreCase(".png") || fileType.equalsIgnoreCase(".pdf") || fileType.equalsIgnoreCase(".html") ||
              fileType.equalsIgnoreCase(".doc") || fileType.equalsIgnoreCase(".docs")) {
         return true;
      }
      return false;
   }
} // end class      
