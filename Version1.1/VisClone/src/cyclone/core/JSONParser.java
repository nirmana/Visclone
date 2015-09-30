/*package cyclone.core;


import cyclone.exception.cycloneConfException;
import cyclone.initialize.XMLReader;
//import org.codehaus.jackson.map.ObjectMapper;

import javax.json.Json;
import javax.json.JsonObject;
import java.io.File;
import java.io.IOException;

*//**
 * Created by DilshaniS on 10/07/2015.
 *//*
public class JSONParser {
    public static void main(String args[]){
        XMLReader xmlReader=new XMLReader();
        try {
            xmlReader.xmlReader();
            
            System.out.println(xmlReader.getCloneClassList().getCloneClassAtIndex(0));
            JsonObject jo = Json.createObjectBuilder()
                    .add("employees", Json.createArrayBuilder()
                            .add(Json.createObjectBuilder()
                                    .add("firstName", "John")
                                    .add("lastName", "Doe")))
                    .build();
            System.out.println(jo.size());
            //mapper.writeValue(new File("C:\\Users\\DilshaniS\\Documents\\Json.json"), xmlReader.getCloneClassList().getCloneClassAtIndex(0));
        } catch (cycloneConfException e) {
            e.printStackTrace();
        } 
    }

}
*/