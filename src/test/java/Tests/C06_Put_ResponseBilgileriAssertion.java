package Tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C06_Put_ResponseBilgileriAssertion {

    @Test
    public void test01(){


        // 1- endpoint ve request body hazırla

        String url = "https://jsonplaceholder.typicode.com/posts/70";

        JSONObject requestBody = new JSONObject();

        requestBody.put("title", "Ahmet");
        requestBody.put("body", "merhaba");
        requestBody.put("userId", 10);
        requestBody.put("id", 70);

        Response response = given().contentType(ContentType.JSON)
                                .when().body(requestBody.toString())
                                    .put(url);

        //Assertions

        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .header("Server", "cloudfare")
                .statusLine("HTTP/1.1 200 OK");
    }

}
