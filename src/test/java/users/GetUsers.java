package users;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.Files;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.lessThan;

public class GetUsers {

    protected Response response;

    protected Files filesUtils = new Files();

    @Step("Get All Users Request")
    public void getUsers(RequestSpecification getUsersRequest){
        response = getUsersRequest.when().get("/api/v1/Users")

                .then().assertThat().statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/UsersSchema.json"))
                .extract().response();

        filesUtils.writeResponseToFile(response.prettyPrint(), "src/main/responses/usersResponses/getUsersResponse.json");
    }
}

