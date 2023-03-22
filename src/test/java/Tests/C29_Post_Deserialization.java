package Tests;

import BaseUrl.BaseUrlHerokuapp;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testDatalari.TestDataHerokuapp;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C29_Post_Deserialization extends BaseUrlHerokuapp {

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
        @Test
        public void test01(){

            // endpoint
            specHerokuapp.pathParam("pp1", "booking");

            // request body
            Map<String, Object>  requestBodyMap = TestDataHerokuapp.requestBodyMap();

            // expected data olustur
            Map<String, Object> expectedData = TestDataHerokuapp.responseBodyMap();

            // Request gonder ve response'u kaydet
            Response response = given().spec(specHerokuapp).contentType(ContentType.JSON)
                    .when().body(requestBodyMap)
                    .post("/{pp1}");

            // Assertions
            Map<String,Object> responseMap = response.as(HashMap.class);

            assertEquals(((Map)expectedData.get("booking")).get("firstname"),
                        ((Map)responseMap.get("booking")).get("firstname"));

            assertEquals(((Map)expectedData.get("booking")).get("lastname"),
                    ((Map)responseMap.get("booking")).get("lastname"));

            assertEquals(((Map)expectedData.get("booking")).get("totalprice"),
                    ((Map)responseMap.get("booking")).get("totalprice"));

            assertEquals(((Map)expectedData.get("booking")).get("depositpaid"),
                    ((Map)responseMap.get("booking")).get("depositpaid"));

            assertEquals(((Map)((Map)expectedData.get("booking")).get("bookingdates")).get("checkin"),
                    ((Map)((Map)responseMap.get("booking")).get("bookingdates")).get("checkin"));

            assertEquals(((Map)((Map)expectedData.get("booking")).get("bookingdates")).get("checkout"),
                    ((Map)((Map)responseMap.get("booking")).get("bookingdates")).get("checkout"));

            assertEquals(((Map)expectedData.get("booking")).get("additionalneeds"),
                    ((Map)responseMap.get("booking")).get("additionalneeds"));


        }
}
