package books;

import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;
import utils.Files;


public class DeleteBook {
    protected int bookId;

    protected Files filesUtils = new Files();

    public DeleteBook(int id){
        this.bookId = id;
    }

    @Step("Delete Specific Book using id Request")
    public void deleteBook(RequestSpecification getBooksRequest){
        getBooksRequest.pathParam("id",bookId)
                .when().delete("/api/v1/Books/{id}")
                .then().assertThat().statusCode(200).extract().response();
    }
}
