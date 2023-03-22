package Tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class C16_Put_SoftAssertileExpectedDataTesti {

    @Test
    public void test01(){

        // endpoint
        String url = "http://dummy.restapiexample.com/api/v1/update/21";

        // request body olustur
        JSONObject requestBody = new JSONObject();
        JSONObject dataBilgileriJson = new JSONObject();

        dataBilgileriJson.put("name", "Ahmet");
        dataBilgileriJson.put("salary", "1230");
        dataBilgileriJson.put("age", "44");
        dataBilgileriJson.put("id", 40);

        requestBody.put("status", "success");
        requestBody.put("data", dataBilgileriJson);

        //System.out.println(requestBody);

        //expected data olustur
        JSONObject expectedData = new JSONObject();
        expectedData.put("status", "success");
        expectedData.put("data", requestBody);
        expectedData.put("message", "Successfully! Record has been updated");

        // request gonder ve donen response'ı kaydet
        Response response = given().contentType(ContentType.JSON)
                .when().body(requestBody.toString())
                .put(url);

        response.prettyPrint();

        // assertions (softassert)
        JsonPath responseJsonPath = response.jsonPath();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(responseJsonPath.get("status"), expectedData.get("status"));
        softAssert.assertEquals(responseJsonPath.get("message"), expectedData.get("message"));

        softAssert.assertEquals(responseJsonPath.get("data.status"),
                expectedData.getJSONObject("data").get("status"));

        softAssert.assertEquals(responseJsonPath.get("data.data.name"),
                expectedData.getJSONObject("data").getJSONObject("data").get("name"));

        softAssert.assertEquals(responseJsonPath.get("data.data.id"),
                expectedData.getJSONObject("data").getJSONObject("data").get("id"));

        softAssert.assertEquals(responseJsonPath.get("data.data.salary"),
                expectedData.getJSONObject("data").getJSONObject("data").get("salary"));

        softAssert.assertEquals(responseJsonPath.get("data.data.age"),
                expectedData.getJSONObject("data").getJSONObject("data").get("age"));

        softAssert.assertAll();




    }

}
