package testDatalari;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TestDataDummy {

    public static int basariliStatusCode = 200;
    public static String contentType = "application/json";

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

        public static JSONObject jsonResponseBodyOlustur(int id, String employee_name, int employee_salary,
                                                         int employee_age, String profile_image){

            JSONObject responseBody = new JSONObject();
            JSONObject dataBody = new JSONObject();

            dataBody.put("id", id);
            dataBody.put("employee_name", employee_name);
            dataBody.put("employee_salary", employee_salary);
            dataBody.put("employee_age", employee_age);
            dataBody.put("profile_image", profile_image);

            responseBody.put("status", "success");
            responseBody.put("data", dataBody);
            responseBody.put("message", "Successfully! Record has been fetched.");

            return responseBody;

        }

    public static Map<String,Object> mapResponseBodyOlustur(double id, String employee_name, double employee_salary,
                                              double employee_age, String profile_image){

        Map<String,Object> bodyMap = new HashMap<>();
        Map<String,Object> dataMap = new HashMap<>();

        dataMap.put("id", id);
        dataMap.put("employee_name", employee_name);
        dataMap.put("employee_salary", employee_salary);
        dataMap.put("employee_age", employee_age);
        dataMap.put("profile_image", profile_image);

        bodyMap.put("status", "success");
        bodyMap.put("data", dataMap);
        bodyMap.put("message", "Successfully! Record has been fetched.");

        return bodyMap;
    }
}
