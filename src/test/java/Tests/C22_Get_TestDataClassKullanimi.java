package Tests;

import BaseUrl.BaseUrlJsonPlaceholder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testDatalari.TestDataJsonPlaceholder;

import static io.restassured.RestAssured.given;

public class C22_Get_TestDataClassKullanimi extends BaseUrlJsonPlaceholder {

    @Test
    public void test01(){

        /*

        https://jsonplaceholder.typicode.com/posts/22 urline bir GET request yolladigimizda donen response'un
        status kodunun 200 ve response bodysinin asagida verilen ile ayni oldugunu test ediniz.

        Response body :
        {
            "userId": 3,
            "id": 22,
            "title": "dolor sint quo a velit explicabo quia nam",
            "body": "eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita earum mollitia molestiae aut atque rem suscipit\nnam impedit esse"
        }

         */

        // endpoint
        specJsonPlaceholder.pathParams("pp1", "posts", "pp2", "22");

        // expected data olustur
        JSONObject expectedData = TestDataJsonPlaceholder.responseBodyforid22();
        // System.out.println(expectedData);

        // request gonder ve donen response'u kaydet
        Response response = given().spec(specJsonPlaceholder)
                            .when()
                            .get("/{pp1}/{pp2}");
        response.prettyPrint();

        // Assertions
        JsonPath responseJsonpath = response.jsonPath();

        Assert.assertEquals(TestDataJsonPlaceholder.basariliStatusCode, response.statusCode());
        Assert.assertEquals(expectedData.getInt("userId"), responseJsonpath.getInt("userId"));
        Assert.assertEquals(expectedData.getInt("id"), responseJsonpath.getInt("id"));
        Assert.assertEquals(expectedData.getString("title"), responseJsonpath.getString("title"));
        Assert.assertEquals(expectedData.getString("body"), responseJsonpath.getString("body"));



    }

}
