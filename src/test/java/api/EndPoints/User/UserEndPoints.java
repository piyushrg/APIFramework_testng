package api.EndPoints.User;

import api.EndPoints.Routes;
import api.Payload.User_pojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class UserEndPoints {

    /**
     * For Creation of User
     * @return the Response from the method to @Test
     */
    public Response CreateUser(User_pojo payload){

        Response res = given().contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .body(payload)
                        .when().post(Routes.Post_User_Url);
        return res;
    }

    /**
     * Get the user details for particular UserName
     * @param UserName
     * @return the Response from the method to the @Test
     */
    public Response GetUser(String UserName){

        Response res = given().pathParam("username", UserName)
                        .when().get(Routes.Get_User_Url);
        return res;
    }

    /**
     * Updates the user as per the provided UserName
     * @param UserName
     * @return: Response to the @Test
     */
    public Response UpdateUser(String UserName, User_pojo payload){

        Response res = given().contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .pathParam("username", UserName)
                        .body(payload)
                        .when().put(Routes.Update_User_Url)
                        .andReturn();
        return res;
    }

    /**
     * Deletes the record as per the UserName
     * @param UserName
     * @return: response to @Test method
     */
    public Response DeleteUser(String UserName){

        Response res = given().pathParam("username", UserName)
                        .when().delete(Routes.Delete_User_Url)
                        .andReturn();
        return res;
    }
}
