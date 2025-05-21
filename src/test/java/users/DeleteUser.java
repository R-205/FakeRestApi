package users;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.Files;

import static org.hamcrest.Matchers.lessThan;

public class DeleteUser {
    protected int userId;
    protected Response response;
    protected Files filesUtils = new Files();

    public DeleteUser (int id){
        this.userId=id;
    }

    @Step("Delete User Request")
    public void deleteUser(RequestSpecification getUsersRequest){
        getUsersRequest
                .when().delete("/api/v1/Users/"+userId)
                .then()
                .assertThat()
                .statusCode(200);
    }
}
