package api;

import api.pojoClass.UserTime;
import api.pojoClass.UserTimeResponse;
import api.specification.Specification;
import org.junit.Assert;
import org.junit.Test;

import java.time.Clock;

import static io.restassured.RestAssured.given;

public class TimeEqualsTest {
    private static String URL = "https://reqres.in/";

    @Test
    public void timeTest(){
        Specification.installSpecification(Specification.responseSpecificationUnique(200), Specification.requestSpecification(URL));
        UserTime user = new UserTime("morpheus", "zion resident");
        UserTimeResponse response = given()
                .body(user)
                .when()
                .put("api/users/2")
                .then().log().all()
                .extract().as(UserTimeResponse.class);

        String regex = "(.{7})$";
        String currentTime = Clock.systemUTC().instant().toString();
        Assert.assertEquals(currentTime.replaceAll(regex, ""), response.getUpdatedAt().replaceAll(regex, ""));
    }
}
