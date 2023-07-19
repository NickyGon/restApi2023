package verifyJSON;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.sound.midi.SysexMessage;
import java.io.File;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Compare2JSON {
    public boolean verifyEqualJSON(String expectedJSON, String actualResult){
        JSONObject expected;
        JSONObject actual;
        boolean valiter=true;

        ObjectMapper mapper= new ObjectMapper();


        System.out.println("Expected JSON key values");
        iterateInfo(expectedJSON);
        System.out.println("------------------------");
        System.out.println("Actual JSON key values");
        iterateInfo(actualResult);
        System.out.println(" ");
        try {
            expected= new JSONObject(expectedJSON);
            System.out.println(mapper.readTree(String.valueOf(expected)));
        } catch (Exception e){
            System.out.println("* Expected JSON (and maybe actual JSON) is not correctly formatted");
            return false;
        }
        try {
            actual= new JSONObject(actualResult);
            System.out.println(mapper.readTree(String.valueOf(actual)));
        } catch (Exception e){
            System.out.println("* Actual JSON is not correctly formatted");
            return false;
        }

        if (!expected.keySet().equals(actual.keySet())){
            System.out.println("* There are not the same keys in both JSONs");
            return false;
        } else {
            Iterator<String> keys= expected.keys();
            while (keys.hasNext()){
                String key = keys.next();
                if (expected.get(key).equals("IGNORE")){
                    System.out.println("* Expected Key: "+key+ " has been ignored of verification");
                }
                if (!expected.get(key).equals(actual.get(key)) && !expected.get(key).equals("IGNORE")){
                    System.out.println("* Expected value "+ expected.get(key)+ " is different from Actual's value "+actual.get(key)+" for key "+ key);
                    valiter=false;
                } else if (!expected.get(key).equals("IGNORE")){
                    System.out.println("* Expected value "+ expected.get(key)+ " equals Actual's value "+actual.get(key)+" for key "+ key);
                }

            }
        }
        return valiter;
    }

    private void iterateInfo(String jString){
        try {
            JSONObject jsonStr=new JSONObject(jString);
            Iterator<String> keys= jsonStr.keys();
            while (keys.hasNext()){
                String key = keys.next();
                String toTell= key+" has value "+jsonStr.get(key);
                System.out.println(toTell);
            }
        } catch (Exception e){
            return;
        }
    }

}
