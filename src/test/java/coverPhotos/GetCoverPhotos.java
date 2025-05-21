package coverPhotos;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.Files;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class GetCoverPhotos {

    protected Response response;

    protected Files filesUtils = new Files();

    @Step("Get All Cover Photos Request")
    public void getCoverPhotos(RequestSpecification getCoverPhotosRequest){
        response = getCoverPhotosRequest.when().get("/api/v1/CoverPhotos")

                .then().assertThat().statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/CoverPhotosSchema.json"))
                .extract().response();

        filesUtils.writeResponseToFile(response.prettyPrint(), "src/main/responses/coverPhotosResponses/getCoverPhotosResponse.json");
    }
}
