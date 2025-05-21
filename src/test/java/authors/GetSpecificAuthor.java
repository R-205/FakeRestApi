package authors;

import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.Files;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.notNullValue;
import static org.testng.Assert.assertEquals;

public class GetSpecificAuthor {

    protected Response response;
    protected int authorId;
    protected Files filesUtils = new Files();

    public GetSpecificAuthor(int id) {
        this.authorId = id;
    }

    @Step("Get Author with specific id Request")
    public void getSpecificAuthor(RequestSpecification getAuthorRequest){
        response =getAuthorRequest
                .when().get("/api/v1/Authors/" + authorId)
                .then().statusCode(200)
                .body("id", equalTo(authorId))
                .body("idBook", notNullValue())
                .body("firstName", notNullValue())
                .body("lastName", notNullValue())
                .extract()
                .response();

        filesUtils.writeResponseToFile(response.prettyPrint(), "src/main/responses/authorsResponses/getSpecificAuthorResponse.json");
        JsonPath js = response.jsonPath();
        assertEquals(js.getInt("id"), authorId, "❌ Author ID does not match the expected value!");
    }
}
