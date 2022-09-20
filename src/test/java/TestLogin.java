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
import paigesObjects.ForgotPasswordPage;
import paigesObjects.MainPage;
import paigesObjects.RegistrationPage;
import testData.User;
import testData.UserClient;
import testData.UserCredentials;
import testData.UserGenerator;

import static constants.adressOfPages.*;

public class TestLogin {
    private WebDriver driver;
    private User user;
    private UserClient userClient;
    private String bearerToken = "";

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        //driver = new SafariDriver();

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        userClient = new UserClient();
        user = UserGenerator.getDeffault();
        new UserClient().createUser(user);

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
    @DisplayName("Test login on button 'Войти в аккаунт'")
    public void testLoginOnButtonLogAccount() {
        driver.get(ADDR_MAIN_PAGE);
        MainPage mainPage = new MainPage(driver);
        mainPage.logginOnButtonLogAccount(user);
        Assert.assertTrue("User not login", mainPage.getResultSuccesLogin());
    }

    @Test
    @DisplayName("Test loggin on button 'Личный кабинет' ")
    public void testLoginOnButtonPersonalAccount() {
        driver.get(ADDR_MAIN_PAGE);
        MainPage mainPage = new MainPage(driver);
        mainPage.logginOnButtonPersonalAccount();
        Assert.assertTrue("User not login", mainPage.getResultLogOnButtonPersonalAccount());
    }

    @Test
    @DisplayName("Test loggin on button from form registration")
    public void testLogOnButtonFromRegistration() {
        driver.get(ADDR_REGISTRATOR);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.logOnButtonEnter();
        Assert.assertTrue("User not login", registrationPage.getResultSuccesRegistration());
    }

    @Test
    @DisplayName("Test loggin on button from form recovery password")
    public void testLogFromRecoveryPassword() {
        driver.get(ADDR_RECOVERY_PASSWORD);
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.logOnButtonEnter();
        Assert.assertTrue("User not login", forgotPasswordPage.getResultSuccesRegistration());
    }
}
