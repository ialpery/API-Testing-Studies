package testDatalari;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TestDataHerokuapp {

    /*
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
     */

    public static JSONObject jsonRequestBody(String firstname, String lastname,
                                             int totalprice, boolean depositpaid,
                                             String checkin, String checkout, String additionalneeds )
    {

        JSONObject requestBody = new JSONObject();
        JSONObject bookingDatesBody = new JSONObject();

        bookingDatesBody.put("checkin",checkin);
        bookingDatesBody.put("checkout", checkout);

        requestBody.put("firstname", firstname);
        requestBody.put("lastname", lastname);
        requestBody.put("totalprice", totalprice);
        requestBody.put("depositpaid", depositpaid);
        requestBody.put("bookingdates", bookingDatesBody);
        requestBody.put("additionalneeds", additionalneeds);

        return requestBody;

    }

    public static JSONObject jsonResponseBody(){

        /*
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

        JSONObject responseBody = new JSONObject();
        JSONObject bookingBody = jsonRequestBody("Ahmet", "Bulut",
                                                 500, false,
                                                 "2021-06-01", "2021-06-10",
                                                 "wi-fi");

        responseBody.put("bookingid", 24);
        responseBody.put("booking", bookingBody);

        return responseBody;

    }
    //--------------------------------------- Asagisi map ornekleri icin -----------------------------------//

        /*
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
     */

    public static Map<String, Object> requestBodyMap(){
        Map<String, Object> requestBodyMap = new HashMap<>();
        requestBodyMap.put("firstname", "Ahmet");
        requestBodyMap.put("lastname", "Bulut");
        requestBodyMap.put("totalprice", 500.0);
        requestBodyMap.put("depositpaid", false);
        requestBodyMap.put("bookingdates", bookingDatesMap());
        requestBodyMap.put("additionalneeds", "wi-fi");

        return requestBodyMap;

    }
    public static Map<String,String> bookingDatesMap(){

        Map<String,String> bookingDatesMap = new HashMap<>();

        bookingDatesMap.put("checkin", "2021-06-01");
        bookingDatesMap.put("checkout", "2021-06-10");

        return bookingDatesMap;

    }

    /*
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

    public static Map<String,Object> responseBodyMap(){

        Map<String,Object> responseBodyMap = new HashMap<>();
        responseBodyMap.put("bookingid", 24.0);
        responseBodyMap.put("booking", requestBodyMap());

        return responseBodyMap;

    }

}
