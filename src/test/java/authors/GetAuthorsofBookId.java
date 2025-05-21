package authors;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.Files;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class GetAuthorsofBookId {

    protected Response response;
    protected int BookId;
    protected Files filesUtils = new Files();

    public GetAuthorsofBookId(int bookID) {
        this.BookId = bookID;
    }


    @Step("Get All Authors fpr specific book id Request")
    public void getAuthorsofBookId(RequestSpecification getAuthorRequest){
        response =getAuthorRequest
                .when().get("api/v1/Authors/authors/books/" + BookId)
                .then().statusCode(200)
                .body("idBook", everyItem(equalTo(BookId)))
                .body(matchesJsonSchemaInClasspath("schemas/AuthorsSchema.json"))
                .extract()
                .response();

        filesUtils.writeResponseToFile(response.prettyPrint(), "src/main/responses/authorsResponses/getAuthorsofBookIdResponse.json");
    }
}

