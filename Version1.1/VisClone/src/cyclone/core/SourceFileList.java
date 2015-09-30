package cyclone.core;

import java.util.ArrayList;

/**
 * Created by DilshaniS on 03/07/2015.
 */
public class SourceFileList {
    private ArrayList<SourceFile> sourceFileList; //List of source files

    public SourceFileList() {
        sourceFileList = new ArrayList();
    }

    public void addToSourceFileList(SourceFile sf){
        this.getSourceFileList().add(sf);
    }

    public int getNumSourceFiles(){
        return this.getSourceFileList().size();
    }
    public SourceFile getSourceFileIndex(int index){
        return this.getSourceFileList().get(index);
    }

    protected ArrayList<SourceFile> getSourceFileList(){
        return this.sourceFileList;
    }
    /*@Override
    public String toString() {
        return "" + "Clone Classes:" + this.getNumSourceFiles();
    }
*/
    /*public void print() {
        for (int i = 0; i < this.getNumSourceFiles(); i++) {
            ((SourceFile) this.getSourceFileIndex(i)).print();
        }
    }*/
}
