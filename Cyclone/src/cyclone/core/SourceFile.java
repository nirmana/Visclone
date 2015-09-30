package cyclone.core;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by DilshaniS on 03/07/2015.
 */
public class SourceFile {
    private String path; //Path of the cloned file
    private String name; //Name of the clone file
    private String location;
    private ArrayList<Fragment> FragmentList; //The number of methods contain by this file

    private int numOfLine;

    public int getNumOfLine() {
        return numOfLine;
    }

    public void setNumOfLine(int numOfLine) {
        this.numOfLine = numOfLine;
    }

    public SourceFile(File f){
        path = f.getAbsolutePath();
        name = f.getName();
        FragmentList = new ArrayList<Fragment>();
    }

    public SourceFile(String path){
        this.path= path;
       // this.name = name;
        FragmentList = new ArrayList<Fragment>();
    }
    public SourceFile(String location, String path){
        this.path= path;
        this.location = location;
        FragmentList = new ArrayList<Fragment>();
    }

    public ArrayList<Fragment> getFragmentList() {
        return FragmentList;
    }

    public void addToFragmentList(Fragment fragment) {
        this.getFragmentList().add(fragment);
    }

    public Fragment getFragmentAtIndex(int index) {
        return this.getFragmentList().get(index);
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

    public String getLocation() {
        return location;
    }


    public void print() {

    }


}

