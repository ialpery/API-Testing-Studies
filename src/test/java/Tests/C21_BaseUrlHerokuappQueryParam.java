package Tests;

import BaseUrl.BaseUrlHerokuapp;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class C21_BaseUrlHerokuappQueryParam extends BaseUrlHerokuapp {

    @Test
    public void test01(){

        /*

        https://restful-booker.herokuapp.com/booking endpointine gerekli Query parametrelerini yazarak
        "firstname" degeri "Eric" ve "lastname" degeri "Jones" olan rezervasyon oldugunu test edecek bir GET
        request gonderdigimizde, donen response'un status kodunun 200 oldugunu ve "Eric Jones" isimli
        en az bir booking oldugunu test edin

         */

        // endpoint
        specHerokuapp
                .pathParam("pp1", "booking")
                .queryParams("firstname", "Alper", "lastname", "Yigit");

        // request gonder ve response kaydet
        Response response = given()
                            .when().spec(specHerokuapp)
                            .get("/{pp1}");

        response.prettyPrint();

        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("bookingid", hasSize(1));

    }

}
