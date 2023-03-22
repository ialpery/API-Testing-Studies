package Tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C13_Get_HardAssertionExpectedDataOlusturma {

    @Test
    public void get01(){

        // endpoint hazirla
        String url = "https://jsonplaceholder.typicode.com/posts/22";

        // expected data olustur
        JSONObject expectedData = new JSONObject();
        expectedData.put("userId", 3);
        expectedData.put("id", 22);
        expectedData.put("title", "dolor sint quo a velit explicabo quia nam");
        expectedData.put("body", "eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita earum mollitia molestiae aut atque rem suscipit\nnam impedit esse");

        // request gonder, donen response'u kaydet

        Response response = given().when().get(url);

        // Assertions

        JsonPath responseJsonPath = response.jsonPath();

        Assert.assertEquals(expectedData.get("id"), responseJsonPath.get("id"));
        Assert.assertEquals(expectedData.get("title"), responseJsonPath.get("title"));
        Assert.assertEquals(expectedData.get("body"), responseJsonPath.get("body"));
        Assert.assertEquals(expectedData.get("userId"), responseJsonPath.get("userId"));


    }
}
