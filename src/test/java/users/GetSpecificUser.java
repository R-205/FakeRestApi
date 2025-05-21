package users;

import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.Files;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.notNullValue;
import static org.testng.Assert.assertEquals;

public class GetSpecificUser {

    protected int userId;
    protected Response response;
    protected Files filesUtils = new Files();

    public GetSpecificUser (int id){
        this.userId=id;
    }

    @Step("Get user with specific id Request")
    public void getSpecificUser(RequestSpecification getUsersRequest){
        response =getUsersRequest
                .when().get("/api/v1/Users/" + userId)
                .then().statusCode(200)
                .body("id", equalTo(userId))
                .body("id", notNullValue())
                .body("userName", notNullValue())
                .body("password", notNullValue())
                .extract()
                .response();

        filesUtils.writeResponseToFile(response.prettyPrint(), "src/main/responses/usersResponses/getSpecificUserResponse.json");

        JsonPath js = response.jsonPath();
        assertEquals(js.getInt("id"), userId, "❌ User ID does not match the expected value!");
    }
}
