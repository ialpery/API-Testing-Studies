package Tests;

import BaseUrl.BaseUrlHerokuapp;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C19_BaseUrlHerokuapp2 extends BaseUrlHerokuapp {

    @Test
    public void test01(){

        /*
        https://restfull-booker.herokuapp/booking endpointine asagidaki body'ye sahip bir post gonderdigimizde
        donen response'un status kodunun 200 oldugunu ve firstname degerinin Ahmet oldugunu test edin

        {
        "firstname" : "Ahmet",
        "lastname"  : "Bulut",
        "totalprice" : 500,
        "depositpaid" : false,
        "bookingdates" : {
                    "checkin" : "2021-06-01"
                    "checkout" : "2021-06-10"
                        },
         "additionalneeds" : "wi-fi" }

         */

        // endpoint
        specHerokuapp.pathParam("pp1", "booking");

        //request body olustur

        JSONObject requestBody = new JSONObject();
        JSONObject rezTarihleri = new JSONObject();

        rezTarihleri.put("checkin", "2021-06-01");
        rezTarihleri.put("checkout", "2021-06-10");

        requestBody.put("firstname", "Ahmet");
        requestBody.put("lastname", "Bulut");
        requestBody.put("totalprice", 500);
        requestBody.put("depositpaid", false);
        requestBody.put("bookingdates", rezTarihleri);
        requestBody.put("additionalneeds", "wi-fi");

        // requesti gonder ve response'u kaydet

        Response response = given().contentType(ContentType.JSON)
                .when().spec(specHerokuapp).body(requestBody.toString())
                .post("/{pp1}");

        //Assertions

        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("booking.firstname", Matchers.equalTo("Ahmet"));


    }

}
