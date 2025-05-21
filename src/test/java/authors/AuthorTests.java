package authors;

import base.TestBase;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AuthorTests extends TestBase {
    protected RequestSpecification getAuthorsRequest;

    private final int authorId;
    private final int bookID;

    protected GetAuthors getAuthors;
    protected GetAuthorsofBookId getAuthorsOfBookId;
    protected AddAuthor addAuthor;
    protected UpdateAuthor updateAuthor;
    protected GetSpecificAuthor getSpecificAuthor;
    protected DeleteAuthor deleteAuthor;

    public AuthorTests(int authorid, int bookid) {
        super();

        this.authorId = authorid;
        this.bookID = bookid;

        getAuthors = new GetAuthors();
        getAuthorsOfBookId = new GetAuthorsofBookId(bookID);
        addAuthor = new AddAuthor(authorId,bookID,"first name "+authorId,"last name "+authorId);
        updateAuthor = new UpdateAuthor(authorId,bookID,"update first name "+authorId,"update last name "+authorId);
        getSpecificAuthor = new GetSpecificAuthor(authorId);
        deleteAuthor = new DeleteAuthor(authorId);
    }

    @BeforeClass
    public void start(){
        getAuthorsRequest = given().relaxedHTTPSValidation().log().all().spec(commonHeader());
    }


    @Test
    public void testAuthorsRequests(){

        getAuthors.getAuthors(getAuthorsRequest);

        addAuthor.logAuthorData(authorId,bookID,"first name "+authorId,"last name "+authorId);
        addAuthor.addAuthor(getAuthorsRequest);

        getAuthorsOfBookId.getAuthorsofBookId(getAuthorsRequest);
        getSpecificAuthor.getSpecificAuthor(getAuthorsRequest);

        updateAuthor.logAuthorData(authorId,bookID,"update first name "+authorId,"update last name "+authorId);
        updateAuthor.updateAuthor(getAuthorsRequest);

        deleteAuthor.deleteAuthor(getAuthorsRequest);
    }

}
