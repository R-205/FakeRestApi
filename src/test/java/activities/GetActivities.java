package activities;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.Files;
import static org.hamcrest.Matchers.lessThan;


import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class GetActivities {
    protected Response response;

    protected Files filesUtils = new Files();

    @Step("Get All Activities Request")
    public void getActivites(RequestSpecification getActivityRequest){
        response = getActivityRequest.when().get("/api/v1/Activities")

                .then().assertThat().statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/ActivitiesSchema.json"))
                .extract().response();

        filesUtils.writeResponseToFile(response.prettyPrint(), "src/main/responses/activityResponses/getActivitiesResponse.json");
    }
}
