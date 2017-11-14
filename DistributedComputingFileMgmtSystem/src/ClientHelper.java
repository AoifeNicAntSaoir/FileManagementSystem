import java.net.*;
import java.io.*;

/**
 * This class is a module which provides the application logic
 * for an Echo client using connectionless datagram socket.
 * @author M. L. Liu
 */
public class ClientHelper {
   private MyClientDatagramSocket mySocket;
   private InetAddress serverHost;
   private int serverPort;

   ClientHelper(String hostName, String portNum)
      throws SocketException, UnknownHostException { 
  	   this.serverHost = InetAddress.getByName(hostName);
  		this.serverPort = Integer.parseInt(portNum);
      // instantiates a datagram socket for both sending
      // and receiving data
   	this.mySocket = new MyClientDatagramSocket(); 
   } 
	
   public String getLoginDetails( String message)
      throws SocketException, IOException {                                                                                 
      String response = "";
      mySocket.loginLogout(serverHost, serverPort, message);
	   // now receive the echo
      response = mySocket.receiveMessage();
      return response;
   } //end getEcho

   public String register(String message)
           throws SocketException, IOException {
      String response = "";
      mySocket.createAccount( serverHost, serverPort, message);
      // now receive the echo
      response = mySocket.receiveMessage();
      return response;

   }

   public void done( ) throws SocketException {
      mySocket.close( );
   }  //end done

} //end class
