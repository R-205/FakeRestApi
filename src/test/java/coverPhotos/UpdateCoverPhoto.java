package coverPhotos;

import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.CoverPhotoData;
import utils.Files;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;

public class UpdateCoverPhoto {

    protected Response response;
    protected int photoId;
    protected int bookId;
    protected String url;

    protected CoverPhotoData coverPhotoDataData = new CoverPhotoData();
    protected Files filesUtils = new Files();

    public UpdateCoverPhoto(int photoid, int bookid, String Turl){
        this.photoId = photoid;
        this.bookId = bookid;
        this.url = Turl;
    }

    @Step("Update cover photo data")
    public void logCoverPhotoData(int id, int book, String url){}

    @Step("Update Specific Cover Photo using id Request")
    public void updateCoverPhoto(RequestSpecification getCoverPhotosRequest){

        coverPhotoDataData.setId(photoId);
        coverPhotoDataData.setIdBook(bookId);
        coverPhotoDataData.setURL(url);

        response = getCoverPhotosRequest.pathParam("id", photoId).body(coverPhotoDataData)

                .when().put("/api/v1/CoverPhotos/{id}")

                .then().assertThat().statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/CoverPhotoSchema.json"))
                .extract().response();

        filesUtils.writeResponseToFile(response.prettyPrint(), "src/main/responses/CoverPhotosResponses/updateCoverPhotoResponse.json");

        JsonPath js = response.jsonPath();

        assertEquals(js.getInt("id"), photoId, "❌ Photo ID does not match the expected value!");
        assertEquals(js.getInt("idBook"), bookId, "❌ Book ID does not match the expected value!");
        assertEquals(js.getString("url"), url, "❌ Photo URL does not match the expected value!");
    }

}
