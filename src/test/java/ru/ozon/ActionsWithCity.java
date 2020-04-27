package ru.ozon;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionsWithCity {
    By cityNowBy;
    WebElement cityNowWeb;
    By inputBoxBy;
    WebElement inputBoxWeb;
    By cityToDelivery;

    public boolean changeCityAndCheck(ChromeDriver chromeDriver, WebDriverWait waiting) {
        cityNowBy = By.cssSelector("span[class=\"c1z1\"]");
        waiting.until(ExpectedConditions.presenceOfElementLocated(cityNowBy));
        cityNowWeb = chromeDriver.findElement(cityNowBy);
        cityNowWeb.click();
        inputBoxBy = By.className("ui-au3");
        waiting.until(ExpectedConditions.presenceOfElementLocated(inputBoxBy));
        inputBoxWeb = chromeDriver.findElement(inputBoxBy);
        inputBoxWeb.click();
        inputBoxWeb.sendKeys("Вольск");
        waiting.until(ExpectedConditions.attributeToBe(chromeDriver.findElement(By.cssSelector("a[class=\"a9\"]")),
                "innerText", "Вольск, Саратовская область"));
        inputBoxWeb.sendKeys(Keys.ENTER);
        chromeDriver.findElements(By.className("c1l")).get(4).click();
        cityToDelivery = By.className("city-name");
        waiting.until(ExpectedConditions.presenceOfElementLocated(cityToDelivery));
        return chromeDriver.findElement(cityToDelivery).getAttribute("innerText")
                .equals("Вольск");
    }

}
