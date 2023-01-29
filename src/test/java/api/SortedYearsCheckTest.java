package api;

import api.pojoClass.ColorsData;
import api.specification.Specification;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class SortedYearsCheckTest {
    private static String URL = "https://reqres.in/";

    @Test
    public void sortedYearsTest() {
        Specification.installSpecification(Specification.responseSpecificationOK200(), Specification.requestSpecification(URL));
        List<ColorsData> colors = given()
                .when()
                .get("api/unknown")
                .then().log().all()
                .extract().body().jsonPath().getList("data", ColorsData.class);

        List<Integer> years = colors.stream().map(ColorsData::getYear).collect(Collectors.toList());
        List<Integer> sortedYears = years.stream().sorted().collect(Collectors.toList());
        Assert.assertEquals(sortedYears, years);
    }
}
