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
import org.openqa.selenium.chrome.ChromeOptions;
import paigesObjects.MainPage;
import testData.User;
import testData.UserClient;
import testData.UserCredentials;
import testData.UserGenerator;

import static constants.adressOfPages.*;


public class TestMoveInConstructor {
    private WebDriver driver;
    private User user;
    private UserClient userClient;
    private String bearerToken = "";


    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        //driver = new SafariDriver();
        driver = new ChromeDriver(options);
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
    @DisplayName("Test move to section sauces")
    public void testMoveOnSauses() {
        driver.get(ADDR_MAIN_PAGE);
        MainPage mainPage = new MainPage(driver);
        mainPage.logginOnButtonLogAccount(user);
        mainPage.clickSauces();
        Assert.assertTrue("Sauces is not selected", mainPage.getResultClickSauces());
    }

    @Test
    @DisplayName("Test move to section fillings")
    public void testMoveOnFillings() {
        driver.get(ADDR_MAIN_PAGE);
        MainPage mainPage = new MainPage(driver);
        mainPage.logginOnButtonLogAccount(user);
        mainPage.clickFillings();
        Assert.assertTrue("Fillings is not selected", mainPage.getResultClickFillings());
    }

    @Test
    @DisplayName("Test move to section suns")
    public void testMoveOnSuns() {
        driver.get(ADDR_MAIN_PAGE);
        MainPage mainPage = new MainPage(driver);
        mainPage.logginOnButtonLogAccount(user);
        mainPage.clickFillings();
        mainPage.clickSuns();
        Assert.assertTrue("Suns is not selected", mainPage.getResultClickSuns());
    }
}

