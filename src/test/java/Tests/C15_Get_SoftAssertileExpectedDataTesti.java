package Tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class C15_Get_SoftAssertileExpectedDataTesti {

    @Test
    public void test01(){
        /*
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

        // request endpoint
        String url = "http://dummy.restapiexample.com/api/v1/employee/3";

        // expected data olustur
        JSONObject expectedData = new JSONObject();
        JSONObject dataBilgileriJson = new JSONObject();

        dataBilgileriJson.put("id", 3);
        dataBilgileriJson.put("employee_name", "Ashton Cox");
        dataBilgileriJson.put("employee_salary", 86000);
        dataBilgileriJson.put("employee_age", 66);
        dataBilgileriJson.put("profile_image", "");

        expectedData.put("status", "success");
        expectedData.put("data", dataBilgileriJson);
        expectedData.put("message", "Successfully! Record has been fetched.");


        // request gonderip donen response'ı kaydet
        Response response = given().when().get(url);
        response.prettyPrint();
        //Assertion
        // responsetaki bilgileri kolay almak için jsonpathe cast edelim
        JsonPath responseJsonPath = response.jsonPath();

        // soft assertionı olusturalim
        SoftAssert softAssert = new SoftAssert();

        // bu sefer once actual sonra expected yazılır
        softAssert.assertEquals(responseJsonPath.get("status"), expectedData.get("status"));
        softAssert.assertEquals(responseJsonPath.get("message"), expectedData.get("message"));

        softAssert.assertEquals(responseJsonPath.get("data.id"),
                                expectedData.getJSONObject("data").get("id"));

        softAssert.assertEquals(responseJsonPath.get("data.employee_name"),
                                expectedData.getJSONObject("data").get("employee_name"));

        softAssert.assertEquals(responseJsonPath.get("data.employee_salary"),
                                expectedData.getJSONObject("data").get("employee_salary"));

        softAssert.assertEquals(responseJsonPath.get("data.employee_age"),
                                expectedData.getJSONObject("data").get("employee_age"));

        softAssert.assertEquals(responseJsonPath.get("data.profile_image"),
                                expectedData.getJSONObject("data").get("profile_image"));



        softAssert.assertAll();




    }

}
