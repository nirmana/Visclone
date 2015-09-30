package cyclone.core;

/**
 * Created by DilshaniS on 24/06/2015.
 */
public class Clone {
    private int stratLine; //start line number of the clone
    private int endLine;   //end line number of the clone
    private int numClonedLines; //Total number of cloned Line: endLine-startLine+1

    private int cloneId; //This is id of the clone that uniquely identify it.
    private int cloneClassId; //This is the clone class id to which the clone belong.

    private String sourcePath; //This is the sourcePath. The file to which clone belong.

    /* Some fields are set to -1 to indicates that they have not valid value*/
    public Clone(int startLine, int endLine, int cloneId,int cloneClassId, String sourcePath) {
        this.stratLine = startLine;
        this.endLine = endLine;
        this.cloneId = cloneId;
        this.cloneClassId = cloneClassId ;
        this.sourcePath = sourcePath;
        this.numClonedLines = endLine - startLine + 1;

    }

    public int getccid() {
        return cloneClassId;
    }

    public void setCloneClassId(int ccid) {
        this.cloneClassId = ccid;
    }

    public int getStratLine() {
        return stratLine;
    }

    public int getEndLine() {
        return endLine;
    }

    public int getNumClonedLines() {
        return numClonedLines;
    }

    public int getCloneId() {
        return cloneId;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public String getParentDir(){
        int index = this.sourcePath.lastIndexOf("/");
        return this.sourcePath.substring(0,index);
    }

    @Override
    public String toString() {
        return "CloneID: " + this.getCloneId() + " Path: " + this.getSourcePath();
    }

    public void print() {
        System.out.println("<CLONE >");
        System.out.print("PCID=" + this.getCloneId() + " CCID="+this.getccid()+" StartLine="+this.getStratLine()+ " EndLine="+this.getEndLine()+" TotalLine="+this.getNumClonedLines());
        System.out.print("Source Path = " + this.getSourcePath());
        System.out.println("</CLONE>");
    }
}
