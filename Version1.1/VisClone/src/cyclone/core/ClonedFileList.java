package cyclone.core;

import java.util.ArrayList;

/**
 * Created by DilshaniS on 03/07/2015.
 */
public class ClonedFileList {
    private ArrayList<ClonedFile> clonedFileList; //List of clone classes

    public ClonedFileList() {
        clonedFileList = new ArrayList();
    }

    public void addToClonedFileList(ClonedFile cf){
        this.getClonedFileList().add(cf);
    }

    public int getNumClonedFiles(){
        return this.getClonedFileList().size();
    }
    public ClonedFile getSourceFileIndex(int index){
        return this.getClonedFileList().get(index);
    }

    protected ArrayList<ClonedFile> getClonedFileList(){
        return this.clonedFileList;
    }
    @Override
    public String toString() {
        return "" + "Clone Classes:" + this.getNumClonedFiles();
    }

}
