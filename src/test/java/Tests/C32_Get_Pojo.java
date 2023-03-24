package Tests;

import BaseUrl.BaseUrlDummy;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.PojoDummyExampleData;
import pojos.PojoDummyExampleResponse;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C32_Get_Pojo extends BaseUrlDummy {


    /*

        https://dummy.restapiexample.com/api/v1/employee/3 urline bir GET request gonderdigimizde donen
        response'un status code'unun 200, content type'inin application/json ve body'sinin asagidaki gibi
        oldugunu test edin.

        Response Body
        (expected data)
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

        // request url olustur
        specDummy.pathParams("pp1", "employee", "pp2", 3);

        // expected data olustur
        PojoDummyExampleData dataPojo =
                new PojoDummyExampleData(3,"Ashton Cox", 86000,
                                         66,"");

        PojoDummyExampleResponse expectedResponseBody = new PojoDummyExampleResponse("success",dataPojo,
                                                                                     "Successfully! Record has been fetched.");

        // request gonder ve donen response'ı kaydet
        Response response = given().spec(specDummy)
                            .when()
                            .get("/{pp1}/{pp2}");

        // Assertions

        // hazir ceviriciler attribute isimlerini degistirdigi icin response'ı pojoya convert edemedik
        // bu durumda response'ı jsonpathe cevirebiliriz.

        JsonPath responseJsonpath = response.jsonPath();
        assertEquals(expectedResponseBody.getStatus(), responseJsonpath.getString("status"));
        assertEquals(expectedResponseBody.getMessage(), responseJsonpath.getString("message"));

        assertEquals(expectedResponseBody.getData().getId(),
                     responseJsonpath.get("data.id"));
        assertEquals(expectedResponseBody.getData().getEmployeeName(),
                     responseJsonpath.get("data.employee_name"));
        assertEquals(expectedResponseBody.getData().getEmployeeAge(),
                     responseJsonpath.get("data.employee_age"));
        assertEquals(expectedResponseBody.getData().getEmployeeSalary(),
                     responseJsonpath.get("data.employee_salary"));
        assertEquals(expectedResponseBody.getData().getProfileImage(),
                     responseJsonpath.get("data.profile_image"));

    }


}
