/**
 * Created by Aoife Sayers on 13/11/2017.
 */

import java.io.*;

public class stuff {
    public static void main(String args[]){

       // check if LoggedOn
        //if logged on > get user directory & list files

        File[] files = new File("C:\\FileManagementSystem\\DistributedComputingFileMgmtSystem\\users\\AoifeSayers").listFiles();
        for(File f:files){
            System.out.println(f.getName());
        }



    }
}
