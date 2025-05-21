package books;

import base.TestBase;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BooksTest extends TestBase {

    protected RequestSpecification getBooksRequest;

    private final int bookID;

    protected GetBooks getBooks;
    protected AddBook addBook;
    protected GetSpecificBook getSpecificBook;
    protected UpdateBook updateBook;
    protected DeleteBook deleteBook;

    public BooksTest(int bookid) {
        super();

        this.bookID = bookid;

        getBooks = new GetBooks();
        addBook = new AddBook(bookID, "Book "+bookID);
        getSpecificBook = new GetSpecificBook(bookID);
        updateBook = new UpdateBook(bookID, "Update Book "+bookID);
        deleteBook = new DeleteBook(bookID);
    }

    @BeforeClass
    public void start(){
        getBooksRequest = given().relaxedHTTPSValidation().log().all().spec(commonHeader());
    }

    @Test
    public void testBooksRequests(){
        getBooks.getBooks(getBooksRequest);

        addBook.logBookData(bookID, "Book "+bookID);
        addBook.addBook(getBooksRequest);

        getSpecificBook.getSpecificBook(getBooksRequest);

        updateBook.logBookData(bookID, "Update Book "+bookID);
        updateBook.updateBook(getBooksRequest);

        deleteBook.deleteBook(getBooksRequest);
    }
}
