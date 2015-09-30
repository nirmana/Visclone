package cyclone.core;

import cyclone.initialize.Constants;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Collection;

/**
 * Created by DilshaniS on 07/07/2015.
 */
public class DirectoryStructure {

    private static String[] directoryStructure;
    public DirectoryStructure(){
    	displayDirectoryContents();
    }

    public static void displayDirectoryContents() {
        File dir = new File(Constants.getSOURCEPATH());
        Collection<File> files = FileUtils.listFiles(dir, new String[]{"java"}, true);
        directoryStructure=new String[files.size()];
        for (int i=0;i<files.size();i++){
            directoryStructure[i]=files.toArray()[i].toString();
            System.out.println(directoryStructure[i]);
        }
    }

    public String[] getDirectoryStructure() {
        return this.directoryStructure;
    }
}
