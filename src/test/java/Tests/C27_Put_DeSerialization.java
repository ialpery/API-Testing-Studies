package Tests;

import BaseUrl.BaseUrlJsonPlaceholder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import testDatalari.TestDataJsonPlaceholder;

import java.util.HashMap;
import java.util.Map;


import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C27_Put_DeSerialization extends BaseUrlJsonPlaceholder {

    /*

    https://jsonplaceholder.typicode.com/posts/70 urline asagidaki body'e sahip bir PUT request yolladigimizda donen
    response'ın response bodysinin asagida verilen ile ayni oldugunu test ediniz

    Request Body

    {
    "title": "Ahmet",
    "body": "Merhaba"
    "userId": 10,
    "id": 70,
    }

    Expected Response Body

    {
    "title": "Ahmet",
    "body": "Merhaba"
    "userId": 10,
    "id": 70,
    }

     */
    @Test
    public void test01(){

        // endpoint
        specJsonPlaceholder.pathParams("pp1", "posts", "pp2", 70);

        // Request bodysini map olarak olusturalim
        Map<String, Object> requestBodyMap = TestDataJsonPlaceholder.bodyOlusturMap();


        // expected data olustur
        Map<String, Object> expectedData = TestDataJsonPlaceholder.bodyOlusturMap();

        // request gonder ve response'u kaydet
        Response response = given().spec(specJsonPlaceholder).contentType(ContentType.JSON)
                            .when().body(requestBodyMap)
                            .put("/{pp1}/{pp2}");

        //Assertion yapabilmemiz icin response'u map'e cevirmeliyiz.(deserialization)
        Map<String,Object> responseMap = response.as(HashMap.class);

        assertEquals(expectedData.get("title"), responseMap.get("title"));
        assertEquals(expectedData.get("body"), responseMap.get("body"));
        assertEquals(expectedData.get("id"), responseMap.get("id"));
        assertEquals(expectedData.get("userId"), responseMap.get("userId"));

    }

}
