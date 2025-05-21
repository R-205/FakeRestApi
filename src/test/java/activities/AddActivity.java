package activities;


import base.TestBase;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.ActivityData;
import pojo.ActivityData;
import utils.Files;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;

public class AddActivity  {

    protected Response response;
    protected int activityId;
    protected String activityTitle;
    protected boolean completed;

    protected ActivityData activityData = new ActivityData();
    protected Files filesUtils = new Files();

    public AddActivity(int id, String title , boolean completed){
        this.activityId = id;
        this.activityTitle = title;
        this.completed = completed;
    }

    @Step("Initialize activity data")
    public void logActivityData(int id, String title, boolean completed){}

    @Step("Add Activity Request")
    public void addActivity(RequestSpecification getActivityRequest){
        activityData.setId(activityId);
        activityData.setTitle(activityTitle);

        response = getActivityRequest.body(activityData)

                .when().post("/api/v1/Activities")

                .then().assertThat().statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/ActivitySchema.json"))
                .extract().response();

        filesUtils.writeResponseToFile(response.prettyPrint(), "src/main/responses/activityResponses/addActivityResponse.json");

        JsonPath js = response.jsonPath();

        assertEquals(js.getInt("id"), activityId, " activity ID does not match the expected value!");
        assertEquals(js.getString("title"), activityTitle, " activity Title does not match the expected value!");
    }
}
