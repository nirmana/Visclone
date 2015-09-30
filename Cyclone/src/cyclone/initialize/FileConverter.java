package cyclone.initialize;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import org.w3c.dom.Element;
import java.util.*;

/**
 * Created by DilshaniS on 19/06/2015.
 */
public class FileConverter {
    private static HashMap<Integer, String> map;
    static int cloneID=0;

public static void main(String args[]) throws ParserConfigurationException, IOException, SAXException, TransformerException {
    FileConverter fileConverter=new FileConverter();
    fileConverter.fileConverter();
}

public void fileConverter() throws TransformerException, ParserConfigurationException, IOException, SAXException {
    try {
        File xmlFile = new File(Constants.getConqatXML());
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder =null;
        dBuilder = dbFactory.newDocumentBuilder();
        DocumentBuilder docBuilder=null;
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        map = new HashMap<Integer, String>();

        NodeList sourceList = doc.getElementsByTagName("sourceFile");
        NodeList cloneClassList = doc.getElementsByTagName("cloneClass");
        for (int temp = 0; temp < sourceList.getLength(); temp++) {
            Node nNode =null;
             nNode = sourceList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                map.put(Integer.parseInt(nNode.getAttributes().getNamedItem("id").getNodeValue()), nNode.getAttributes().getNamedItem("location").getNodeValue());
            }
        }

        docBuilder=dbFactory.newDocumentBuilder();
        Document output = docBuilder.newDocument();
        Element rootElement = output.createElement("cloneDetectionResult");
        output.appendChild(rootElement);
        Element info = output.createElement("info");
        rootElement.appendChild(info);
        FileConverter fileConverter =new FileConverter();
        info.setAttribute("processedLines", fileConverter.getAttributeCount(sourceList, "length"));
        info.setAttribute("processedFiles",fileConverter.getElementNodesCount(sourceList));
        info.setAttribute("duplicatedLines",fileConverter.getAttributeCount(cloneClassList, "normalizedLength"));
        info.setAttribute("duplicatedFiles",fileConverter.countDuplicatedFiles(cloneClassList));

        Element cloneclass = null;
            for (int i = 0; i < cloneClassList.getLength(); i++) {
                Node nNode = null;
                nNode = cloneClassList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                     cloneclass=getCloneClass(output,String.valueOf(i + 1), fileConverter.getElementNodesCount(nNode.getChildNodes()), nNode.getAttributes().getNamedItem("normalizedLength").getNodeValue(),
                             nNode,fileConverter,i);
                    rootElement.appendChild(cloneclass);
                }

            }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource dmSource = new DOMSource(output);
        StreamResult result = new StreamResult(new File(Constants.getConvertedXML()));

        transformer.transform(dmSource, result);

       // Runtime.getRuntime().exec("notepad /Users/DilshaniS/Desktop/myxml.xml");

    } catch (ParserConfigurationException e) {
        e.printStackTrace();
    }catch(SAXException e1){
        e1.printStackTrace();
    }catch(IOException e2){
        e2.printStackTrace();
    }
}

    public String getElementNodesCount(NodeList childNodes) {
        int nodes =0;
        for (int j = 0; j < childNodes.getLength(); j++) {
            if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
                nodes++;
            }
        }
        return String.valueOf(nodes);
    }

    public String getAttributeCount(NodeList nodeList, String s) {
        int nodes =0;
        for (int j = 0; j <= nodeList.getLength(); j++) {
            Node nNode = null;
            nNode = nodeList.item(j);
            if (nNode!=null) {
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    nodes=nodes+ Integer.parseInt(nNode.getAttributes().getNamedItem(s).getNodeValue());
                }
            }
        }
        return String.valueOf(nodes);
    }


    public String getPathBySourceFileId(int sourceFileId) {
        return map.get(sourceFileId).toString();
    }

    public String countDuplicatedFiles(NodeList nodeList){
        List<String> temp = new ArrayList<String>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = null;
            nNode = nodeList.item(i);
            for (int j = 0; j < nNode.getChildNodes().getLength(); j++) {
                if (nNode.getChildNodes().item(j).getNodeType() == Node.ELEMENT_NODE) {
                    temp.add(nNode.getChildNodes().item(j).getAttributes().getNamedItem("sourceFileId").getNodeValue());
                }
            }
        }
        Set<String> uniqueValues = new HashSet<String>(temp);
        return String.valueOf(uniqueValues.size());
    }

    private static Element getCloneClass(Document doc, String id, String nFragments, String length,
                                         Node nNode, FileConverter fileConverter,int ccID) {
        Element cloneClassNode = doc.createElement("cloneclass");
        cloneClassNode.setAttribute("ccid", id);
        cloneClassNode.setAttribute("nfragments", nFragments);
        cloneClassNode.setAttribute("length", length);

        for (int j = 0; j < nNode.getChildNodes().getLength(); j++) {
            if (nNode.getChildNodes().item(j).getNodeType() == Node.ELEMENT_NODE) {
                cloneClassNode.appendChild(getSources(doc, fileConverter.getPathBySourceFileId(Integer.parseInt(nNode.getChildNodes().item(j).getAttributes().getNamedItem("sourceFileId").getNodeValue())),
                        nNode.getChildNodes().item(j).getAttributes().getNamedItem("startLine").getNodeValue(),
                        nNode.getChildNodes().item(j).getAttributes().getNamedItem("endLine").getNodeValue(),ccID,cloneID));
                cloneID++;
            }
        }
        return cloneClassNode;
    }

    private static Element getSources(Document doc,String file, String startline, String endline,int ccID,int cloneID) {
        Element node = doc.createElement("source");
        node.setAttribute("file",file);
        node.setAttribute("startline",startline);
        node.setAttribute("endline",endline);
        node.setAttribute("cloneclassid",String.valueOf(ccID+1));
        node.setAttribute("cloneid",String.valueOf(cloneID));
        return node;
    }

}
