package authors;

import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.AuthorData;
import utils.Files;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.lessThan;
import static org.testng.Assert.assertEquals;

public class UpdateAuthor {

    protected Response response;
    protected int authorId;
    protected int bookId;
    protected String firstName;
    protected String lastName;

    protected AuthorData authorData = new AuthorData();
    protected Files filesUtils = new Files();

    public UpdateAuthor(int id, int bookid , String firstname,String lastname){
        this.authorId = id;
        this.bookId = bookid;
        this.firstName = firstname;
        this.lastName = lastname;
    }

    @Step("Update author data")
    public void logAuthorData(int id, int bookId, String firstName, String lastName){}

    @Step("Update Author with specific id Request")
    public void updateAuthor(RequestSpecification getAuthorsRequest) {

        authorData.setId(authorId);
        authorData.setBookId(bookId);
        authorData.setFirstName(firstName);
        authorData.setLastName(lastName);

        response = getAuthorsRequest.body(authorData)
                .when().put("/api/v1/Authors/" + authorId)
                .then()
                .assertThat().
                statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/AuthorSchema.json"))

                .extract().response();

        filesUtils.writeResponseToFile(response.asPrettyString(), "src/main/responses/authorsResponses/updateAuthor.json");


        JsonPath js = response.jsonPath();

        assertEquals(js.getInt("id"), authorId, "Author ID does not match the expected value!");
        assertEquals(js.getInt("idBook"), bookId, "Book ID does not match the expected value!");
        assertEquals(js.getString("firstName"), firstName, "First Name does not match the expected value!");
        assertEquals(js.getString("lastName"), lastName, "Last Name does not match the expected value!");
    }
}


