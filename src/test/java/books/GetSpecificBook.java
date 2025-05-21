package books;

import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.BookData;
import utils.Files;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;

public class GetSpecificBook {

    protected Response response;
    protected int bookId;

    protected Files filesUtils = new Files();

    public GetSpecificBook(int id){
        this.bookId = id;
    }

    @Step("Get Specific Book using id Request")
    public void getSpecificBook(RequestSpecification getBooksRequest){
        response = getBooksRequest.pathParam("id",bookId)

                .when().get("/api/v1/Books/{id}")

                .then().assertThat().statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/BookSchema.json"))
                .extract().response();

        filesUtils.writeResponseToFile(response.prettyPrint(), "src/main/responses/booksResponses/getSpecificBookResponse.json");

        JsonPath js = response.jsonPath();

        assertEquals(js.getInt("id"), bookId, "❌ Book ID does not match the expected value!");

        assertEquals(js.getString("title"), "Book "+bookId, "❌ Book Title does not match the expected value!");
    }
}
