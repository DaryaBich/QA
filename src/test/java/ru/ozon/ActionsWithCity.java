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
        cityNowBy = By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/div[2]/div/button/span");
        waiting.until(ExpectedConditions.presenceOfElementLocated(cityNowBy));
        cityNowWeb = chromeDriver.findElement(cityNowBy);
        cityNowWeb.click();
        inputBoxBy = By.xpath("//*[@id=\"__nuxt\"]/div/div[2]/div/div/div/div/div/label/div/input");
        waiting.until(ExpectedConditions.presenceOfElementLocated(inputBoxBy));
        inputBoxWeb = chromeDriver.findElement(inputBoxBy);
        inputBoxWeb.click();
        inputBoxWeb.sendKeys("Вольск");
        waiting.until(ExpectedConditions.attributeToBe(chromeDriver.findElement(
                By.xpath("//*[@id=\"__nuxt\"]/div/div[2]/div/div/div/div/ul/li[1]/a")), "innerText",
                "Вольск, Саратовская область"));
        inputBoxWeb.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        waiting.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/div[2]/div/ul/li[5]/div/a")));
        chromeDriver.findElement(By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/div[2]/div/ul/li[5]/div/a")).click();
        cityToDelivery = By.className("city-name");
        waiting.until(ExpectedConditions.presenceOfElementLocated(cityToDelivery));
        return chromeDriver.findElement(cityToDelivery).getAttribute("innerText")
                .equals("Вольск");
    }

}
