import java.io.*;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This module contains the presentaton logic of a FTP System.
 * Adaption of an EchoClient from M. L. Liu
 * @author M. L. Liu
 */
public class Client {
   public static void main(String[] args) {
      InputStreamReader is = new InputStreamReader(System.in);
      BufferedReader br = new BufferedReader(is);
      try {
         System.out.println("Welcome to the File Management System client");
         String hostName = "localhost";
         String portNum = "7";
         ClientHelper helper = new ClientHelper(hostName, portNum);
         boolean done = false;
         String message, serverResult;
         //Program Loop
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
               case "1": //Login
                  System.out.println("You want to log in");
                  System.out.println("Enter username");
                  String username = br.readLine();
                  System.out.println("Enter password");
                  String password = br.readLine();
                  if (username.equals("") || username.isEmpty() ||
                          password.equals("") || password.isEmpty()) {
                     throw new EmptyArgsException("You left Empty Fields");
                  }
                  serverResult = login(username, password);
                  System.out.println(serverResult);
                  break;
               case "2": //Logout
                  System.out.println("You want to log out");
                  System.out.println("Enter username");
                  username = br.readLine();
                  System.out.println("Enter password");
                  password = br.readLine();
                  if (username.equals("") || username.isEmpty() ||
                          password.equals("") || password.isEmpty()) {
                     throw new EmptyArgsException("You left Empty Fields");
                  }
                  serverResult = logout(username, password);
                  System.out.println(serverResult);
                  break;
               case "3": //Upload
                  //Check Users Details
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
                  String filePath = br.readLine();

                  //Save File as
                  System.out.println("The File Management System supports the following file types: " +
                          "\n.jpg, .txt, .png, .pdf, .doc,");
                  System.out.println("Enter server file name");
                  String fileName = br.readLine();
                  System.out.println("File you want to upload: " + fileName);
                  String fileType = fileName.substring(fileName.length() - 4);
                  boolean isValidFile = validateFileType(fileType);
                  if (isValidFile == false) {
                     throw new EmptyArgsException("Invalid file type");
                  }
                  System.out.println("Your file type" + fileType);
                  serverResult = upload(username, fileName);
                  System.out.println(serverResult);
                  break;
               case "4": //Downloads
                  System.out.println("You want to download");
                  LoggedInUsers.AddToList(new User("AoifeSayers", "Hi"));
                  LoggedInUsers.getLoggedInUsers();
                  System.out.println("Enter username");
                  username = br.readLine();
                  if (LoggedInUsers.isLoggedIn(username)==false){
                     System.out.println(username + " is not logged in");
                     break;
                  }
                  serverResult = helper.send("4, " + username + ", "  +"getDirectory");
                  System.out.println(serverResult);
                  System.out.println("Enter file name you wish to download");
                  fileName = br.readLine();
                  System.out.println("Enter name you wish to call the file");
                  String saveFileAs = br.readLine();
                  serverResult = download(username, fileName, saveFileAs);
                  System.out.println(serverResult);

                  break;
               case "5": //Register
                  System.out.println("You want to register");
                  System.out.println("Enter username");
                  username = br.readLine();
                  System.out.println("Enter password");
                  password = br.readLine();
                  //Check empty fields
                  if (username.equals("") || password.equals(""))
                     throw new EmptyArgsException("Missing fields");
                  serverResult = register(username, password);
                  System.out.println(serverResult);
                  break;
               case "6": //Quit
                  System.out.println("Quitting!");
                  helper.done();
                  done = true;
                  break;
               default:
                  System.out.println("Invalid option! Try again");
                  break;
            }// end switch
         } // end while
      } // end try  
      catch (Exception ex) {
         ex.printStackTrace();
      } // end catch
   } //end main
   public static String register(String username, String password) throws IOException {
      ClientHelper helper = new ClientHelper("localhost", "7");
      String message = "5" + ", " + username + ", " + password;
      String serverResult = helper.send(message);
      return serverResult;
   }
   public static String download(String username, String fileName, String saveFileAs) throws IOException {
      ClientHelper helper = new ClientHelper("localhost","7");
      String result = helper.send("4, " + username + ", " + fileName);
      System.out.println("Result received" + result);
      FileOutputStream fos = new FileOutputStream("C:\\FileManagementSystem\\DistributedComputingFileMgmtSystem\\" + saveFileAs);
      fos.write(result.getBytes());
      fos.close();
      System.out.println("File Downloaded to this destination: C:\\FileManagementSystem\\DistributedComputingFileMgmtSystem\\" + saveFileAs);
      return  result;
   }
   public static String upload(String username, String fileName) throws IOException {
      ClientHelper helper = new ClientHelper("localhost","7");
      String homePath = "C:\\FileManagementSystem\\DistributedComputingFileMgmtSystem\\";
      Path path = Paths.get(homePath + fileName);
      byte[] data = Files.readAllBytes(path);
      String byteDataString = new String(data);
      String serverResult = helper.send("3" + ", " +  username + ", " + fileName + ", " + byteDataString);
      return serverResult;
   }
   public static String logout(String username, String password) throws IOException {
      ClientHelper helper = new ClientHelper("localhost", "7");
      String message = "2" + ", " + username + ", " + password;
      String serverResult = helper.send(message);
      return serverResult;
   }
   public static String login(String username, String password) throws IOException {
      ClientHelper helper = new ClientHelper("localhost", "7");
      String message = "1" + ", " + username + ", " + password;
      String serverResult = helper.send(message);
      return serverResult;
   }
   public static boolean validateFileType(String fileType) {
      if (fileType.equalsIgnoreCase(".jpg") || fileType.equalsIgnoreCase(".txt") ||
              fileType.equalsIgnoreCase(".png") || fileType.equalsIgnoreCase(".pdf") ||
              fileType.equalsIgnoreCase(".doc")) {
         return true;
      }
      return false;
   }
} // end class      
