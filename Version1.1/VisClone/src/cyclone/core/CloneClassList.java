package cyclone.core;

import java.util.ArrayList;

/**
 * Created by DilshaniS on 24/06/2015.
 */
public class CloneClassList {
    private ArrayList<CloneClass> cloneClassList; //List of clone classes

    public CloneClassList() {
        cloneClassList = new ArrayList();
    }

    public void addToCloneClassList(CloneClass cc){
        this.getCloneClassList().add(cc);
    }

    public int getNumOfCloneClass(){
        return this.getCloneClassList().size();
    }
    public CloneClass getCloneClassAtIndex(int index){
        return this.getCloneClassList().get(index);
    }

    protected ArrayList<CloneClass> getCloneClassList(){
        return this.cloneClassList;
    }
    @Override
    public String toString() {
        return "" + "Clone Classes:" + this.getNumOfCloneClass();
    }

    public void print() {
        for (int i = 0; i < this.getNumOfCloneClass(); i++) {
            ((CloneClass) this.getCloneClassAtIndex(i)).print();
        }
    }

}
