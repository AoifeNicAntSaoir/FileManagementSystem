import java.io.*;

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
                        mySocket.sendMessage(request.getAddress(),
                                request.getPort(), message);
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

                        createUser(username, password);
                        message = "Account successfully created ";
                        mySocket.sendMessage(request.getAddress(),
                                request.getPort(), message);

                        break;

                }

            } //end while
        } // end try
        catch (Exception ex) {
            ex.printStackTrace();
        } // end catch
    } //end main


    public static void createUser(String username, String password) {
        BufferedWriter bw = null;
        FileWriter fw = null;
        String path = "C:/FileManagementSystem/DistributedComputingFileMgmtSystem/users/";
        File file = new File(path + username);
        if (file.exists()) {
            System.out.println("The directory already exists @ " + path + username);
        } else {
            file.mkdir();
            System.out.println("Directory is created for " + username);
            String message = username + "," + " " + password;
            try {
                fw = new FileWriter("Users.txt", true);
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
            }
        }
    }

}//end class


