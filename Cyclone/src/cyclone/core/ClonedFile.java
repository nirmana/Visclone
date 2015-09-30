package cyclone.core;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by DilshaniS on 03/07/2015.
 */
public class ClonedFile {
    private ArrayList<Clone> cloneList;
    private int              clonedLine;
    private String           location;
    private String           name;    // Name of the clone file
    private int              numOfLine;
    private String           path;    // Path of the cloned file

    public ClonedFile(String path,String location) {
        this.path            = path;
        //this.name            = f.getName();
        cloneList       = new ArrayList();    // the list of clones this file contain
        this.location   = location;
        /*this.clonedLine = 0;
        this.numOfLine  = 0;*/
    }

    public ClonedFile(String path) {
        this.path            = path;
        //name            = this.name;
        cloneList       = new ArrayList();    // the list of clones this file contain
        this.clonedLine = 0;
        this.numOfLine  = 0;
    }

    public int getNumOfLine() {
        return numOfLine;
    }

    public int getClonedLine() {
        return clonedLine;
    }

    public void setNumOfLine(int numOfLine) {
        this.numOfLine = numOfLine;
    }

    public void addToCloneList(Clone c) {
        this.cloneList.add(c);
        this.clonedLine = this.clonedLine + c.getNumClonedLines();
    }

    protected ArrayList<Clone> getCloneList() {
        return this.cloneList;
    }

    public Clone getCloneAtIndex(int index) {
        return this.getCloneList().get(index);
    }

    public int getNumOfClone() {
        return this.getCloneList().size();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String toString() {
        return (name + " (Cloned Fragment: " + this.getCloneList().size()+ ")");
    }

    public String getParentDir() {
        return this.getPath().substring(0, this.getPath().length() - this.getName().length());
    }
}
