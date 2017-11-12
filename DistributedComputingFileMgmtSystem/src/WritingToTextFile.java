/**
 * Created by Aoife Sayers on 11/11/2017.
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
public class WritingToTextFile {
    private static final String FILENAME = "C:\\shitfuckbollox\\myFile.txt";
    public static void main(String[] args) {

        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            boolean done = false;
            String content = "This is the content to write into file\n";
           for(int i=0; i<10; i++){
                content += "more text\n";
            }


            fw = new FileWriter(FILENAME);
            bw = new BufferedWriter(fw);
            bw.write(content);

            System.out.println("Done");

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
