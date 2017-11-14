import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Aoife Sayers on 14/11/2017.
 */
public class ReadFileIntoByteArray {
    public static void main(String[] args) {
        File file = new File("/temp/abc.txt");
//init array with file length
        byte[] bytesArray = new byte[(int) file.length()];
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("C:\\FileManagementSystem\\DistributedComputingFileMgmtSystem\\myFile.txt");
            fis.read(bytesArray); //read file into bytes[]
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(bytesArray.length);

    }
}
