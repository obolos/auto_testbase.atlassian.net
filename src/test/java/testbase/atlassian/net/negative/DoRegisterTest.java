package testbase.atlassian.net.negative;

import docum_api.DoRegister;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

public class DoRegisterTest extends DoRegister {

    private Map<String,String> params = generateParamsForDoRegister();

    @DataProvider
    public Object[][] requiredFields() {
        return new Object[][] {
                {"examplemaildotcom.mail.ru", params.get("name"), "123"},
                {params.get("email"), params.get("name"), "  -_$ап;--`<>Rverylong string"}
        };
    }

    @Test(dataProvider = "requiredFields")
    public void doRegisterVerifyRequiredFields(String email, String name, String password) {
        // Verify that email is wrong and password have some validation
        doRegisterErrorTest(email, name, password);

    }
}
