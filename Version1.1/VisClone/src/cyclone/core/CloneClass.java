package cyclone.core;

import java.util.ArrayList;

/**
 * Created by DilshaniS on 24/06/2015.
 */
public class CloneClass {
    private int ccid;   /*The id to identify the clone class. Each clone object also
    contain this id to detect to which clone class they belong*/

    private int nClonelines;
    private int nClonefragments; //Number of clone fragments/ clone object
    private ArrayList<Clone> cloneList; //Contains the clone objects belong to this class

    public CloneClass(int ccid, int nlines, int nfragments) {
        this.ccid = ccid;
        this.nClonelines = nlines;
        this.nClonefragments = nfragments;
        cloneList = new ArrayList(nfragments);
    }

    public CloneClass(int ccid) {
        this.ccid = ccid;
        this.nClonelines = 0;
        this.nClonefragments = 0;
        cloneList = new ArrayList(nClonefragments);
    }

    public void addClone(Clone clone) {
        this.getCloneList().add(clone);
        /*this.nClonefragments++;
        this.nClonelines = this.nClonelines + clone.getNumClonedLines();*/
    }

    public Clone getCloneAtIndex(int index){
        return this.getCloneList().get(index);
    }

    public int getccId() {
        return ccid;
    }

    public int getNumOfClonelines() {
        return this.nClonelines;
    }

    public int getNumOfClonefragments() {
        return nClonefragments;
    }

    public ArrayList<Clone> getCloneList() {
        return cloneList;
    }

    @Override
    public String toString() {
        return "CC-" + this.getccId() + "(Fragments: " + this.getCloneList().size() + ")";
    }

    public void print() {
        System.out.println("<CLONE CLASS>");
        System.out.println("Clone Class ID=" + this.getccId() + "Num of fragments="+this.getNumOfClonefragments()+" Num of cloned lines="+this.getNumOfClonelines());
        for (int i = 0; i < this.getCloneList().size(); i++) {
            ((Clone) this.getCloneList().get(i)).print();
        }
        System.out.println("</CLONE CLASS>");
    }
}
