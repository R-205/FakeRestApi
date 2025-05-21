package books;

import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.BookData;
import utils.Files;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;

public class AddBook {
    protected Response response;
    protected int bookId;
    protected String bookTitle;

    protected BookData bookData = new BookData();
    protected Files filesUtils = new Files();

    public AddBook(int id, String title) {
        this.bookId = id;
        this.bookTitle = title;
    }

    @Step("Initialize book data")
    public void logBookData(int id, String title){}

    @Step("Add Book Request")
    public void addBook(RequestSpecification getBooksRequest){
        bookData.setId(bookId);
        bookData.setTitle(bookTitle);

        response = getBooksRequest.body(bookData)

                .when().post("/api/v1/Books")

                .then().assertThat().statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/BookSchema.json"))
                .extract().response();

        filesUtils.writeResponseToFile(response.prettyPrint(), "src/main/responses/booksResponses/addBookResponse.json");

        JsonPath js = response.jsonPath();

        assertEquals(js.getInt("id"), bookId, "❌ Book ID does not match the expected value!");
        assertEquals(js.getString("title"), bookTitle, "❌ Book Title does not match the expected value!");
    }
}
