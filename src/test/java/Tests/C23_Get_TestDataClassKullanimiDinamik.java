package Tests;

import BaseUrl.BaseUrlJsonPlaceholder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testDatalari.TestDataJsonPlaceholder;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C23_Get_TestDataClassKullanimiDinamik extends BaseUrlJsonPlaceholder {


    @Test
    public void test01(){

        /*
        https://jsonplaceholder.typicode.com/posts/40 urline bir GET request yolladigimizda donen response'ın status
        kodunun 200 ve response bodysinin asagida verilen ile ayni oldugunu test ediniz.

        Response body :
        {
            "userId": 4,
            "id": 40,
            "title": "enim quo cumque",
            "body": "ut voluptatum aliquid illo tenetur nemo sequi quo facilis\nipsum rem optio mollitia quas\nvoluptatem eum voluptas qui\nunde omnis voluptatem iure quasi maxime voluptas nam"
        }

         */

        // endpoint
        specJsonPlaceholder.pathParams("pp1","posts", "pp2", "40");

        // expected data olustur
        JSONObject expectedData = TestDataJsonPlaceholder.responseJsonBody(4,40,"enim quo cumque", "ut voluptatum aliquid illo tenetur nemo sequi quo facilis\nipsum rem optio mollitia quas\nvoluptatem eum voluptas qui\nunde omnis voluptatem iure quasi maxime voluptas nam");

        //request goonder ve donen response'ı kaydet
        Response response = given()
                            .when().spec(specJsonPlaceholder)
                            .get("/{pp1}/{pp2}");

        // Assertions

        JsonPath responseJsonpath = response.jsonPath();

        assertEquals(TestDataJsonPlaceholder.basariliStatusCode, response.statusCode());
        assertEquals(expectedData.getInt("userId"), responseJsonpath.getInt("userId"));
        assertEquals(expectedData.getInt("id"), responseJsonpath.getInt("id"));
        assertEquals(expectedData.getString("title"), responseJsonpath.getString("title"));
        assertEquals(expectedData.getString("body"), responseJsonpath.getString("body"));




    }
}
