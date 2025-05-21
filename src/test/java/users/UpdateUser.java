package users;

import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.UserData;
import utils.Files;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.lessThan;
import static org.testng.Assert.assertEquals;

public class UpdateUser {

    protected Response response;
    protected int userId;
    protected String userName;

    protected UserData userData = new UserData();
    protected Files filesUtils = new Files();

    public UpdateUser(int id, String username){
        this.userId = id;
        this.userName = username;
    }

    @Step("Update user data")
    public void logUserData(int id, String userName){}

    @Step("Update user with specific id Request")
    public void updateUser(RequestSpecification getUsersRequest){
        userData.setId(userId);
        userData.setUserName(userName);

        response= getUsersRequest.body(userData)
                .when().put("/api/v1/Users/"+userId)
                .then()
                .assertThat().
                statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/UserSchema.json"))

                .extract().response();

        filesUtils.writeResponseToFile(response.asPrettyString(), "src/main/responses/usersResponses/updateUser.json");

        JsonPath js = response.jsonPath();
        assertEquals(js.getInt("id"), userId, "User ID does not match the expected value!");
        assertEquals(js.getString("userName"), userName, "First Name does not match the expected value!");
    }
}


