import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import paigesObjects.MainPage;
import testData.User;
import testData.UserClient;
import testData.UserCredentials;
import testData.UserGenerator;

import static constants.adressOfPages.*;

public class TestExitFromPersonalAccount {
    private WebDriver driver;
    private User user;
    private UserClient userClient;
    private String bearerToken = "";


    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        //driver = new SafariDriver();
        driver.manage().window().maximize();

        userClient = new UserClient();
        user = UserGenerator.getDeffault();
        new UserClient().createUser(user);
        userClient.loginUser(UserCredentials.from(user));
    }

    @After
    public void teardown() {
        ValidatableResponse loginResponse = userClient.loginUser(UserCredentials.from(user));
        if (!bearerToken.isEmpty()) {
            bearerToken = loginResponse.extract().path("accessToken").toString().replace("Bearer ", "");
        }
        userClient.deleteUser(bearerToken);
        driver.quit();
    }

    @Test
    @DisplayName("Test exit from personal account")
    public void testExitFromAccount() {
        driver.get(ADDR_MAIN_PAGE);
        MainPage mainPage = new MainPage(driver);
        mainPage.logginOnButtonLogAccount(user);
        mainPage.clickOnButtonPrivateAccount();
        mainPage.clickOnButtonExit();
        Assert.assertTrue("Failed to log out of account", mainPage.getResultExitAccount());
    }
}