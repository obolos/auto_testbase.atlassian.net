package testbase.atlassian.net.positive;

import docum_api.DoRegister;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

public class DoRegisterTest extends DoRegister {
    Map<String,String> params; // email and name for check required params
    Map<String,String> params1; // email and name for doRegister and already exists

    @Test(priority=1)
    public void doRegisterCheckStatusCode() {
        params1 = generateParamsForDoRegister();
        // Send post request on doRegister
        doRegisterWithStatusOK(params1);
    }

    @DataProvider
    public Object[][] requiredFields() {
        params = generateParamsForDoRegister();
        return new Object[][] {
                {params.get("email"), "", "123"},
                {"",params.get("name"), "123"},
                {params.get("email"), params1.get("name"), "123"},
                {params1.get("email"),"", "123"},
                {params.get("email"), params.get("name"), ""}, // password required!

        };
    }

    @Test(dataProvider = "requiredFields", priority=2)
    public void doRegisterVerifyRequiredFields(String email, String name, String password) {
        // Verify that same password or email already exists and fields are required
        doRegisterErrorTest(email, name, password);

    }

    }

