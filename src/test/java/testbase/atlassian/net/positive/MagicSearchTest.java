package testbase.atlassian.net.positive;

import docum_api.MagicSearch;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MagicSearchTest extends MagicSearch {

    String text = "example"; // for validation schema

    /**
     * 230 — никого не нашли по запросу, пустой ответ
     * 231 — найден 1 юзер
     * 232 — найдено больше 1 юзера (но без компаний)
     * 233 — найдена 1 компания
     * 234 — найдено больше 1 компании (но без юзеров)
     * 235 — найдены как юзеры, так и компании
     * 200 — запрос выполнен успешно и не попал под вышеизложенные сценарии (ну вдруг)     *
     */

    @DataProvider
    public Object[][] searchStatusCodes() {
        return new Object[][] {
                {"!- ", 230},
                {"email@example.com", 231},
                {"example", 232},
                {"345", 235},
                {"Volodymyr's company", 234}
        };
    }

    @DataProvider
    public Object[][] searchParameters() {
        return new Object[][] {
                {"example", PartyType.USER, 6, false, Status.ACTUAL, 230} // фио,емейл,дата рождения,ИНН,Название
        };
    }

    @Test(dataProvider = "searchStatusCodes", description = "Test search statusCodes", priority = 1)
    public void doSearchWithParameters(String input, int statusCode) {
        searchPeopleWithText(input, statusCode);
    }

    @Test(dataProvider = "searchParameters", description = "Test search parameters", priority = 2)
    public void doSearchWithParameters(String input, PartyType partyType, int maxCount, Boolean fullSimilarity, Status taskStatus, int statusCode) {
        searchPeopleWithText(input, partyType, maxCount, fullSimilarity, taskStatus, statusCode);
    }


    @Test(description = "Test Headers", priority = 3)
    public void headerFromResponse() {
        getHeadersFromResponse("example");
    }


    @Test(description = "Validate response by Schema", priority = 0)
    public void validateResponse() {
        validateJsonSchema(text);
    }

}
