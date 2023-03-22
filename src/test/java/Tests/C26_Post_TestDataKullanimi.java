package Tests;

import BaseUrl.BaseUrlHerokuapp;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testDatalari.TestDataHerokuapp;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C26_Post_TestDataKullanimi extends BaseUrlHerokuapp {

    @Test
    public void test01(){

        /*

        https://restful-booker.herokuapp.com/booking urline asagidaki body'ye sahip bir POST request
        gonderdigimizde donen response'un id haric asagidaki gibi oldugunu test edin.

        Request Body

        {
            "firstname": "Ahmet",
            "lastname": "Bulut",
            "totalprice": 500,
            "depositpaid": false,
            "bookingdates": {
                "checkin": "2021-06-01",
                "checkout": "2021-06-10"
            },
            "additionalneeds": "wi-fi"
        }

        Response Body
        {
            "bookingid":24
            "booking": {
                "firstname": "Ahmet",
                "lastname": "Bulut",
                "totalprice": 500,
                "depositpaid": false,
                "bookingdates": {
                    "checkin": "2021-06-01",
                    "checkout": "2021-06-10"
                    },
                "additionalneeds": "wi-fi"
                }
         }

         */

        // endpoint
        specHerokuapp.pathParams("pp1", "booking");

        // request Body
        JSONObject requestBody = TestDataHerokuapp.jsonRequestBody("Ahmet", "Bulut",
                                                                   500, false,
                                                                    "2021-06-01", "2021-06-10",
                                                                    "wi-fi");

        // expected body
        JSONObject expectedData = TestDataHerokuapp.jsonResponseBody();


        // request gonder ve response'ı kaydet
        Response response = given()
                            .spec(specHerokuapp).contentType(ContentType.JSON)
                            .when().body(requestBody.toString())
                            .post("/{pp1}");


        //Assertions

        JsonPath responseJsonpath = response.jsonPath();

        assertEquals(expectedData.getJSONObject("booking").getString("firstname"),
                    responseJsonpath.getString("booking.firstname"));

        assertEquals(expectedData.getJSONObject("booking").getString("lastname"),
                    responseJsonpath.getString("booking.lastname"));

        assertEquals(expectedData.getJSONObject("booking").getInt("totalprice"),
                    responseJsonpath.getInt("booking.totalprice"));

        assertEquals(expectedData.getJSONObject("booking").getBoolean("depositpaid"),
                    responseJsonpath.getBoolean("booking.depositpaid"));

        assertEquals(expectedData.getJSONObject("booking").getString("additionalneeds"),
                    responseJsonpath.getString("booking.additionalneeds"));

        assertEquals(expectedData.getJSONObject("booking").getJSONObject("bookingdates").getString("checkin"),
                    responseJsonpath.getString("booking.bookingdates.checkin"));

        assertEquals(expectedData.getJSONObject("booking").getJSONObject("bookingdates").getString("checkout"),
                    responseJsonpath.getString("booking.bookingdates.checkout"));







    }
}
