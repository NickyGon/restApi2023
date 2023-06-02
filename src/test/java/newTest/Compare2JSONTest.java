package newTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import verifyJSON.Compare2JSON;

public class Compare2JSONTest {

    static Compare2JSON compare2JSON;
    @BeforeAll
    public static void setup(){
        compare2JSON=new Compare2JSON();
    }


   @Test
    public void verifyComparer(){
        String expected="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"date\":\"IGNORE\"\n" +
                "}";
        String actual="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":99,\n" +
                "\"date\":1651564855\n" +
                "}";
        boolean expectedResult=false;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    @Test
    public void verifyComparer2(){
        String expected="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"date\":\"IGNORE\"\n" +
                "}";
        String actual="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"date\":1651564855\n" +
                "}";
        boolean expectedResult=false;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    @Test
    public void verifyComparer3(){
        String expected="";
        String actual="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"date\":1651564855\n" +
                "}";
        boolean expectedResult=false;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    @Test
    public void verifyComparer4(){
        String expected="{}";
        String actual="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"date\":1651564855\n" +
                "}";
        boolean expectedResult=false;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    @Test
    public void verifyComparer5(){
        String expected="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"date\":\"IGNORE\"\n" +
                "}";
        String actual="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\"9,\n" +
                "\"date\":1651564855\n" +
                "}";
        boolean expectedResult=false;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    @Test
    public void verifyComparer6(){
        String expected="{\n" +
                "\"Content\":\"IGNORE\",\n" +
                "\"Icon\":9,\n" +
                "\"date\":\"IGNORE\"\n" +
                "}";
        String actual="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"date\":1651564855\n" +
                "}";
        boolean expectedResult=true;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    @Test
    public void verifyComparer7(){
        String expected="{\n" +
                "\"Content\":\"IGNORE\",\n" +
                "\"Icon\":9,\n" +
                "\"date\":\"1651564855\"\n" +
                "}";
        String actual="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":99,\n" +
                "\"date\":[1,2,3,4]\n" +
                "}";
        boolean expectedResult=false;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    @Test
    public void verifyComparer8(){
        String expected="{\n" +
                "\"Content\":\"IGNORE\",\n" +
                "\"Icon\":IGNORE,\n" +
                "\"date\":\"IGNORE\"\n" +
                "}";
        String actual="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"date\":1651564855\n" +
                "}";
        boolean expectedResult=true;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }
}
