package Tests;

import BaseUrl.BaseUrlDummy;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testDatalari.TestDataDummy;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C28_Get_DeSerialization extends BaseUrlDummy {

    /*

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
    @Test
    public void test01(){

        // endpoint
        specDummy.pathParams("pp1", "employee", "pp2", 3);

        // expected data
        Map<String, Object> expectedData = TestDataDummy.mapResponseBodyOlustur(3.0, "Ashton Cox",
                                                                                86000.0, 66.0,
                                                                                "");

        // request gonder ve donen response'u kaydet
        Response response = given().spec(specDummy)
                            .when().contentType(ContentType.JSON)
                            .get("/{pp1}/{pp2}");

        Map<String,Object> responseMap = response.as(HashMap.class);

        // Assertions
        assertEquals(TestDataDummy.basariliStatusCode, response.statusCode());
        assertEquals(TestDataDummy.contentType, response.contentType());

        assertEquals(expectedData.get("message"), responseMap.get("message"));
        assertEquals(expectedData.get("status"), responseMap.get("status"));

        assertEquals(((Map)expectedData.get("data")).get("id"),
                ((Map)responseMap.get("data")).get("id"));

        assertEquals(((Map)expectedData.get("data")).get("employee_name"),
                ((Map)responseMap.get("data")).get("employee_name"));

        assertEquals(((Map)expectedData.get("data")).get("employee_salary"),
                ((Map)responseMap.get("data")).get("employee_salary"));

        assertEquals(((Map)expectedData.get("data")).get("employee_age"),
                ((Map)responseMap.get("data")).get("employee_age"));

        assertEquals(((Map)expectedData.get("data")).get("profile_image"),
                ((Map)responseMap.get("data")).get("profile_image"));

    }
}
