package cyclone.core;

/**
 * Created by DilshaniS on 03/07/2015.
 */
public class Fragment {
    private int startLine; //start Line of the fragment: Function/Block
    private int endLine;   //End line of the fragment
    private String sourcePath; //Path of the source file to which it belongs

    public Fragment(int startLine, int endLine, String sourcepath){
        this.startLine = startLine;
        this.endLine = endLine;
        this.sourcePath = sourcepath;

    }
    public int getEndLine() {
        return endLine;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public int getStartLine() {
        return startLine;
    }

    public void setEndLine(int endLine) {
        this.endLine = endLine;
    }

    public void setStartLine(int startLine) {
        this.startLine = startLine;
    }

    public void print(){
        System.out.println("Start Line " + startLine+"End Line " + endLine+"Path="+this.sourcePath);

    }

}

