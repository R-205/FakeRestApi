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

public class AddUser {

    protected Response response;
    protected int userId;
    protected String userName;
    protected String password;

    protected UserData userData = new UserData();
    protected Files filesUtils = new Files();

    public AddUser(int id, String name,String pass){
        this.userId = id;
        this.userName = name;
        this.password = pass;
    }

    @Step("Initialize user data")
    public void logUserData(int id, String userName, String password){}


    @Step("Add User Request")
    public void addUser(RequestSpecification getUsersRequest){
        userData.setId(userId);
        userData.setUserName(userName);
        userData.setPassword(password);

        response= getUsersRequest.body(userData)
                .when().post("/api/v1/Users")
                .then()
                .assertThat().
                statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/UserSchema.json"))

                .extract().response();

        filesUtils.writeResponseToFile(response.asPrettyString(), "src/main/responses/usersResponses/addUser.json");

        JsonPath js = response.jsonPath();
        assertEquals(js.getInt("id"), userId, "❌ User ID does not match the expected value!");
        assertEquals(js.getString("userName"), userName, "❌ First Name does not match the expected value!");
        assertEquals(js.getString("password"), password, "❌ Last Name does not match the expected value!");
    }
}
