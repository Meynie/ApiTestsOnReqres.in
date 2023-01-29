package api;

import api.specification.Specification;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class DeleteUserTest {
    private static String URL = "https://reqres.in/";

    @Test
    public void deleteUsers() {
        Specification.installSpecification(Specification.responseSpecificationUnique(204), Specification.requestSpecification(URL));
        given()
                .when()
                .delete("api/users/2")
                .then().log().all();

    }
}
