package coverPhotos;

import base.TestBase;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CoverPhotosTest extends TestBase{

    protected RequestSpecification getCoverPhotosRequest;

    private final int photoID;
    private final int bookID;

    protected GetCoverPhotos getCoverPhotos;
    protected AddCoverPhoto addCoverPhoto;
    protected GetCoverPhotosSpecificBook getCoverPhotosSpecificBook;
    protected GetSpecificCoverPhoto getSpecificCoverPhoto;
    protected UpdateCoverPhoto updateCoverPhoto;
    protected DeleteCoverPhoto deleteCoverPhoto;

    public CoverPhotosTest(int photoid, int bookid) {
        super();

        this.photoID = photoid;
        this.bookID = bookid;

        getCoverPhotos = new GetCoverPhotos();
        addCoverPhoto =new AddCoverPhoto(photoID, bookID);
        getCoverPhotosSpecificBook = new GetCoverPhotosSpecificBook(bookID);
        getSpecificCoverPhoto = new GetSpecificCoverPhoto(photoID);
        updateCoverPhoto = new UpdateCoverPhoto(photoID, bookID, "Image");
        deleteCoverPhoto = new DeleteCoverPhoto(photoID);
    }

    @BeforeClass
    public void start(){
        getCoverPhotosRequest = given().relaxedHTTPSValidation().log().all().spec(commonHeader());
    }

    @Test
    public void testCoverPhotosRequests() {
        getCoverPhotos.getCoverPhotos(getCoverPhotosRequest);

        addCoverPhoto.logCoverPhotoData(photoID, bookID);
        addCoverPhoto.addCoverPhoto(getCoverPhotosRequest);

        getCoverPhotosSpecificBook.getCoverPhotosSpecificBook(getCoverPhotosRequest);
        getSpecificCoverPhoto.getSpecificCoverPhoto(getCoverPhotosRequest);

        updateCoverPhoto.logCoverPhotoData(photoID, bookID, "Image");
        updateCoverPhoto.updateCoverPhoto(getCoverPhotosRequest);

        deleteCoverPhoto.deleteCoverPhoto(getCoverPhotosRequest);
    }
}
