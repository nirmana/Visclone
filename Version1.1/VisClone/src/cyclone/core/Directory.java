package cyclone.core;

import java.util.ArrayList;

/**
 * Created by DilshaniS on 30/06/2015.
 */
public class Directory {
    private ArrayList<Directory> childDirectoryList;
    private ArrayList<Clone> ownCloneList; // the clone list that reside within this diretory. Not withing sub directories
    private ArrayList<ClonedFile> ownClonedFileList; // the cloned file list that reside within this diretory. Not withing sub directories.
    private ArrayList<SourceFile> ownSourceFileList;//source file within this directory
    private ArrayList<Fragment> ownFragmentList;//only within this directory

    private ArrayList<Clone> allCloneList; //this list contains all clones in this directory and also in the subsequent subDirectories altogether
    private ArrayList<ClonedFile> allClonedFileList; // The list of cloned file within this directories and subirectories
    private ArrayList<SourceFile> allSourceFileList;//all sourcefileList within this and sub directories
    private ArrayList<Fragment> allFragmentList;//All frgments within this and subdirectories

    private String name;
    private String path;

    //This data should be collected when the directory structure established by the CloneManager
    private int nclonedFile;
    private int nclone;
    private int totalNumOfLine;

    private long totalLine;         //Number of lines within this diretories including sub directories
    private long totalClonedline;
    private long ownTotalLine;      //total line of the files residing only at level-0 of this directory
    private long ownTotalClonedLine;

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public long getOwnTotalClonedLine() {
        return ownTotalClonedLine;
    }

    public long getOwnTotalLine() {
        return ownTotalLine;
    }

    public long getTotalClonedline() {
        return totalClonedline;
    }

    public long getTotalLine() {
        return totalLine;
    }

    public Directory() {
        nclonedFile = 0;
        nclone = 0;

        /*this.name = name;
        this.path = path;*/

        childDirectoryList = new ArrayList();

        ownCloneList = new ArrayList<Clone>();
        ownClonedFileList = new ArrayList<ClonedFile>();
        ownSourceFileList = new ArrayList<SourceFile>();
        ownFragmentList = new ArrayList<Fragment>();

        this.allCloneList = new ArrayList();
        this.allClonedFileList = new ArrayList();
        this.allSourceFileList = new ArrayList<SourceFile>();
        this.allFragmentList = new ArrayList<Fragment>();
    }

    public void addToChildDirectoryList(Directory d) {
        this.getChildDirectoryList().add(d);
    }

    protected ArrayList<Directory> getChildDirectoryList() {
        return this.childDirectoryList;
    }

    public Directory getChildDirectoryAtIndex(int index) {
        return this.getChildDirectoryList().get(index);
    }

    public int getNumOfChildDirectory() {
        return this.getChildDirectoryList().size();
    }

    public ArrayList<Clone> getAllCloneList() {
        return allCloneList;
    }

    public ArrayList<ClonedFile> getAllClonedFileList() {
        return allClonedFileList;
    }

    public ArrayList<Fragment> getAllFragmentList() {
        return allFragmentList;
    }

    public ArrayList<SourceFile> getAllSourceFileList() {
        return allSourceFileList;
    }

    public ArrayList<Clone> getOwnCloneList() {
        return ownCloneList;
    }

    public ArrayList<ClonedFile> getOwnClonedFileList() {
        return ownClonedFileList;
    }

    public ArrayList<Fragment> getOwnFragmentList() {
        return ownFragmentList;
    }

    public ArrayList<SourceFile> getOwnSourceFileList() {
        return ownSourceFileList;
    }



    @Override
    public String toString() {
        return "" + this.getName() + " (Clone: " + this.getAllCloneList().size() + " Cloned File: " + this.getAllClonedFileList().size() + ")";
    }

    /* the objective of this function is to build the cloned file list and propagate the result upward
     * so that upper directory can build its clonedFile list by adding the result for all its subdirectory
     * and add them with the clonedfileList
     */

    protected void calculateOwnTotalClonedLine() {
        for (int i = 0; i < this.getOwnCloneList().size(); i++) {
            this.ownTotalClonedLine = this.ownTotalClonedLine + this.getOwnCloneList().get(i).getNumClonedLines();
        }
    }

    protected void calculateOwnTotalLine() {
        for (int i = 0; i < this.getOwnSourceFileList().size(); i++) {
            this.ownTotalLine = this.ownTotalLine + this.getOwnSourceFileList().get(i).getNumOfLine();
        }

    }

    public void buildList() {
        ArrayList<Clone> cloneList = new ArrayList();
        ArrayList<ClonedFile> clonedFileList = new ArrayList();
        ArrayList<SourceFile> sourceFileList = new ArrayList();
        ArrayList<Fragment> fragmentList = new ArrayList();


        //System.out.println("Building list for = "+this.getPath());
        for (int i = 0; i < childDirectoryList.size(); i++) {
            ((Directory) childDirectoryList.get(i)).buildList();
            ArrayList<Clone> cl = ((Directory) childDirectoryList.get(i)).getAllCloneList();
            ArrayList<ClonedFile> cfl = ((Directory) childDirectoryList.get(i)).getAllClonedFileList();
            ArrayList<SourceFile> sfl = (((Directory) childDirectoryList.get(i)).getAllSourceFileList());
            ArrayList<Fragment> fl = (((Directory) childDirectoryList.get(i)).getAllFragmentList());

            this.getAllCloneList().addAll(cl);
            this.getAllClonedFileList().addAll(cfl);
            this.getAllSourceFileList().addAll(sfl);
            this.getAllFragmentList().addAll(fl);

            //((Directory) childDirectoryList.get(i)).calculateOwnTotalClonedLine();
            //((Directory) childDirectoryList.get(i)).calculateOwnTotalLine();

            this.totalClonedline = this.totalClonedline + ((Directory) childDirectoryList.get(i)).getTotalClonedline();
            this.totalLine = this.totalLine + ((Directory) childDirectoryList.get(i)).getTotalLine();

            //System.out.println(this.getPath()+" Total ClonedLine = "+this.totalClonedline);
            //System.out.println(this.getPath()+" Total Line = "+this.totalLine);

        }
        this.getAllCloneList().addAll(this.getOwnCloneList());
        this.getAllClonedFileList().addAll(this.getOwnClonedFileList());
        this.getAllSourceFileList().addAll(this.getOwnSourceFileList());
        this.getAllFragmentList().addAll(this.getOwnFragmentList());
        this.calculateOwnTotalClonedLine();
        this.calculateOwnTotalLine();


        this.totalClonedline = this.totalClonedline + this.ownTotalClonedLine;
        this.totalLine = this.totalLine + this.ownTotalLine;

        //System.out.println("Finally = "+this.getPath()+" Total ClonedLine = "+this.totalClonedline);
        //System.out.println("Finally ="+this.getPath()+ " Total Line = "+this.totalLine);

        //+System.out.println("Name = "+this.getName()+"Path = "+this.getPath()+" TotalFile="+this.getAllClonedFileList().size()+"Total Clone = "+this.getAllCloneList().size());

    }
}




