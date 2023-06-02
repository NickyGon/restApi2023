package apiTestItems;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CRUDItemTest {

    @Test
    public void verifyCRUDItem() {
        int projectId=4114346; //Para no crear un proyecto cada vez. Usando uno de los existentes
        int itemId;
        JSONObject body = new JSONObject();
        body.put("Content","Item para la casa 3");
        body.put("ProjectId",projectId);
        //CREATE
        Response response=given()
                .auth()
                .preemptive()
                .basic("nikita27111@hotmail.com", "estoesesparta")
                .header("Content-Type", "application/json")
                .body(body.toString())
                .log().all()
                .when()
                .post("https://todo.ly/api/items.json");

        response.then()
                .log().all()
                .statusCode(200)
                .body("Content", equalTo(body.get("Content")))
                .body("ProjectId", equalTo(body.get("ProjectId")));

        itemId = response.then().extract().path("Id");

        //Update
        body.remove("Content");
        body.put("Content","NewTitleAdded3");
        body.put("Checked",true);

        Response response2=given()
                .auth()
                .preemptive()
                .basic("nikita27111@hotmail.com", "estoesesparta")
                .header("Content-Type", "application/json")
                .body(body.toString())
                .log().all()
                .when()
                .put("https://todo.ly/api/items/"+itemId+".json");

        response2.then()
                .log().all()
                .statusCode(200)
                .body("Content", equalTo(body.get("Content")))
                .body("ProjectId", equalTo(body.get("ProjectId")))
                .body("Checked", equalTo(body.get("Checked")));

        Response response3=given()
                .auth()
                .preemptive()
                .basic("nikita27111@hotmail.com", "estoesesparta")
                .header("Content-Type", "application/json")
                .log().all()
                .when()
                .get("https://todo.ly/api/items/"+itemId+".json");

        response3.then()
                .log().all()
                .statusCode(200)
                .body("Content", equalTo(body.get("Content")))
                .body("ProjectId", equalTo(body.get("ProjectId")))
                .body("Checked", equalTo(body.get("Checked")));

        Response response4=given()
                .auth()
                .preemptive()
                .basic("nikita27111@hotmail.com", "estoesesparta")
                .header("Content-Type", "application/json")
                .log().all()
                .when()
                .delete("https://todo.ly/api/items/"+itemId+".json");

        response4.then()
                .log().all()
                .statusCode(200)
                .body("Content", equalTo(body.get("Content")))
                .body("ProjectId", equalTo(body.get("ProjectId")))
                .body("Checked", equalTo(body.get("Checked")))
                .body("Deleted",equalTo(true));
    }
}
