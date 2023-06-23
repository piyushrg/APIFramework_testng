package api.Tests.User;

import api.EndPoints.User.UserEndPoints;
import api.Payload.User_pojo;
import api.utilities.ExtentReportManager;
import api.utilities.runner;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests extends ExtentReportManager {

    Faker fake = new Faker();
    User_pojo  user = new User_pojo();
    UserEndPoints userLogic = new UserEndPoints();

    @BeforeClass
    public void setUpData(){

        user.setId(fake.idNumber().hashCode());
        user.setUsername(fake.name().username());
        user.setFirstName(fake.name().firstName());
        user.setLastName(fake.name().lastName());
        user.setEmail(fake.internet().safeEmailAddress());
        user.setPassword(fake.internet().password());
        user.setPhone(fake.phoneNumber().cellPhone());
    }

    @Test(priority=1)
    public void PostUser(){
        test = extent.createTest("Method for Post user");

        Response response = userLogic.CreateUser(user);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        test.log(Status.PASS, String.valueOf(response.getStatusCode()));
    }

    @Test(priority=2)
    public void GetUser(){
        test = extent.createTest("Method for Get user");

        Response response = userLogic.GetUser(this.user.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

        test.log(Status.PASS, String.valueOf(response.getStatusCode()));
        test.log(Status.PASS,"Username "+ user.getUsername());
    }

    @Test(priority = 3)
    public void UpdateUser(){
        test = extent.createTest("Update the Pet User");

        user.setFirstName(fake.name().firstName());
        user.setLastName(fake.name().lastName());
        user.setEmail(fake.internet().safeEmailAddress());

        Response response = userLogic.UpdateUser(this.user.getUsername(), user);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

        Response response1 = userLogic.GetUser(this.user.getUsername());
        response1.then().log().all();
        test.log(Status.PASS, "updated firstname "+ user.getFirstName());
        test.log(Status.PASS, "updated lastname "+ user.getLastName());
        test.log(Status.PASS, "updated email "+ user.getEmail());
    }

    @Test(priority = 4)
    public void DeleteUseer(){
        test = extent.createTest("Delete the Pet User");

        Response response =  userLogic.DeleteUser(this.user.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        test.log(Status.PASS,"Deleted with code "+ String.valueOf(response.getStatusCode()));
    }

}
