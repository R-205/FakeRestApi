package coverPhotos;

import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.CoverPhotoData;
import utils.Files;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;

public class AddCoverPhoto {
    protected Response response;
    protected int photoId;
    protected int bookId;

    protected CoverPhotoData coverPhotoDataData = new CoverPhotoData();
    protected Files filesUtils = new Files();

    public AddCoverPhoto(int photoid, int bookid){
        this.photoId = photoid;
        this.bookId = bookid;
    }

    @Step("Initialize cover photo data")
    public void logCoverPhotoData(int id, int book){}

    @Step("Add Cover Photo Request")
    public void addCoverPhoto(RequestSpecification getCoverPhotosRequest){
        coverPhotoDataData.setId(photoId);
        coverPhotoDataData.setIdBook(bookId);

        response = getCoverPhotosRequest.body(coverPhotoDataData)

                .when().post("/api/v1/CoverPhotos")

                .then().assertThat().statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/CoverPhotoSchema.json"))
                .extract().response();

        filesUtils.writeResponseToFile(response.prettyPrint(), "src/main/responses/CoverPhotosResponses/addCoverPhotoResponse.json");

        JsonPath js = response.jsonPath();

        assertEquals(js.getInt("id"), photoId, "❌ Photo ID does not match the expected value!");

        assertEquals(js.getInt("idBook"), bookId, "❌ Book ID does not match the expected value!");
    }
}

