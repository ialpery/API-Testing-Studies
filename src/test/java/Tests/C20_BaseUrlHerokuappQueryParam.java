package Tests;

import BaseUrl.BaseUrlHerokuapp;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C20_BaseUrlHerokuappQueryParam extends BaseUrlHerokuapp {

    @Test
    public void test01(){

        /*

        https://restful-booker.herokuapp.com/booking endpointine gerekli Query parametrelerini yazarak
        "firstname" degeri "Eric" olan rezervasyon oldugunu test edecek bir GET request gonderdigimizde,
        donen response'un status kodunun 200 oldugunu ve "Eric" ismine sahip en az bir booking oldugunu test edin

         */

        specHerokuapp
                .pathParam("pp1", "booking")
                .queryParam("firstname", "Eric");

        // requesti gonder ve response'u kaydet
        Response response = given()
                            .when().spec(specHerokuapp)
                            .get("/{pp1}");

        response.prettyPrint();

        // Assertions
        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("firstname", Matchers.hasSize(5));
    }
}
