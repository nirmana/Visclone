package cyclone.initialize;

import cyclone.core.Directory;
import cyclone.core.DirectoryStructure;

/**
 * Created by DilshaniS on 24/06/2015.
 */
public class Project {
    private String projectName;
    private String[] projectStructure;
    private String projectID;
    private String rootDirectoryName;
    private String rootDirectoryPath;

    public String[] getProjectStructure() {
        DirectoryStructure directoryStructure=new DirectoryStructure();
        this.projectStructure=directoryStructure.getDirectoryStructure();
        return projectStructure;
    }
}
