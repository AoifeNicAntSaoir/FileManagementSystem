/**
 * Created by Aoife Sayers on 11/11/2017.
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
public class WritingToTextFile {
    public static void main(String[] args) {
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            boolean done = false;
            String content = "This is the content to write into file\n";
            fw = new FileWriter("Users.txt", true);
            bw = new BufferedWriter(fw);
            bw.write(content);
            bw.append("\n");
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
