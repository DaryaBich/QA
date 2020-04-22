package ru.ozon;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Thread.sleep;

public class FourthScene extends WebDriverSettings {
    @Test
    public void forth() throws InterruptedException {
        WebDriverWait webDriverWait = new WebDriverWait(chromeDriver, 60);
        ThirdScene thirdScene = new ThirdScene();
        thirdScene.searchJuicersCatalog(chromeDriver, webDriverWait);
        sleep(1000);
        thirdScene.searchJuicerWithCondition(chromeDriver, webDriverWait);
        sleep(1000);
        thirdScene.chooseSorting(chromeDriver, webDriverWait);
        sleep(1000);
        sortingByEnergy(chromeDriver, webDriverWait);
        sleep(1000);
        Assert.assertTrue(thirdScene.addProduct(chromeDriver, webDriverWait));
    }

    private void sortingByEnergy(ChromeDriver chromeDriver, WebDriverWait webDriverWait) {
        WebElement min = chromeDriver.findElement(By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/div[2]/div[2]/div[1]/div" +
                "/aside/div[10]/div[2]/div[2]/div[1]/input"));
        min.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        min.sendKeys("1000", Keys.ENTER);
    }
}
