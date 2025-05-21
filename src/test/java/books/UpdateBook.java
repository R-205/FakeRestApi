package books;

import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.BookData;
import utils.Files;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;

public class UpdateBook {
    protected Response response;
    protected int bookId;
    protected String bookTitle;

    protected BookData bookData = new BookData();
    protected Files filesUtils = new Files();

    public UpdateBook(int id, String title){
        this.bookId = id;
        this.bookTitle = title;
    }

    @Step("Update book data")
    public void logBookData(int id, String title){}

    @Step("Update Specific Book using id Request")
    public void updateBook(RequestSpecification getBooksRequest){
        bookData.setId(bookId);
        bookData.setTitle(bookTitle);

        response = getBooksRequest.pathParam("id", bookId).body(bookData)

                .when().put("/api/v1/Books/{id}")

                .then().assertThat().statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/BookSchema.json"))
                .extract().response();

        filesUtils.writeResponseToFile(response.prettyPrint(), "src/main/responses/booksResponses/updateBookResponse.json");

        JsonPath js = response.jsonPath();

        assertEquals(js.getInt("id"), bookId, "❌ Book ID does not match the expected value!");
        assertEquals(js.getString("title"), bookTitle, "❌ Book Title does not match the expected value!");
    }
}
