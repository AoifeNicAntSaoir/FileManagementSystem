import java.io.File;

/**
 * Created by Aoife Sayers on 13/11/2017.
 */
public class CreatingDirectory {
    public static void main(String[] args) {
        String path = "C:\\FileManagementSystem\\DistributedComputingFileMgmtSystem\\users\\";
        String name = "john";
       File dir = new File(path+name);
        if(dir.exists())
        {
            System.out.println("Already exists");
        }
        else
        {
            dir.mkdir();
            System.out.println(dir.toString() + " has been created");
        }



    }
}
