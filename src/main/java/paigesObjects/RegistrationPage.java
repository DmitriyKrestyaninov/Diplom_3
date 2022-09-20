package paigesObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testData.User;

import static constants.adressOfPages.ADDR_LOGIN;

public class RegistrationPage {
    // Логотип сайта
    private By logoOfMainPage = By.className("AppHeader_header__logo__2D0X2");

    //Поле "Имя"
    private By fieldNameDefault = By.cssSelector("#root > div > main > div > form > fieldset:nth-child(1) > div > div");
    private By fieldNameActive = By.cssSelector("#root > div > main > div > form > fieldset:nth-child(1) > div > div > input");

    // Поле "Email"
    private By fieldEmailDefault = By.cssSelector("#root > div > main > div > form > fieldset:nth-child(2) > div > div");
    private By fieldEmailActive = By.cssSelector("#root > div > main > div > form > fieldset:nth-child(2) > div > div > input");

    //Поле "Пароль"
    private By fieldPasswordDefault = By.cssSelector("#root > div > main > div > form > fieldset:nth-child(3) > div > div");
    private By fieldPasswordActive = By.cssSelector("#root > div > main > div > form > fieldset:nth-child(3) > div > div > input");

    //Кнопка "Зарегистрироваться"
    private By buttonRegistration = By.cssSelector("#root > div > main > div > form > button");

    //Логотип "Вход"
    private By buttonEnter = By.className("Auth_link__1fOlj");

    //Подсказка "Неверный пароль"
    private By incorrectPassword = By.cssSelector("#root > div > main > div > form > fieldset:nth-child(3) > div > p");
    //Логотип "Вход"
    private By logoInput = By.cssSelector("#root > div > main > div > h2");

    private WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    private void waitForLoadRegistrationPage() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(logoOfMainPage));
    }

    private void setNameUser(String name) {
        driver.findElement(fieldNameDefault).click();
        driver.findElement(fieldNameActive).sendKeys(name);
    }

    private void setEmail(String email) {
        driver.findElement(fieldEmailDefault).click();
        driver.findElement(fieldEmailActive).sendKeys(email);
    }

    private void setPassword(String password) {
        driver.findElement(fieldPasswordDefault).click();
        driver.findElement(fieldPasswordActive).sendKeys(password);
    }

    private void clickButtonRegistration() {
        driver.findElement(buttonRegistration).click();
    }

    public Boolean getResultSuccesRegistration() {
        new WebDriverWait(driver,3).until(ExpectedConditions.visibilityOfElementLocated(logoInput));
        return driver.findElement(logoInput).isDisplayed();
    }
    public Boolean getResultRegistrationIncorrectPassword(){
        return driver.findElement(incorrectPassword).isDisplayed();
    }

    public void registerUser(WebDriver driver, User user){
        waitForLoadRegistrationPage();
        setNameUser(user.getName());
        setEmail(user.getEmail());
        setPassword(user.getPassword());
        clickButtonRegistration();
    }
    public void logOnButtonEnter(){
        waitForLoadRegistrationPage();
        clickOnButtonEnter();
        getResultSuccesRegistration();
    }

    private void clickOnButtonEnter() {
        driver.findElement(buttonEnter).click();
    }
}
