package users;

import base.TestBase;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UserTests extends TestBase {
    protected RequestSpecification getUsersRequest;

    private final int userID;

    protected GetUsers getUsers;
    protected GetSpecificUser getSpecificUser;
    protected DeleteUser deleteUser;
    protected AddUser addUser;
    protected UpdateUser updateUser;

    public UserTests(int userid) {
        super();

        this.userID = userid;

        getUsers =new GetUsers();
        addUser =new AddUser(userID,"User "+userID,"password "+userID);
        getSpecificUser = new GetSpecificUser(userID);
        updateUser = new UpdateUser(userID,"update User "+userID);
        deleteUser = new DeleteUser(userID);
    }

    @BeforeMethod
    public void start(){
        getUsersRequest = given().relaxedHTTPSValidation().log().all().spec(commonHeader());
    }

    @Test
    public void testUsersRequests(){
        getUsers.getUsers(getUsersRequest);

        addUser.logUserData(userID,"User "+userID,"password "+userID);
        addUser.addUser(getUsersRequest);

        getSpecificUser.getSpecificUser(getUsersRequest);

        updateUser.logUserData(userID,"update User "+userID);
        updateUser.updateUser(getUsersRequest);

        deleteUser.deleteUser(getUsersRequest);
    }
}
