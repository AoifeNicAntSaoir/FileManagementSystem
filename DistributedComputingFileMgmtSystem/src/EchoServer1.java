import java.io.*;

/**
 * This module contains the application logic of an echo server
 * which uses a connectionless datagram socket for interprocess 
 * communication.
 * A command-line argument is required to specify the server port.
 * @author M. L. Liu
 */

public class EchoServer1 {

   public static void main(String[] args) {
      int serverPort = 7;    // default port
      if (args.length == 1 )
         serverPort = Integer.parseInt(args[0]);
       String messageCode;
       String username;
       String password;
      try {
         // instantiates a datagram socket for both sending
         // and receiving data
   	   MyServerDatagramSocket mySocket = new MyServerDatagramSocket(serverPort); 
         System.out.println("Echo server ready.");  
         while (true) {  // forever loop
            DatagramMessage request = 
               mySocket.receiveMessageAndSender();
            System.out.println("Request received");
            String message = request.getMessage( );
            System.out.println("message received: "+ message);
            // Now send the echo to the requestor
             String[] splitMessage = message.split(",");
             messageCode = splitMessage[0];
             username = splitMessage[1];
             password = splitMessage[2];
             //Removing whitespace from message
             messageCode = messageCode.trim();
             username = username.trim();
             password = password.trim();


             switch(messageCode){
                 case "1":
                     System.out.println("Log in");
                     //logIn
                     break;
                 case "2":
                     System.out.println("Log Out");
                     break;
                 case "3":
                     System.out.println("Upload");
                     break;
                 case "4":
                     System.out.println("Download");
             }
            mySocket.sendMessage(request.getAddress( ),
               request.getPort( ), message);
		   } //end while
       } // end try
	    catch (Exception ex) {
          ex.printStackTrace( );
	    } // end catch
   } //end main
    public void decodeProtocolMessage(String message)
    {

    }

} // end class      
