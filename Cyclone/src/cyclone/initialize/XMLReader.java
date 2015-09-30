package cyclone.initialize;

import cyclone.core.*;
import cyclone.exception.cycloneConfException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by DilshaniS on 24/06/2015.
 */
public class XMLReader {
    //private static HashMap<Integer, String> map;
    private CloneClassList cloneClassList;
    private  ClonedFileList clonedFileList;
    SourceFileList sourceFileList;

    public static void main(String args[]) throws cycloneConfException {
       XMLReader xmlReaderClass=new XMLReader();
       xmlReaderClass.xmlReader();
   }

    public void xmlReader() throws cycloneConfException {

        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc;
            Document document;

            //Load cyclone xml
            File xmlFile= new File(Constants.getConvertedXML());
            if (!xmlFile.exists()) {
                throw new cycloneConfException("cyclone.xml does not exists in "+
                        Constants.getConvertedXML());
            }else {
                doc = docBuilder.parse(xmlFile);
                doc.getDocumentElement().normalize();

                NodeList cloneClass = doc.getElementsByTagName("cloneclass");
                cloneClassList = new CloneClassList();
                for (int temp = 0; temp < cloneClass.getLength(); temp++) {
                    Node nNode = null;
                    nNode = cloneClass.item(temp);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        CloneClass cc = new CloneClass(Integer.parseInt(nNode.getAttributes().getNamedItem("ccid").getNodeValue()),
                                Integer.parseInt(nNode.getAttributes().getNamedItem("length").getNodeValue()),
                                Integer.parseInt(nNode.getAttributes().getNamedItem("nfragments").getNodeValue()));
                        addCloneList(nNode, cc);
                        cloneClassList.addToCloneClassList(cc);
                        //    cc.print();
                    }
                }//cloneClassList.print();



                NodeList clonedFile = doc.getElementsByTagName("source");
                clonedFileList = new ClonedFileList();
               // int cloneFiles = 0;
                for (int j = 0; j < clonedFile.getLength(); j++) {
                    Node nNode = null;
                    nNode = clonedFile.item(j);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        if (clonedFileList.getNumClonedFiles() == 0) {
                            ClonedFile cf = new ClonedFile(nNode.getAttributes().getNamedItem("file").getNodeValue());
                            clonedFileList.addToClonedFileList(cf);

                        } else {
                                String file=nNode.getAttributes().getNamedItem("file").getNodeValue();
                                ClonedFile cf = new ClonedFile(nNode.getAttributes().getNamedItem("file").getNodeValue());
                                boolean isExist = false;
                                for (int k = 0; k < clonedFileList.getNumClonedFiles(); k++) {
                                  /* System.out.println(clonedFileList.getSourceFileIndex(k).getPath());
                                    System.out.println(file+"   aluth eka---------------");*/
                                    if (clonedFileList.getSourceFileIndex(k).getPath().trim().equals(file.trim())) {
                                        isExist = true;
                                        break;
                                    }
                                }
                                if (!isExist) {
                                    clonedFileList.addToClonedFileList(cf);
                                }
                            //cloneFiles++;
                            }

                        }
                    }
                    System.out.println(clonedFileList.getNumClonedFiles());
                }
                //Load conqat xml
                File conqat = new File(Constants.getConqatXML());
                if (!conqat.exists()) {
                    throw new cycloneConfException("conqat.xml does not exists in " +
                            Constants.getConqatXML());
                } else {
                    document = docBuilder.parse(conqat);
                    document.getDocumentElement().normalize();

                    NodeList sourceList = document.getElementsByTagName("sourceFile");
                    sourceFileList = new SourceFileList();
                    //CloneClassList cloneClassList=new CloneClassList();
                    for (int temp = 0; temp < sourceList.getLength(); temp++) {
                        Node nNode = null;
                        nNode = sourceList.item(temp);
                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                            SourceFile sf = new SourceFile(nNode.getAttributes().getNamedItem("location").getNodeValue(),
                                    nNode.getAttributes().getNamedItem("path").getNodeValue());

                            sourceFileList.addToSourceFileList(sf);
                            //    cc.print();
                        }
                    }//cloneClassList.print();
                    //   System.out.println(sourceFileList.getNumSourceFiles());
                }

        }catch (ParserConfigurationException e) {
            e.printStackTrace();
        }catch(SAXException e1){
            e1.printStackTrace();
        }catch(IOException e2){
            e2.printStackTrace();
        }
    }

    public static void addCloneList(Node node, CloneClass cc){
        Clone clone;
        for (int j = 0; j < node.getChildNodes().getLength(); j++) {
            if (node.getChildNodes().item(j).getNodeType() == Node.ELEMENT_NODE) {
                clone = new Clone(Integer.parseInt(node.getChildNodes().item(j).getAttributes().getNamedItem("startline").getNodeValue()),
                                  Integer.parseInt(node.getChildNodes().item(j).getAttributes().getNamedItem("endline").getNodeValue()),
                                  Integer.parseInt(node.getChildNodes().item(j).getAttributes().getNamedItem("cloneid").getNodeValue()),
                                  Integer.parseInt(node.getChildNodes().item(j).getAttributes().getNamedItem("cloneclassid").getNodeValue()),
                                  node.getChildNodes().item(j).getAttributes().getNamedItem("file").getNodeValue());
                cc.addClone(clone);
               // System.out.println(clone.getParentDir());
            }
        }
    }
    public CloneClassList getCloneClassList() {
        return cloneClassList;
    }

    public ClonedFileList getClonedFileList() {
        return clonedFileList;
    }

    public SourceFileList getSourceFileList() {
        return sourceFileList;
    }
}
