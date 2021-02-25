package docum_api;

import config.TestConfig;
import io.qameta.allure.Step;

import java.util.Map;

import static constants.Constants.Actions.TEST_BASE_DO_REGISTER_POST;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class DoRegister extends TestConfig {

    @Step("Verify that status code is 200 and response is valid")
    public void doRegisterWithStatusOK(Map<String, String> postContent) {
        given().with().body(postContent).log().uri().
                when().post(TEST_BASE_DO_REGISTER_POST).
                then().body(matchesJsonSchemaInClasspath("doRegisterSchema.json")).
                log().body().statusCode(200);
    }


    @Step("Verify that response type is error")
    public void doRegisterErrorTest(String email, String name, String password) {
        String jsonBody = "{\n" +
                "    \"email\": \""+email+"\",\n" +
                "    \"name\": \""+name+"\",\n" +
                "    \"password\": \""+password+"\"\n" +
                "}";

        given().with().body(jsonBody).log().all().
                when().post(TEST_BASE_DO_REGISTER_POST).
                then().body("type", equalTo("error")).
                log().body().statusCode(200);
        }

}


