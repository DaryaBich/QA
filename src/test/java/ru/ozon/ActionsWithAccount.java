package ru.ozon;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.Thread.sleep;

public class ActionsWithAccount {
    private By buttonLogIn = By.cssSelector("div[class=\"b6w6 c2v0\"]");
    private By inputPhone = By.name("phone");
    private By getCode = By.cssSelector("div[class=\"ui-b ui-f9 ui-h4\"]");

    public boolean logInToAccount(ChromeDriver chromeDriver, WebDriverWait waiting) throws InterruptedException {
        waiting.until(ExpectedConditions.presenceOfElementLocated(buttonLogIn));
        chromeDriver.findElement(buttonLogIn).click();
        waiting.until(ExpectedConditions.presenceOfElementLocated(inputPhone));
        chromeDriver.findElement(inputPhone).sendKeys("9271091935");
        chromeDriver.findElement(getCode).click();
        sleep(20000);
        String isCabinet = chromeDriver.findElement(By.cssSelector("div[class=\"b9x5 c2v0\"]"))
                .getAttribute("textContent");
        return isCabinet.equals("0 Кабинет ");
    }
}
