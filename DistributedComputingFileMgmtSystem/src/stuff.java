/**
 * Created by Aoife Sayers on 13/11/2017.
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
public class stuff {
    public static void main(String args[]){

        String message = "3, AoifeSayers, AoifesShit.txt, hi!hi!hi!hi!hi!hi!hi!This is the content to write into file";
        String[] splitUploadMessage = message.split(",");
        String messageCode = splitUploadMessage[0];
        messageCode = messageCode.trim();
        System.out.println(messageCode);
        String username = splitUploadMessage[1];
        username = username.trim();
        System.out.println(username);

        String fileName = splitUploadMessage[2];
        fileName = fileName.trim();
        System.out.println(fileName);

        String fileContent = splitUploadMessage[3];
        System.out.println(fileContent);



    }
}
