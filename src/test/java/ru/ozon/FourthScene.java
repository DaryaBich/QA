package ru.ozon;


import io.qameta.allure.Description;
import io.qameta.allure.Step;
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
    @Description(value = "Поиск соковыжималок, выставление мощности, добавление в корзину")
    public void forth() throws InterruptedException {
        WebDriverWait webDriverWait = new WebDriverWait(chromeDriver, 60);
        ThirdScene thirdScene = new ThirdScene();
        thirdScene.searchJuicersCatalog(chromeDriver, webDriverWait);
        sleep(1000);
        boolean res1 = thirdScene.chooseSorting(chromeDriver, webDriverWait);
        sortingByEnergy(chromeDriver, webDriverWait);
        sleep(1000);
        boolean res2 = thirdScene.searchJuicerWithCondition(chromeDriver, webDriverWait);
        sleep(1000);
        boolean res3 = thirdScene.addProduct(chromeDriver, webDriverWait);
        Assert.assertTrue(res1 && res2 && res3);
    }
@Step("выставление условия мощности")
    public void sortingByEnergy(ChromeDriver chromeDriver, WebDriverWait webDriverWait) {
        WebElement min = chromeDriver.findElement(By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/div[2]/div[2]/div[1]/div" +
                "/aside/div[10]/div[2]/div[2]/div[1]/input"));
        min.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        min.sendKeys("1000", Keys.ENTER);
    }
}
