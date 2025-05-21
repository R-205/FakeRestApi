package base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class TestBase {
    public RequestSpecification commonHeader() {
        return new RequestSpecBuilder().setBaseUri("https://fakerestapi.azurewebsites.net")
                                        .setContentType(ContentType.JSON).build();
    }
}
