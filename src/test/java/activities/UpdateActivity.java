package activities;

import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.ActivityData;
import utils.Files;

import static org.hamcrest.Matchers.lessThan;
import static org.testng.Assert.assertEquals;

public class UpdateActivity {

    protected Response response;
    protected int activityId;
    protected String activityTitle;
    protected boolean completed;
    protected ActivityData activityData = new ActivityData();
    protected Files filesUtils = new Files();

    public  UpdateActivity(int id , String title , boolean completed){
     this.activityId=id;
     this.activityTitle=title;
     this.completed=completed;
    }

    @Step("Update activity data")
    public void logActivityData(int id, String title, boolean completed){}

    @Step("Update Specific Activity using id Request")
    public void updateActivity(RequestSpecification getActivityRequest){

        activityData.setId(activityId);
        activityData.setTitle(activityTitle);
        activityData.setCompleted(completed);

        response =getActivityRequest.body(activityData)
               .when().put("/api/v1/Activities/"+activityId)
               .then().assertThat()
               .statusCode(200)
               .extract().response();

        filesUtils.writeResponseToFile(response.prettyPrint(),"src/main/responses/activityResponses/updateActivityResponse.json");

        JsonPath js = response.jsonPath();

        assertEquals(js.getInt("id"), activityId, "Activity ID does not match the expected value!");
        assertEquals(js.getString("title"), activityTitle, "Activity Title does not match the expected value!");
        assertEquals(js.getBoolean("completed"), completed, "Completed status does not match the expected value!");

    }

}
