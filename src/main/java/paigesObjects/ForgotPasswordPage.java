package paigesObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static constants.adressOfPages.ADDR_LOGIN;

public class ForgotPasswordPage {
    //Логотип  бургера
    private By logoOfMainPage = By.className("AppHeader_header__logo__2D0X2");

    //Кнопка "Войти"
    private By buttonEnter = By.className("Auth_link__1fOlj");
    private WebDriver driver;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    public void logOnButtonEnter() {
        waitForLoadRegistrationPage();
        clickOnButtonEnter();
        getResultSuccesRegistration();
    }

    private void waitForLoadRegistrationPage() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(logoOfMainPage));
    }

    private void clickOnButtonEnter() {
        driver.findElement(buttonEnter).click();
    }

    public Boolean getResultSuccesRegistration() {
        return driver.getCurrentUrl().equals(ADDR_LOGIN);
    }
}

