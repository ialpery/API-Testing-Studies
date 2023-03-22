package Tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class C09_Get_BodyTekrarlardanKurtulma {

    @Test
    public void test01(){

        String url = "https://restful-booker.herokuapp.com/booking/10";
        Response response = given().when().get(url);

        response.prettyPrint();

           /*
        response.then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname", Matchers.equalTo("Jim"))
                .body("lastname", Matchers.equalTo("Ericsson"))
                .body("totalprice", Matchers.lessThan(500))
                .body("depositpaid", Matchers.equalTo(true))
                .body("additionalneeds", Matchers.notNullValue());
            */

        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname", equalTo("Mark"),
                        "lastname", equalTo("Ericsson"),
                        "totalprice", lessThan(500),
                        "depositpaid", equalTo(false),
                        "aditionalneeds", notNullValue());

    }

}
