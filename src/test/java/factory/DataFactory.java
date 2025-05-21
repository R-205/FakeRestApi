package factory;

import activities.ActivityTest;
import authors.AuthorTests;
import books.BooksTest;
import coverPhotos.CoverPhotosTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import users.UserTests;
import utils.Files;

public class DataFactory {

    private static final Files file = new Files();

    @BeforeSuite
    public void clearAllureResults(){
        file.deleteFilesFromDirectory("allure-results");
    }

    @DataProvider(name = "Data")
    public static Object[][] getData() {
        return file.readCsvFile();
    }

    @Factory(dataProvider = "Data")
    public Object[] createActivityTestInstance(int activityID, int authorId, int bookID, int photoID, int userID){
        return new Object[] {new ActivityTest(activityID)};
    }

    @Factory(dataProvider = "Data")
    public Object[] createBooksTestInstance(int activityID, int authorId, int bookID, int photoID, int userID){
        return new Object[] {new BooksTest(bookID)};
    }

    @Factory(dataProvider = "Data")
    public Object[] createCoverPhotosTestInstance(int activityID, int authorId, int bookID, int photoID, int userID){
        return new Object[] {new CoverPhotosTest(photoID, bookID)};
    }

    @Factory(dataProvider = "Data")
    public Object[] createAuthorsTestInstance(int activityID, int authorId, int bookID, int photoID, int userID){
        return new Object[] {new AuthorTests(authorId, bookID)};
    }

    @Factory(dataProvider = "Data")
    public Object[] createUsersTestInstance(int activityID, int authorId, int bookID, int photoID, int userID){
        return new Object[] {new UserTests(userID)};
    }
}
