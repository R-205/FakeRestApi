package coverPhotos;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteCoverPhoto {

    protected Response response;
    protected int photoId;

    public DeleteCoverPhoto(int photoId){
        this.photoId = photoId;
    }

    @Step("Delete Specific Cover Photo using id Request")
    public void deleteCoverPhoto(RequestSpecification getCoverPhotosRequest){
        response = getCoverPhotosRequest.pathParam("id", photoId)

                .when().delete("/api/v1/CoverPhotos/{id}")

                .then().assertThat().statusCode(200)
                .extract().response();
    }
}
