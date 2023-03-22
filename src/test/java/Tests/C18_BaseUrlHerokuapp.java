package Tests;

import BaseUrl.BaseUrlHerokuapp;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C18_BaseUrlHerokuapp extends BaseUrlHerokuapp {

    /*
     https://restful-booker.herokuapp.com/booking endpointine bir GET request gonderdigimizde
     donen response'un status kodunun 200 oldugunu ve responseta 12 booking oldugunu test edin.
     */

    @Test
    public void test01(){

        // endpoint olustur
        specHerokuapp.pathParam("pp1", "booking");

        Response response = given()
                            .when().spec(specHerokuapp)
                            .get("/{pp1}");
        response.prettyPrint();

        JsonPath responsejsonpath=response.jsonPath();
        System.out.println(responsejsonpath.getList("bookingid").size()); //kac tane bookingid oldugunu gormek icin yazdık


       response.then().assertThat().statusCode(200).body("bookingid", Matchers.lessThan(10000));

    }

}
