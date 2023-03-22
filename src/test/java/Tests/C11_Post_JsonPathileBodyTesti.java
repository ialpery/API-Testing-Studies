package Tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C11_Post_JsonPathileBodyTesti {


    @Test
    public void test01(){

        String url = "https://restful-booker.herokuapp.com/booking";

        JSONObject requestBody = new JSONObject();
        JSONObject rezervasyonTarihleriJson = new JSONObject();

        // request body oluştur
        rezervasyonTarihleriJson.put("checkin", "2023-01-10");
        rezervasyonTarihleriJson.put("checkout", "2023-01-20");

        requestBody.put("firstname", "Ahmet");
        requestBody.put("lastname", "Bulut");
        requestBody.put("totalprice", 500);
        requestBody.put("depositpaid", false);
        requestBody.put("bookingdates", rezervasyonTarihleriJson);
        requestBody.put("additionalneeds", "wi-fi");

        // requesti gönder
        Response response = given().contentType(ContentType.JSON)
                .when().body(requestBody.toString())
                .post(url);

        response.prettyPrint();

        // assertions

        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("booking.firstname", equalTo("Ahmet"),
                        "booking.lastname", equalTo("Bulut"),
                        "booking.totalprice", equalTo(500),
                        "booking.depositpaid", equalTo(false),
                        "booking.bookingdates.checkin", equalTo("2023-01-10"),
                        "booking.bookingdates.checkout", equalTo("2023-01-20")
                        );

    }
}
