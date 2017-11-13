import java.io.File;

/**
 * Created by Aoife Sayers on 13/11/2017.
 */
public class CreatingDirectory {
    public static void main(String[] args) {
        String path = "C:/FileManagementSystem/DistributedComputingFileMgmtSystem/users/";
        String dirName = "yo";
        File file = new File(path + dirName);
        if (file.exists()) {
            System.out.println("The directory already exists @ " + path + dirName);
        }
        else{
            file.mkdir();
            System.out.println("Directory is created!");
        }
    }
}
