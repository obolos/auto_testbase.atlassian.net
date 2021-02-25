package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import net.andreinc.mockneat.MockNeat;
import org.testng.annotations.BeforeClass;

import java.util.HashMap;
import java.util.Map;

import static constants.Constants.RunVariable.path;
import static constants.Constants.RunVariable.server;

public class TestConfig {
    MockNeat mock = MockNeat.threadLocal();

    private String generateEmail() {
        return mock.emails().domain("mail.ru").val();
    }

    private String generateName() {
        return mock.celebrities().actors().val();
    }

    protected enum Status {
        ALL,
        ACTUAL,
        COMPLETE,
        FAIL
    }
    protected enum PartyType {
        USER,
        COMPANY
    }

    public Map<String, String> generateParamsForDoRegister() {
        HashMap<String, String> postContent = new HashMap<>();
        postContent.put("email",generateEmail());
        postContent.put("name",generateName()+"salt");
        postContent.put("password", "123");

        return postContent;
    }

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = server;
        RestAssured.basePath = path;

        RequestSpecification requestSpecificationForJson = new RequestSpecBuilder()
                .addHeader("Content-Type","application/json")
                .build();

        RestAssured.requestSpecification = requestSpecificationForJson;
    }
}
