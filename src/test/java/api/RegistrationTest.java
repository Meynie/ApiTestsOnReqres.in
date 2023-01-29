package api;

import api.pojoClass.Register;
import api.pojoClass.SuccessRegister;
import api.pojoClass.UnSuccessRegister;
import api.specification.Specification;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RegistrationTest {
    private static String URL = "https://reqres.in/";

    @Test
    public void successRegTest() {
        Specification.installSpecification(Specification.responseSpecificationOK200(), Specification.requestSpecification(URL));
        Integer id = 4;
        String token = "QpwL5tke4Pnpja7X4";
        Register registerUser = new Register("eve.holt@reqres.in", "pistol");
        SuccessRegister successRegister = given()
                .body(registerUser)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().as(SuccessRegister.class);

        Assert.assertNotNull(successRegister.getId());
        Assert.assertNotNull(successRegister.getToken());
        Assert.assertEquals(id, successRegister.getId());
        Assert.assertEquals(token, successRegister.getToken());
    }

    @Test
    public void unSuccessRegister() {
        Specification.installSpecification(Specification.responseSpecificationFail400(), Specification.requestSpecification(URL));

        Register registerUser = new Register("sydney@fife", "");
        UnSuccessRegister unSuccessRegister = given()
                .body(registerUser)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().as(UnSuccessRegister.class);

        Assert.assertEquals("Missing password", unSuccessRegister.getError());
    }
}
