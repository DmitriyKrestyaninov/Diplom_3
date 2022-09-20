package paigesObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testData.User;

import static constants.adressOfPages.ADDR_LOGIN;


public class MainPage {
    private static final String selectedChapter ="tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect";

    //Логоти "Stellar burger"
    private By logoOfMainPage = By.className("AppHeader_header__logo__2D0X2");

    // Кнопка "Войти в аккаунт"
    private By buttonLogInAccount = By.xpath("//*[@id='root']/div/main/section[2]/div/button");

    //Поле "Email"
    private By fieldEmailDefault = By.cssSelector("#root > div > main > div > form > fieldset:nth-child(1) > div > div");
    private By fieldEmailActive = By.cssSelector("#root > div > main > div > form > fieldset:nth-child(1) > div > div > input");

    //Поле "Пароль"
    private By fieldPasswordDefault = By.cssSelector("#root > div > main > div > form > fieldset:nth-child(2) > div > div");
    private By fieldPasswardActive = By.cssSelector("#root > div > main > div > form > fieldset:nth-child(2) > div > div > input");

    //Кнопка  "Войти"
    private By buttonLogIn = By.cssSelector("#root > div > main > div > form > button");

    // Кнопка входа в личный кабинет
    private By buttonLogPrivateAccount = By.cssSelector("#root > div > header > nav > a > p");

    //Лого "Конструктор"
    private By logoConstructor = By.cssSelector("#root > div > header > nav > ul > li:nth-child(1) > a");

    //кнопка Профиль
    private By profile = By.xpath("//*[@id=\"root\"]/div/main/div/nav/ul/li[1]/a");

    // Раздел "Соберите бургер"
    private By createBurger = By.className("App_componentContainer__2JC2W");

    //Кнопка выхода из аккаунта
    private By buttonExit = By.cssSelector("#root > div > main > div > nav > ul > li:nth-child(3) > button");

    // Раздел входа в аккаунт
    private By logoInput = By.cssSelector("#root > div > main > div > h2");

    //Соусы
    private By sauces = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[1]/div[2]");

    //Булки
    private By suns = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[1]/div[1]");

    // Начинки
    private By fillings = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[1]/div[3]");

    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private void waitForLoadHomePage() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(logoOfMainPage));
    }

    public void clickOnButtonInputAccount() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(buttonLogInAccount));
        driver.findElement(buttonLogInAccount).click();
    }

    public void logginOnButtonLogAccount(User user) {
        waitForLoadHomePage();
        clickOnButtonInputAccount();
        setEmail(user.getEmail());
        setPassword(user.getPassword());
        clickOnButtonLogIn();
    }

    public void logginOnButtonPersonalAccount() {
        waitForLoadHomePage();
        clickOnButtonPrivateAccount();
    }

    private void clickOnButtonLogIn() {
        driver.findElement(buttonLogIn).click();
    }

    public void clickOnButtonPrivateAccount() {
        driver.findElement(buttonLogPrivateAccount).click();
    }

    private void setPassword(String password) {
        driver.findElement(fieldPasswordDefault).click();
        driver.findElement(fieldPasswardActive).sendKeys(password);
    }

    private void setEmail(String email) {
        driver.findElement(fieldEmailDefault).click();
        driver.findElement(fieldEmailActive).sendKeys(email);
    }

    public Boolean getResultSuccesLogin() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(logoConstructor));
        return driver.findElement(logoConstructor).isDisplayed();
    }

    public boolean getResultLogOnButtonPersonalAccount() {
        return driver.getCurrentUrl().equals(ADDR_LOGIN);
    }

    public boolean getResultMovePersonalAccount() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(profile));
        return driver.findElement(profile).isDisplayed();
    }

    public void clickOnButtonConstructor() {
        driver.findElement(logoConstructor).click();
    }

    public boolean getResultMoveConstructor() {
        return driver.findElement(createBurger).isDisplayed();
    }

    public void clickOnButtonExit() {
        new WebDriverWait(driver, 4).until(ExpectedConditions.visibilityOfElementLocated(buttonExit));
        driver.findElement(buttonExit).click();
    }

    public boolean getResultExitAccount() {
        new WebDriverWait(driver, 2).until(ExpectedConditions.visibilityOfElementLocated(logoInput));
        return driver.findElement(logoInput).isDisplayed();
    }
    public void clickSauces(){
        new WebDriverWait(driver, 4).until(ExpectedConditions.visibilityOfElementLocated(sauces));
        driver.findElement(sauces).click();
    }

    public void clickSuns(){
        new WebDriverWait(driver, 4).until(ExpectedConditions.visibilityOfElementLocated(suns));
        driver.findElement(suns).click();
    }
    public void clickFillings(){
        new WebDriverWait(driver, 4).until(ExpectedConditions.visibilityOfElementLocated(fillings));
        driver.findElement(fillings).click();
    }

    public boolean getResultClickSauces() {
        return driver.findElement(sauces).getAttribute("class").equals(selectedChapter);
    }
    public boolean getResultClickSuns() {
        return driver.findElement(suns).getAttribute("class").equals(selectedChapter);
    }
    public boolean getResultClickFillings() {
        return driver.findElement(fillings).getAttribute("class").equals(selectedChapter);
    }
}


