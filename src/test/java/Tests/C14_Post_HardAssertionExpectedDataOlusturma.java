package Tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C14_Post_HardAssertionExpectedDataOlusturma {

    @Test
    public void test01(){

        String url = "https://restful-booker.herokuapp.com/booking";

        JSONObject requestBody = new JSONObject();
        JSONObject rezTarihleriJson = new JSONObject();

        rezTarihleriJson.put("checkin", "2021-06-01");
        rezTarihleriJson.put("checkout", "2021-06-10");

        requestBody.put("firstname", "Ahmet");
        requestBody.put("lastname", "Bulut");
        requestBody.put("totalprice", 500);
        requestBody.put("depositpaid", false);
        requestBody.put("bookingdates", rezTarihleriJson);
        requestBody.put("additionalneeds", "wi-fi");

        System.out.println(requestBody.toString());

        // expected data olustur

        JSONObject expectedData = new JSONObject();
        expectedData.put("bookingid", 24);
        expectedData.put("booking", requestBody);

        // request gonderip donen response'i kaydet

        Response response = given().contentType(ContentType.JSON)
                .when().body(requestBody.toString())
                .post(url);

        // Assertion

        JsonPath responseJsonPath = response.jsonPath();

        // ilk yazilan expected ===> olusturdugumuz JSonObject : expectedData
        // ikinci olan actual ====> response : responseJsonPath
        assertEquals(expectedData.getJSONObject("booking").get("firstname"),
                                      responseJsonPath.get("booking.firstname"));

        assertEquals(expectedData.getJSONObject("booking").get("lastname"),
                                      responseJsonPath.get("booking.lastname"));

        assertEquals(expectedData.getJSONObject("booking").get("totalprice"),
                                      responseJsonPath.get("booking.totalprice"));

        assertEquals(expectedData.getJSONObject("booking").get("depositpaid"),
                                      responseJsonPath.get("booking.depositpaid"));

        assertEquals(expectedData.getJSONObject("booking").get("additionalneeds"),
                                      responseJsonPath.get("booking.additionalneeds"));

        assertEquals(expectedData.getJSONObject("booking").getJSONObject("bookingdates").get("checkin"),
                                      responseJsonPath.get("booking.bookingdates.checkin"));

        assertEquals(expectedData.getJSONObject("booking").getJSONObject("bookingdates").get("checkout"),
                responseJsonPath.get("booking.bookingdates.checkout"));



    }

}
