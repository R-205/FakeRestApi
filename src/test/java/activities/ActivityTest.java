package activities;

import base.TestBase;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ActivityTest extends TestBase {

    protected RequestSpecification getActivityRequest;

    private final int activityID;

    protected GetActivities getActivities;
    protected AddActivity addactivity;
    protected GetSpecificActivity getSpecifiActivities;
    protected UpdateActivity updateActivity;
    protected DeleteActivity deleteActivity;

    public ActivityTest(int activityid) {
        super();

        this.activityID = activityid;

        getActivities =new GetActivities();
        addactivity = new AddActivity(activityID, "new activity title" , true);
        getSpecifiActivities =new GetSpecificActivity(activityID);
        updateActivity =new UpdateActivity(activityID,"updated activity" , false);
        deleteActivity = new DeleteActivity(activityID);
    }

    @BeforeClass
    public void start(){
        getActivityRequest = given().relaxedHTTPSValidation().log().all().spec(commonHeader());
    }


    @Test
    public void testActivitiesRequests(){
        getActivities.getActivites(getActivityRequest);

        addactivity.logActivityData(activityID, "new activity title" , true);
        addactivity.addActivity(getActivityRequest);

        getSpecifiActivities.getspecificActivity(getActivityRequest);

        updateActivity.logActivityData(activityID, "updated activity" , true);
        updateActivity.updateActivity(getActivityRequest);

        deleteActivity.deleteActivity(getActivityRequest);
    }
}
