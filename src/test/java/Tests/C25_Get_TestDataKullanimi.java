package Tests;

import BaseUrl.BaseUrlDummy;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testDatalari.TestDataDummy;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C25_Get_TestDataKullanimi extends BaseUrlDummy {

    @Test
    public void test01(){

        /*

        https://dummy.restapiexample.com/api/v1/employee/3 urline bir GET request gonderdigimizde donen
        response'un status code'unun 200, content type'inin application/json ve body'sinin asagidaki gibi
        oldugunu test edin.

        Response Body

        {
            "status": "success",
            "data": {
                "id": 3,
                "employee_name": "Ashton Cox",
                "employee_salary": 86000,
                "employee_age": 66,
                "profile_image": ""
            },
            "message": "Successfully! Record has been fetched."
        }

         */
        //request

        specDummy.pathParams("pp1","employee", "pp2", "3");


        // expected data olustur
        JSONObject expectedData = TestDataDummy.jsonResponseBodyOlustur(3, "Ashton Cox",
                                                                        86000, 66,
                                                                        "");

        // request gonder ve donen response'u kaydet
        Response response = given().spec(specDummy)
                            .when()
                            .get("/{pp1}/{pp2}");

        // Assertion
        // Expected data : JSONObject
        // response : JsonPath    // data.id şeklinde ulasabilmek icin Jsonpathe ceviriypruz

        JsonPath responseJsonpath = response.jsonPath();

        assertEquals(TestDataDummy.basariliStatusCode, response.statusCode());
        assertEquals(TestDataDummy.contentType, response.contentType());



        assertEquals(expectedData.getJSONObject("data").getInt("id"),
                responseJsonpath.getInt("data.id"));

        assertEquals(expectedData.getJSONObject("data").getString("employee_name"),
                responseJsonpath.getString("data.employee_name"));

        assertEquals(expectedData.getJSONObject("data").getInt("employee_salary"),
                responseJsonpath.getInt("data.employee_salary"));

        assertEquals(expectedData.getJSONObject("data").getInt("employee_age"),
                responseJsonpath.getInt("data.employee_age"));

        assertEquals(expectedData.getJSONObject("data").getString("profile_image"),
                responseJsonpath.getString("data.profile_image"));

        assertEquals(expectedData.getString("message"), responseJsonpath.getString("message"));
        assertEquals(expectedData.getString("status"), responseJsonpath.getString("status"));










    }
}
