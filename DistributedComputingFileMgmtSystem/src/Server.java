import sun.rmi.runtime.Log;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This module contains the application logic of an echo server
 * which uses a connectionless datagram socket for interprocess 
 * communication.
 * A command-line argument is required to specify the server port.
 * @author M. L. Liu
 */
/* Codes for responses
Login success 500
Login unsuccessful 501

register success 600
register unsuccessful 601

logout succesfull 700
logout unsuccceful 701

upload success 800
upload unsuccessful 801

download success
download unsuccessful
 */
public class Server {
    public static void main(String[] args) {
        int serverPort = 7;    // default port
        String messageCode;
        String username;
        String password;
        try {
            // instantiates a datagram socket for both sending and receiving data
            MyServerDatagramSocket mySocket = new MyServerDatagramSocket(serverPort);
            System.out.println("File Management server ready.");

            while (true) {  // forever loop
                //Send & receive data
                DatagramMessage request = mySocket.receiveMessageAndSender();
                System.out.println("Request received");
                String message = request.getMessage();

                System.out.println("message received: " + message);
                // Decode message from 1, myUsername, myPassword etc.
                String[] splitMessage = message.split(",");
                messageCode = splitMessage[0];
                username = splitMessage[1];
                password = splitMessage[2];
                //Removing whitespace from message
                messageCode = messageCode.trim();
                username = username.trim();
                password = password.trim();

                login("admin","password");

                //Determine which type of message & invoke different methods
                //1 Login,  2 Logout, 3 upload, 4 download, 5. register
                switch (messageCode) {
                    case "1":
                        System.out.println("Log in - server");
                        String loginResp = login(username, password);
                        mySocket.sendMessage(request.getAddress(), request.getPort(), loginResp);
                        break;
                    case "2":
                        System.out.println("Log Out - server");
                        String logoutResp = logout(username);
                        mySocket.sendMessage(request.getAddress(), request.getPort(), logoutResp);
                        break;
                    case "3":
                        System.out.println("Upload - server");
                        //if(password.equals("checkingLoggedOn")/) {
                            System.out.println("Server is checking if " + username + " is logged on");
                           String checkLoggedInRes = checkIfLoggedIn(username);
                        System.out.println(checkLoggedInRes);
                            mySocket.sendMessage(request.getAddress(), request.getPort(), checkLoggedInRes);
                        FileOutputStream fos = new FileOutputStream("C:\\FileManagementSystem\\DistributedComputingFileMgmtSystem\\workpls.txt");
                        fos.write(message.getBytes());
                        fos.close();




                       // }
                        break;
                    case "4":
                        System.out.println("Download -server");
                    case "5":
                        System.out.println("Create account - server");
                        String resp = createUser(username, password);
                        mySocket.sendMessage(request.getAddress(), request.getPort(), resp);
                        break;
                    default:
                        System.out.println("An error occured!");
                        resp = "00: An error occured on ther server try again";
                        mySocket.sendMessage(request.getAddress(), request.getPort(), resp);
                }
              // LoggedInUsers.getLoggedInUsers();
            } //end while
        } // end try
        catch (Exception ex) {
            ex.printStackTrace();
        } // end catch
    } //end main

    public static String checkIfLoggedIn(String username){
       Boolean isLoggedIn =  LoggedInUsers.isLoggedIn(username);
        System.out.println(isLoggedIn);
        String loggedInResp = "default";
        if(isLoggedIn.equals(false)){
            loggedInResp = username + " is not logged in";
            return loggedInResp;
        }
        else if(isLoggedIn.equals(true)) {
            loggedInResp = username + " is logged in";
        }
        return loggedInResp;
    }
    public static String logout(String username){
        String logoutResp = LoggedInUsers.logOutUser(username);
        return logoutResp;
    }

    public static String login(String username, String password) {
        List<User> listOfAllUsers = new ArrayList<>();
        String FILENAME = "C:\\FileManagementSystem\\DistributedComputingFileMgmtSystem\\users\\Users.txt";
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
            serverResponse = findUsers(listOfAllUsers, username, password);
            System.out.println(serverResponse);
            br.close();
            fr.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return serverResponse;
    }

    public static String findUsers(List<User> userList, String username, String password){
        String serverResponse = "501: Credentials entered incorrect/ user does not exist";
        for(User u: userList)
        {
            System.out.println(username + "" + u.getUsername());
            System.out.println(password + "" + u.getPassword());
            if(username.equals(u.getUsername()) &&  password.equals(u.getPassword()))
            {
                serverResponse =  "500: " + username + " found & logged in";
                LoggedInUsers.AddToList(new User(username, password));
                return serverResponse;
            }
        }
        return serverResponse;
    }


    public static String createUser(String username, String password) {
        BufferedWriter bw = null;
        FileWriter fw = null;
        //Set path & Create directory for each user in users/myName
        String path = "C:\\FileManagementSystem\\DistributedComputingFileMgmtSystem\\users\\";;
        File dir = new File(path+username);
        String serverMessage = "default mssg";
        //Check if directory exists
        if(!dir.exists()) {
            if (dir.mkdirs()) {
                System.out.println(dir.toString() + " has been created");
                try {
                    String message = username + ", " + password;
                    fw = new FileWriter(path+"Users.txt", true);
                    bw = new BufferedWriter(fw);
                    bw.write(message + "\n");
                    bw.append("");
                    System.out.println("Users were added to file");
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (bw != null)
                            bw.close();
                        if (fw != null)
                            fw.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    serverMessage = "600: User Created: " + username;
                }
            } else {
                System.out.println("error occured");
                serverMessage = "601: Sorry an error occured - user may already exist or something went wrong";
            }
        }
        return serverMessage;
    }

}//end class


