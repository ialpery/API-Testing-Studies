package Tests;

import BaseUrl.BaseUrlJsonPlaceholder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.PojoJsonPlaceholder;
import testDatalari.TestDataJsonPlaceholder;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C30_Put_PojoClass extends BaseUrlJsonPlaceholder {

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

    @Test
    public void test01(){

        // endpoint
        specJsonPlaceholder.pathParams("pp1", "posts", "pp2", 70);

        // request body
        PojoJsonPlaceholder requestBodyPojo =
                new PojoJsonPlaceholder("Ahmet", "Merhaba", 10, 70);

        // expected response body olustur
        PojoJsonPlaceholder expectedDataPojo =
                new PojoJsonPlaceholder("Ahmet", "Merhaba", 10, 70);

        // request gonderip response'u kaydet
        Response response = given().spec(specJsonPlaceholder).contentType(ContentType.JSON)
                .when().body(requestBodyPojo)
                .put("/{pp1}/{pp2}");

        // Assertions
        // once response'ı pojoya çevirmeliyiz, cunku requestimiz pojo
        PojoJsonPlaceholder responsePojo = response.as(PojoJsonPlaceholder.class);

        assertEquals(TestDataJsonPlaceholder.basariliStatusCode, response.getStatusCode());
        assertEquals(TestDataJsonPlaceholder.contentType, response.contentType());
        assertEquals(TestDataJsonPlaceholder.headerConnection, response.header("Connection"));

        assertEquals(expectedDataPojo.getTitle(), responsePojo.getTitle());
        assertEquals(expectedDataPojo.getBody(), responsePojo.getBody());
        assertEquals(expectedDataPojo.getUserId(), responsePojo.getUserId());
        assertEquals(expectedDataPojo.getId(), responsePojo.getId());

    }

}
