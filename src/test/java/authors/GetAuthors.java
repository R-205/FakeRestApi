package authors;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.Files;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.lessThan;

public class GetAuthors {
    protected Response response;

    protected Files filesUtils = new Files();


    @Step("Get All Authors Request")
    public void getAuthors(RequestSpecification getAuthorsRequest){
        response = getAuthorsRequest.when().get("/api/v1/Authors")

                .then().assertThat().statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/AuthorsSchema.json"))
                .extract().response();

        filesUtils.writeResponseToFile(response.prettyPrint(), "src/main/responses/authorsResponses/getAuthorsResponse.json");
    }
}

