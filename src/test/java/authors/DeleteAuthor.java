package authors;
import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;
import utils.Files;
import static org.hamcrest.Matchers.lessThan;
public class DeleteAuthor {

    protected int AuthorId;
    protected Files filesUtils = new Files();

    public DeleteAuthor(int id){
        this.AuthorId=id;
    }

    @Step("Delete Author Request")
    public void deleteAuthor(RequestSpecification getAuthorRequest){
        getAuthorRequest
                .when().delete("/api/v1/Authors/"+AuthorId)
                .then()
                .assertThat()
                .statusCode(200);
    }
}
