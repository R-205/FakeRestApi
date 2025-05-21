package activities;

import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;
import utils.Files;

import static org.hamcrest.Matchers.lessThan;


public class DeleteActivity {

    protected int activityId;
    protected Files filesUtils = new Files();

    public DeleteActivity(int id) {
        this.activityId = id;
    }

    @Step("Delete Specific Activity using id Request")
    public void deleteActivity(RequestSpecification getActivityRequest){
          getActivityRequest
                .when().delete("/api/v1/Activities/" + activityId)
                .then().statusCode(200);
    }

}
