package Tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C08_Post_ResponseBodyTesti {

    @Test
    public void test01(){

        String url = "https://jsonplaceholder.typicode.com/posts";

        JSONObject requestBody = new JSONObject();
        // body oluşturalım
        requestBody.put("title", "API");
        requestBody.put("body", "API ogrenmek ne guzel");
        requestBody.put("userId", 10);

        // body'i gönderelim
        Response response = given().contentType(ContentType.JSON)
                .when().body(requestBody.toString())
                .post(url);

        // Assertions

        response.then()
                .assertThat()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("title", Matchers.equalTo("API"))
                .body("userId", Matchers.lessThan(100))
                .body("body", Matchers.containsString("API"));

    }
}
