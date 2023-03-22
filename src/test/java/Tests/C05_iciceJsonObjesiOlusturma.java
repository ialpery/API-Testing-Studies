package Tests;

import org.json.JSONObject;
import org.junit.Test;

public class C05_iciceJsonObjesiOlusturma {

    @Test
    public void test01() {

        // once inner jason objesi olusturalim
        JSONObject date = new JSONObject();

        date.put("checkin", "2018-01-01");
        date.put("checkout", "2019-01-01");

        // outer json objesi olusturalim
        JSONObject requestBody = new JSONObject();

        requestBody.put("firstname", "Jim");
        requestBody.put("additionalneeds", "Breakfast");
        requestBody.put("bookingdates", date);
        requestBody.put("totalprice", 111);
        requestBody.put("depositpaid", true);
        requestBody.put("lastname", "Brown");

        System.out.println(requestBody);
    }
}
