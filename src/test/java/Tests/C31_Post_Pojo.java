package Tests;

import BaseUrl.BaseUrlHerokuapp;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.PojoHerokuappBookingdates;
import pojos.PojoHerokuappRequestBody;
import pojos.PojoHerokuappResponseBody;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C31_Post_Pojo extends BaseUrlHerokuapp {

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
        PojoHerokuappBookingdates bookingdatesPojo =
                new PojoHerokuappBookingdates("2021-06-01", "2021-06-10");

        PojoHerokuappRequestBody requestBodyPojo =
                new PojoHerokuappRequestBody("Ahmet", "Bulut", 500,
                                             false, bookingdatesPojo, "wi-fi");

        // expected data olustur
        bookingdatesPojo = new PojoHerokuappBookingdates("2021-06-01", "2021-06-10");
        PojoHerokuappRequestBody bookingPojo = new PojoHerokuappRequestBody("Ahmet", "Bulut",
                                                                            500, false,
                                                                             bookingdatesPojo, "wi-fi");

        PojoHerokuappResponseBody expectedResponseBodyPojo =
                new PojoHerokuappResponseBody(24, bookingPojo);

        // request gonder ve response'ı kaydet
        Response response = given().spec(specHerokuapp).contentType(ContentType.JSON)
                            .when().body(requestBodyPojo)
                            .post("/{pp1}");


        // Assertions
        // expectedResponseBody <===> actual response'ı da pojoya cevirmeliyiz
        PojoHerokuappResponseBody responsePojo = response.as(PojoHerokuappResponseBody.class);

        assertEquals(expectedResponseBodyPojo.getBooking().getFirstname(),
                     responsePojo.getBooking().getFirstname());

        assertEquals(expectedResponseBodyPojo.getBooking().getLastname(),
                     responsePojo.getBooking().getLastname());

        assertEquals(expectedResponseBodyPojo.getBooking().getTotalprice(),
                     responsePojo.getBooking().getTotalprice());

        assertEquals(expectedResponseBodyPojo.getBooking().isDepositpaid(),
                     responsePojo.getBooking().isDepositpaid());

        assertEquals(expectedResponseBodyPojo.getBooking().getAdditionalneeds(),
                     responsePojo.getBooking().getAdditionalneeds());

        assertEquals(expectedResponseBodyPojo.getBooking().getBookingdates().getCheckin(),
                     responsePojo.getBooking().getBookingdates().getCheckin());

        assertEquals(expectedResponseBodyPojo.getBooking().getBookingdates().getCheckout(),
                     responsePojo.getBooking().getBookingdates().getCheckout());

    }

}
