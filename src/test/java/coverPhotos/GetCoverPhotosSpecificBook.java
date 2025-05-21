package coverPhotos;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.Files;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class GetCoverPhotosSpecificBook {

    protected Response response;
    protected int idBook;

    protected Files filesUtils = new Files();

    public GetCoverPhotosSpecificBook(int bookId){
        this.idBook = bookId;
    }

    @Step("Get All Cover Photos For Specific Book using bookID Request")
    public void getCoverPhotosSpecificBook(RequestSpecification getCoverPhotosRequest){

        response = getCoverPhotosRequest.pathParam("id", idBook)

                .when().get("/api/v1/CoverPhotos/books/covers/{id}")

                .then().assertThat().statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/CoverPhotosSchema.json"))
                .extract().response();

        filesUtils.writeResponseToFile(response.prettyPrint(), "src/main/responses/CoverPhotosResponses/getCoverPhotosSpecificBookResponse.json");
    }
}
