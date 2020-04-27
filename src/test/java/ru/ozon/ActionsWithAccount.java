package ru.ozon;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.Thread.sleep;

public class ActionsWithAccount {
    private By buttonLogIn = By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/header/div[1]/div[4]/div[1]/div[1]");
    private By inputPhone = By.name("phone");
    private By getCode = By.xpath("/html/body/div[3]/div/div/div/div/div/div/div/div/div[3]/button/div");

    public boolean logInToAccount(ChromeDriver chromeDriver, WebDriverWait waiting) throws InterruptedException {
        waiting.until(ExpectedConditions.presenceOfElementLocated(buttonLogIn));
        chromeDriver.findElement(buttonLogIn).click();
        waiting.until(ExpectedConditions.presenceOfElementLocated(inputPhone));
        chromeDriver.findElement(inputPhone).sendKeys("9271091935");
        chromeDriver.findElement(getCode).click();
        sleep(15000);
        String isCabinet = chromeDriver.
                findElement(By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/header/div[1]/div[4]/div[1]/a"))
                .getAttribute("textContent");
        return isCabinet.equals("0 Кабинет");
    }
}
