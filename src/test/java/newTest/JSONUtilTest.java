package newTest;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.JsonUtil;

public class JSONUtilTest {

    @Test
    public void verifyJSONEqual(){
        String expected="";
        String actual="";
        Assertions.assertTrue(JsonUtil.areEqualJSON(expected,actual));
    }
}
