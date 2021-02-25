package constants;

import static constants.Constants.Servers.TEST_BASE_URL;
import static constants.Constants.Path.TEST_BASE_PATH;

public class Constants {

    public static class RunVariable {
        public static String server = TEST_BASE_URL;
        public static String path = TEST_BASE_PATH;
    }

    public static class Servers {
        public static String TEST_BASE_URL =  "http://users.bugred.ru/";
    }

    public static class Path {
        public static String TEST_BASE_PATH = "tasks/rest/";
    }

    public static class Actions {
        public static String TEST_BASE_DO_REGISTER_POST = "doregister";
        public static String TEST_BASE_CREATE_COMPANY_POST = "createcompany";
        public static String TEST_BASE_CREATE_USER_POST = "createuser";
        public static String TEST_BASE_CREATE_USER_WITH_TASKS = "createuserwithtasks";
        public static String TEST_BASE_ADD_AVATAR = "addavatar";
        public static String TEST_BASE_DELETE_AVATAR = "deleteavatar";
        public static String TEST_BASE_MAGIC_SEARCH = "magicsearch";
    }
}
