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

public class AddAuthor {

    protected Response response;
    protected int authorId;
    protected int bookId;
    protected String firstName;
    protected String lastName;

    protected AuthorData authorData = new AuthorData();
    protected Files filesUtils = new Files();

    public AddAuthor(int id, int bookid, String firstname, String lastname){
        this.authorId = id;
        this.bookId = bookid;
        this.firstName = firstname;
        this.lastName = lastname;
    }

    @Step("Initialize author data")
    public void logAuthorData(int id, int bookId, String firstName, String lastName){}


    @Step("Add Author Request")
    public void addAuthor(RequestSpecification getAuthorsRequest){
        authorData.setId(authorId);
        authorData.setBookId(bookId);
        authorData.setFirstName(firstName);
        authorData.setLastName(lastName);

        response= getAuthorsRequest.body(authorData)
               .when().post("/api/v1/Authors")
                .then()
               .assertThat().
               statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/AuthorSchema.json"))

                .extract().response();

       filesUtils.writeResponseToFile(response.asPrettyString(), "src/main/responses/authorsResponses/addAuthor.json");

       JsonPath js = response.jsonPath();
       assertEquals(js.getInt("id"), authorId, "❌ Author ID does not match the expected value!");
       assertEquals(js.getInt("idBook"), bookId, "❌ Book ID does not match the expected value!");
       assertEquals(js.getString("firstName"), firstName, "❌ First Name does not match the expected value!");
       assertEquals(js.getString("lastName"), lastName, "❌ Last Name does not match the expected value!");
    }
}
