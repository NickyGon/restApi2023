package apiTest;

import factoryRequest.FactoryRequest;
import factoryRequest.RequestInfo;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Properties;

import java.util.Base64;

import static org.hamcrest.Matchers.equalTo;

public class ProjectCRUDTest {

    RequestInfo requestInfo = new RequestInfo();
    Response response;

    JSONObject body = new JSONObject();
    String auth;

    @BeforeEach
    public void setup(){
        body.put("Content","Data");
        auth = Base64.getEncoder().encodeToString((Properties.user+":"+Properties.pwd).getBytes());
    }

    @Test
    public void crudProjectTest(){
        requestInfo.setHost(Properties.host+"api/projects.json").setBody(body.toString()).setHeader("Authorization","Basic "+auth);

        response=FactoryRequest.make("post").send(requestInfo);
        response.then()
                .log().all()
                .statusCode(200)
                .body("Content",equalTo(body.get("Content")));
        int idProject = response.then().extract().path("Id");

        body.put("Content","Update");
        requestInfo.setHost(Properties.host+"api/projects/"+idProject+".json").setBody(body.toString());
        response=FactoryRequest.make("put").send(requestInfo);
        response.then()
                .log().all()
                .statusCode(200)
                .body("Content",equalTo(body.get("Content")));

        response=FactoryRequest.make("get").send(requestInfo);
        response.then()
                .log().all()
                .statusCode(200)
                .body("Content",equalTo(body.get("Content")));

        response=FactoryRequest.make("delete").send(requestInfo);
        response.then()
                .log().all()
                .statusCode(200)
                .body("Content",equalTo(body.get("Content")));
    }
}
