package apiTestItems;

import factoryRequest.FactoryRequest;
import factoryRequest.RequestInfo;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Properties;

import java.util.Base64;

import static org.hamcrest.Matchers.equalTo;

public class ItemCRUDTestToken {
    int projectId=4114346;
    RequestInfo requestInfo = new RequestInfo();
    Response response;

    JSONObject body = new JSONObject();
    String auth;


    @BeforeEach
    public void setup(){
        body.put("Content","Item para token Class Ahora");
        body.put("ProjectId",projectId);
        auth = Base64.getEncoder().encodeToString((Properties.user+":"+Properties.pwd).getBytes());
    }

    @Test
    public void crudItemTest() {

        //Para autenticacion

        requestInfo.setHost(Properties.host + "api/authentication/token.json").setBody(body.toString()).setHeader("Authorization", "Basic " + auth);
        response = FactoryRequest.make("get").send(requestInfo);
        response.then()
                .log().all()
                .statusCode(200)
                .body("UserEmail",equalTo(Properties.user));
        String token = response.then().extract().path("TokenString");

        requestInfo.removeHeader("Authorization");
        requestInfo.setHost(Properties.host + "api/items.json").setBody(body.toString()).setHeader("Token",token);
        response=FactoryRequest.make("post").send(requestInfo);
        response.then()
                .log().all()
                .statusCode(200)
                .body("Content",equalTo(body.get("Content")))
                .body("ProjectId", equalTo(body.get("ProjectId")));
        int idItem = response.then().extract().path("Id");

        body.put("Content","Item Updated Token Class Ahora");
        body.put("Checked",true);
        requestInfo.setHost(Properties.host+"api/items/"+idItem+".json").setBody(body.toString());
        response=FactoryRequest.make("put").send(requestInfo);
        response.then()
                .log().all()
                .statusCode(200)
                .body("Content",equalTo(body.get("Content")))
                .body("ProjectId", equalTo(body.get("ProjectId")))
                .body("Checked", equalTo(body.get("Checked")));

        response=FactoryRequest.make("get").send(requestInfo);
        response.then()
                .log().all()
                .statusCode(200)
                .body("Content",equalTo(body.get("Content")))
                .body("ProjectId", equalTo(body.get("ProjectId")))
                .body("Checked", equalTo(body.get("Checked")));

        response=FactoryRequest.make("delete").send(requestInfo);
        response.then()
                .log().all()
                .statusCode(200)
                .body("Content",equalTo(body.get("Content")))
                .body("ProjectId", equalTo(body.get("ProjectId")))
                .body("Checked", equalTo(body.get("Checked")))
                .body("Deleted",equalTo(true));

    }
}
