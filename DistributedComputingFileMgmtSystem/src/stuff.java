/**
 * Created by Aoife Sayers on 13/11/2017.
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
public class stuff {
    public static void main(String[] args) throws IOException {


        Writer output;
        output = new BufferedWriter(new FileWriter("C:\\FileManagementSystem\\DistributedComputingFileMgmtSystem\\myFile.txt", true));  //clears file every time
        output.append("hi!");
        output.close();
    }
}
