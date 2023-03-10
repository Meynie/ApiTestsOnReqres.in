package api;

import api.pojoClass.*;
import api.specification.Specification;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class CheckAvatarAndIDTest {
    private static String URL = "https://reqres.in/";

    @Test
    public void checkAvatarAndIdTest() {
        Specification.installSpecification(Specification.responseSpecificationOK200(), Specification.requestSpecification(URL));
        List<UserData> users = given()
                .when()
                .get("api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);

        // Проход по всем пользователям и проверка, что id содержится в avatar
        //users.stream().forEach(x-> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));

        Assert.assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith("@reqres.in")));

        List<String> avatars = users.stream().map(UserData::getAvatar).collect(Collectors.toList());
        List<String> ids = users.stream().map(x -> x.getId().toString()).collect(Collectors.toList());
        for (int i = 0; i < avatars.size(); i++) {
            Assert.assertTrue(avatars.get(i).contains(ids.get(i)));
        }
    }
}
