package coverPhotos;

import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.Files;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;

public class GetSpecificCoverPhoto {
    protected Response response;
    protected int photoId;

    protected Files filesUtils = new Files();

    public GetSpecificCoverPhoto(int photoId){
        this.photoId = photoId;
    }

    @Step("Get Specific Cover Photo using id Request")
    public void getSpecificCoverPhoto(RequestSpecification getCoverPhotosRequest){

        response = getCoverPhotosRequest.pathParam("id", photoId)

                .when().get("/api/v1/CoverPhotos/{id}")

                .then().assertThat().statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/CoverPhotoSchema.json"))
                .extract().response();

        filesUtils.writeResponseToFile(response.prettyPrint(), "src/main/responses/CoverPhotosResponses/getSpecificCoverPhotoResponse.json");

        JsonPath js = response.jsonPath();

        assertEquals(js.getInt("id"), photoId, "❌ Photo ID does not match the expected value!");
    }
}
