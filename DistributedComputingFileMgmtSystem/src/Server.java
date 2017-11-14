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

public class Server {

    public static void main(String[] args) {
        int serverPort = 7;    // default port
        if (args.length == 1)
            serverPort = Integer.parseInt(args[0]);
        String messageCode;
        String username;
        String password;
        try {
            // instantiates a datagram socket for both sending
            // and receiving data
            MyServerDatagramSocket mySocket = new MyServerDatagramSocket(serverPort);
            System.out.println("File Management server ready.");
            while (true) {  // forever loop
                DatagramMessage request =
                        mySocket.receiveMessageAndSender();
                System.out.println("Request received");
                String message = request.getMessage();
                System.out.println("message received: " + message);
                // Now send the echo to the requestor
                String[] splitMessage = message.split(",");
                messageCode = splitMessage[0];
                username = splitMessage[1];
                password = splitMessage[2];
                //Removing whitespace from message
                messageCode = messageCode.trim();
                username = username.trim();
                password = password.trim();


                switch (messageCode) {
                    case "1":
                        System.out.println("Log in - server");
                        String loginResp = login(username, password);
                        mySocket.sendMessage(request.getAddress(),
                                request.getPort(), loginResp);
                        break;
                    case "2":
                        System.out.println("Log Out -server");
                        break;
                    case "3":
                        System.out.println("Upload - server");
                        break;
                    case "4":
                        System.out.println("Download -server");
                    case "5":
                        System.out.println("Create account - server");
                       String resp = createUser(username, password);
                        mySocket.sendMessage(request.getAddress(),
                                request.getPort(), resp);
                        break;
                }
            } //end while
        } // end try
        catch (Exception ex) {
            ex.printStackTrace();
        } // end catch
    } //end main

    public static String login(String username, String password){
        List<User> listOfAllUsers = new ArrayList<>();
        String FILENAME = "C:\\FileManagementSystem\\DistributedComputingFileMgmtSystem\\Users.txt";
        BufferedReader br = null;
        FileReader fr = null;
        String serverResponse = "";
        try {
            fr = new FileReader(FILENAME);
            br = new BufferedReader(fr);
            String sCurrentLine;
            String uname="", pass="";
            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);
                String[] splitMessage = sCurrentLine.split(", ");
                uname = splitMessage[0];
                uname = uname.trim();
                pass = splitMessage[1];
                pass = pass.trim();
                listOfAllUsers.add(new User((String)uname, (String)pass));
            }
            for(User u : listOfAllUsers){
                if(u.getUsername() == (username) && u.getPassword() == (password)) //.equals(Object obj) == value equality
                {
                    User us = new User(username, password);
                    LoggedInUsers.AddToList(us);
                    System.out.println(username + " is now logged in");
                    serverResponse = username + " is now logged in";
                }
                /*else // not right
                {
                    serverResponse = username + " is not a registered user";
                }
                */
            }


            LoggedInUsers.getLoggedInUsers();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
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
                    serverMessage = "User Created:" + username;
                }
            } else {
                System.out.println("error occured");
                serverMessage = "Sorry an error occured - user may already exist or something went wrong";
            }
        }
        return serverMessage;
    }

}//end class


