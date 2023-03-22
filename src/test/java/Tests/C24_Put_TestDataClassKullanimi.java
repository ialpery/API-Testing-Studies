package Tests;

import BaseUrl.BaseUrlJsonPlaceholder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testDatalari.TestDataJsonPlaceholder;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C24_Put_TestDataClassKullanimi extends BaseUrlJsonPlaceholder {

    @Test
    public void test01(){

        /*

        https://jsonplaceholder.typicode.com/posts/70 urline asagidaki body'e sahip bir PUT request
        yolladigimizda donen response'un status kodunun 200, content type'ının "application/json; charset=utf-8",
        connection header degerinin "keep-alive" ve response bodysinin asagidaki ile ayni oldugunu test ediniz.

        Request Body
        {

        "title":"Ahmet",
        "body":"Merhaba",
        "userId":10,
        "id":70

        Response Body (Expected Data)
        {

        "title":"Ahmet",
        "body":"Merhaba",
        "userId":10,
        "id":70

        }

        }

         */

        // endpoint
        specJsonPlaceholder.pathParams("pp1", "posts", "pp2", 70);

        // request body olustur
        JSONObject requestBody = TestDataJsonPlaceholder.responseJsonBody(10,70,"Ahmet", "Merhaba");

        //expected data olustur
        JSONObject expectedData = TestDataJsonPlaceholder.responseJsonBody(10, 70, "Ahmet", "Merhaba");

        // request gonder ve donen response'ı kaydet
        Response response = given().spec(specJsonPlaceholder)
                            .when().body(requestBody.toString()).contentType(ContentType.JSON)
                            .put("/{pp1}/{pp2}");

        // Assertions
        JsonPath responseJsonpath = response.jsonPath();

        assertEquals(TestDataJsonPlaceholder.basariliStatusCode, response.statusCode());
        assertEquals(TestDataJsonPlaceholder.contentType, response.contentType());
        assertEquals(TestDataJsonPlaceholder.headerConnection,response.header("Connection"));

        assertEquals(expectedData.getInt("id"), responseJsonpath.getInt("id"));
        assertEquals(expectedData.getInt("userId"), responseJsonpath.getInt("userId"));
        assertEquals(expectedData.getString("title"), responseJsonpath.getString("title"));
        assertEquals(expectedData.getString("body"), responseJsonpath.getString("body"));



    }

}
