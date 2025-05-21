package books;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.Files;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class GetBooks {

    protected Response response;

    protected Files filesUtils = new Files();

    @Step("Get All Books Request")
    public void getBooks(RequestSpecification getBooksRequest){
        response = getBooksRequest.when().get("/api/v1/Books")

                .then().assertThat().statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/BooksSchema.json"))
                .extract().response();

        filesUtils.writeResponseToFile(response.prettyPrint(), "src/main/responses/booksResponses/getBooksResponse.json");
    }
}
