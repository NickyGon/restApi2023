package utils;

import org.json.JSONObject;

import java.util.Iterator;

public class JsonUtil {

    /*
    * {"key":"value"}
    * {"key":"value"}
    */
    public static boolean areEqualJSON(String expected,String actual){
        boolean areEqual=true;
        JSONObject actualJson= new JSONObject(actual);
        JSONObject expectedJson= new JSONObject(expected);

        Iterator<?> keys = expectedJson.keys();

        while(keys.hasNext()){
            String key= (String) keys.next();
            String expectedValue= String.valueOf(expectedJson.get(key));
            String actualValue=String.valueOf(actualJson.get(key));

            if(actualJson.has(key)){
                if (expectedValue.equals(actualValue) && expectedValue.equals("IGNORE")){
                    System.out.println("INFO > Ignoring key comparation for [ "+key+" ]");
                } else if (!expectedValue.equals(actualValue)){
                    areEqual=false;
                    System.out.println("ERROR> el key ["+key+"tiene como expected ["+expectedValue+"] vs actual ["+actualValue+"]" );
                }
            } else {
                areEqual=false;
                System.out.println("ERROR> El actual result no tiene el key: ["+key+"]");
            }


        }

        return areEqual;
    }
}
