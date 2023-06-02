package newTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import verifyJSON.Compare2JSON;

public class CompareTwoJSONSTest {

    static Compare2JSON compare2JSON;
    @BeforeAll
    public static void setup(){
        compare2JSON=new Compare2JSON();
    }


   //Test positivo Todo igual
   @Test
   public void verifyComparer(){
       String expected="{\n" +
               "\"Content\":\"ExampleFile\",\n" +
               "\"Icon\":9,\n" +
               "}";
       String actual="{\n" +
               "\"Content\":\"ExampleFile\",\n" +
               "\"Icon\":9,\n" +
               "}";
       boolean expectedResult=true;
       boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
       Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
   }

    //Test positivo Un ignore
    @Test
    public void verifyComparer2(){
        String expected="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
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

    //Test positivo Dos ignore
    @Test
    public void verifyComparer3(){
        String expected="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
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

    //Test positivo Todos ignore
    @Test
    public void verifyComparer4(){
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

    //Test negativo sin igualdad completa o ignore
    @Test
    public void verifyComparer5(){
        String expected="{\n" +
                "\"Content\":\"Christmas\",\n" +
                "\"Icon\":9,\n" +
                "\"date\":\"123231321\"\n" +
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

    //Test negativo un ignore pero desigualdad
    @Test
    public void verifyComparer6(){
        String expected="{\n" +
                "\"Content\":\"Christmas\",\n" +
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

    //Test negativo dos ignore pero desigualdad
    @Test
    public void verifyComparer7(){
        String expected="{\n" +
                "\"Content\":\"Christmas\",\n" +
                "\"Icon\":IGNORE,\n" +
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

    //Test negativo expected vacío
    @Test
    public void verifyComparer8(){
        String expected="";
        String actual="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":99,\n" +
                "\"date\":1651564855\n" +
                "}";
        boolean expectedResult=false;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    //Test negativo expected JSON vacío
    @Test
    public void verifyComparer9(){
        String expected="{}";
        String actual="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":99,\n" +
                "\"date\":1651564855\n" +
                "}";
        boolean expectedResult=false;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    //Test negativo actual vacío
    @Test
    public void verifyComparer10(){
        String expected="{\n" +
                "\"Content\":\"Christmas\",\n" +
                "\"Icon\":9,\n" +
                "\"date\":\"IGNORE\"\n" +
                "}";
        String actual="";
        boolean expectedResult=false;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    //Test negativo actual JSON vacío
    @Test
    public void verifyComparer11(){
        String expected="{\n" +
                "\"Content\":\"Christmas\",\n" +
                "\"Icon\":9,\n" +
                "\"date\":\"IGNORE\"\n" +
                "}";
        String actual="{}";
        boolean expectedResult=false;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    //Test negativo los dos vacios
    @Test
    public void verifyComparer12(){
        String expected="";
        String actual="";
        boolean expectedResult=false;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    //Test positivo los dos JSON vacios
    @Test
    public void verifyComparer13(){
        String expected="{}";
        String actual="{}";
        boolean expectedResult=true;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    //Test negativo expected vacio vs actual JSON vacio
    @Test
    public void verifyComparer14(){
        String expected="";
        String actual="{}";
        boolean expectedResult=false;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    //Test negativo expected JSON vacio vs actual vacio
    @Test
    public void verifyComparer15(){
        String expected="{}";
        String actual="";
        boolean expectedResult=false;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    //Test positivo Todos iguales pero otro orden
    @Test
    public void verifyComparer16(){
        String expected="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"Creator\":\"Nicole\"\n" +
                "}";
        String actual="{\n" +
                "\"Icon\":9,\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Creator\":\"Nicole\"\n" +
                "}";
        boolean expectedResult=true;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    //Test negativo Una desigualdad pero otro orden
    @Test
    public void verifyComparer17(){
        String expected="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"Creator\":\"Nicole\"\n" +
                "}";
        String actual="{\n" +
                "\"Icon\":99,\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Creator\":\"Nicole\"\n" +
                "}";
        boolean expectedResult=false;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    //Test negativo Dos desigualdades pero otro orden
    @Test
    public void verifyComparer18(){
        String expected="{\n" +
                "\"Content\":\"Christmas\",\n" +
                "\"Icon\":9,\n" +
                "\"Creator\":\"Nicole\"\n" +
                "}";
        String actual="{\n" +
                "\"Icon\":99,\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Creator\":\"Nicole\"\n" +
                "}";
        boolean expectedResult=false;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    //Test negativo Todos desiguales pero otro orden
    @Test
    public void verifyComparer19(){
        String expected="{\n" +
                "\"Content\":\"Christmas\",\n" +
                "\"Icon\":9,\n" +
                "\"date\":323231235\n" +
                "}";
        String actual="{\n" +
                "\"Icon\":99,\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"date\":1651564855\n" +
                "}";
        boolean expectedResult=false;
        boolean actualResult=compare2JSON.verifyEqualJSON(expected,actual);
        Assertions.assertEquals(actualResult,expectedResult,"Error, los resultados no son lo mismo");
    }

    //Test negativo al menos una diferencia
    @Test
    public void verifyComparer20(){
        String expected="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"date\":1651564855\n" +
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

    //Test negativo al menos dos diferencias
    @Test
    public void verifyComparer21(){
        String expected="{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"date\":\"17238323278\"\n" +
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

}
