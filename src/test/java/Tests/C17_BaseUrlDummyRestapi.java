package Tests;

import BaseUrl.BaseUrlJsonPlaceholder;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C17_BaseUrlDummyRestapi extends BaseUrlJsonPlaceholder {

    /* Class icinde 3 test methodu olusturun ve asagidaki testleri yapin

    3-https://jsonplaceholder.typicode.com/posts/50 endpointine bir delete request gonderdigimizde donen response'un
    status code'unun 200 oldugunu ve response body'sinin null oldugunu test edin

     */

    @Test
    public void test01(){
        /* Class icinde 3 test methodu olusturun ve asagidaki testleri yapin
            1-https://jsonplaceholder.typicode.com/posts endpointine bir get request gonderdigimizde
            donen response'un status code'unun 200 oldugunu ve Response'ta 100 kayit oldugunu test edin
        */

        // 1- endpoint
        specJsonPlaceholder.pathParam("pp1", "posts");  // pp1 ismini kafamıza göre verebiliriz

        // 2- request gonder ve donen response'u kaydet
        Response response = given()
                            .when().spec(specJsonPlaceholder)
                            .get("/{pp1}");

        response.then().assertThat().statusCode(200).body("title", Matchers.hasSize(200));

    }

    @Test
    public void test02(){

         /*
            2-https://jsonplaceholder.typicode.com/posts/44 endpointine bir get request gonderdigimizde donen response'un
            status code'unun 200 oldugunu ve title degerinin "optio dolor molestias sit" oldugunu test edin
         */

        specJsonPlaceholder.pathParams("pp1", "posts", "pp2", 44);

        Response response = given()
                .when().spec(specJsonPlaceholder)
                .get("/{pp1}/{pp2}");

        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("title", Matchers.equalTo("optio dolor molestias sit"));


    }
    @Test
    public void test03(){

        /*
            3-https://jsonplaceholder.typicode.com/posts/50 endpointine bir delete request gonderdigimizde donen response'un
            status code'unun 200 oldugunu ve response body'sinin null oldugunu test edin
         */

        specJsonPlaceholder.pathParams("pp1","posts", "pp2", "50");


        Response response = given()
                            .when().spec(specJsonPlaceholder)
                            .delete("/{pp1}/{pp2}");

        response.prettyPrint();

        response.then().assertThat().statusCode(200).body("id", Matchers.nullValue());

    }




}
