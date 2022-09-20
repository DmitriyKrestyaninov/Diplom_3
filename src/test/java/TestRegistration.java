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
import paigesObjects.RegistrationPage;
import testData.User;
import testData.UserClient;
import testData.UserCredentials;
import testData.UserGenerator;

import static constants.adressOfPages.ADDR_REGISTRATOR;

public class TestRegistration {
    private WebDriver driver;
    private User user;
    private UserClient userClient;
    private String bearerToken = "";

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //driver = new SafariDriver();
        driver.get(ADDR_REGISTRATOR);
        driver.manage().window().maximize();
        userClient = new UserClient();
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
    @DisplayName("Test success registration")
    public void testSuccessRegistration() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        user = UserGenerator.getDeffault();
        registrationPage.registerUser(driver, user);
        Assert.assertTrue("Пользователь не зарегистрирован", registrationPage.getResultSuccesRegistration());
    }

    @Test
    @DisplayName("Test registration with incorrect password")
    public void testRegistrationWithIncorrectPassword() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        user = UserGenerator.getUserWithIncorrectPassword();
        registrationPage.registerUser(driver, user);
        Assert.assertTrue("Пользователь не зарегистрирован", registrationPage.getResultRegistrationIncorrectPassword());
    }
}
