package activities;

import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.Files;

import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class GetSpecificActivity {
    protected Response response;
    protected int activityId;

    protected Files filesUtils = new Files();

    public GetSpecificActivity(int id){

        this.activityId = id;
    }

    @Step("Get Specific Activity using id Request")
    public void getspecificActivity(RequestSpecification getActivityRequest){
        response =getActivityRequest
                .when().get("/api/v1/Activities/" + activityId)
                .then().statusCode(200)
                .body("id", equalTo(activityId))
                .body("title", notNullValue())
                .body("dueDate", notNullValue())
                .body("completed", notNullValue())
                .extract()
                .response();

        filesUtils.writeResponseToFile(response.prettyPrint(), "src/main/responses/activityResponses/getSpecificActivityResponse.json");

        JsonPath js = response.jsonPath();
        assertEquals(js.getInt("id"), activityId, "Activity ID does not match the expected value!");
    }
}
