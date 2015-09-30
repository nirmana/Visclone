package cyclone.initialize;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import cyclone.exception.cycloneConfException;
import org.xml.sax.SAXException;

import cyclone.core.CloneClassList;
import cyclone.core.ClonedFileList;
import cyclone.core.Directory;
import cyclone.core.DirectoryStructure;
import cyclone.core.SourceFileList;

/**
 * Created by DilshaniS on 24/06/2015.
 */
public class Project {

    private String projectName;
    private String[] projectStructure;
    private String projectID;
    private String rootDirectoryName;
    private String rootDirectoryPath;
    private CloneClassList cloneClassList;
    private  ClonedFileList clonedFileList;
    private SourceFileList sourceFileList;

    /*public static void main(String args[]) throws cycloneConfException {
        try {
            Project project=new Project();
            System.out.println(project.getCloneClassList().getNumOfCloneClass());
            String[] directoryStructure=project.getProjectStructure();
            
            for(int i=0;i<directoryStructure.length;i++){
            	System.out.println(directoryStructure[i]);
            }

        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

    }*/

    public Project() throws TransformerException, ParserConfigurationException, IOException, SAXException, cycloneConfException {
    	XMLReader xmlReader=new XMLReader();
        xmlReader.xmlReader();
    	this.cloneClassList=xmlReader.getCloneClassList();
    	this.clonedFileList=xmlReader.getClonedFileList();
    	this.sourceFileList=xmlReader.getSourceFileList();
    	
    }
    public String[] getProjectStructure() {
        DirectoryStructure directoryStructure=new DirectoryStructure();
        this.projectStructure=directoryStructure.getDirectoryStructure();
        return projectStructure;
    }

    public CloneClassList getCloneClassList() {
        return this.cloneClassList;
    }

    public ClonedFileList getClonedFileList() {
        return this.clonedFileList;
    }

    public SourceFileList getSourceFileList() {
        return this.sourceFileList;
    }
    
}
