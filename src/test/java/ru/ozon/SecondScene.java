package ru.ozon;


import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.Thread.sleep;

public class SecondScene extends WebDriverSettings  {

    @Test
   @Description("Обновление города на Вольск, проверка выставления")
    public void updateCityTest() throws InterruptedException {
        updateCity();
        sleep(1000);
        // сравниваем текущий город с выставленным
        String city = (chromeDriver.findElement(By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/div[1]/div/button/span")))
                .getAttribute("innerText");
        Assert.assertEquals(city, "Вольск");
    }

    @Test
    @Description(value = "вход в аккаунт, изменение города и сравнение города доставки и текущего")
    public void loginAndCheck() throws InterruptedException {
        WebDriverWait webDriverWait = new WebDriverWait(chromeDriver,30);
        (new FirstScene()).loginAccount(chromeDriver); // авторизация на сайте
        // меняем город
        updateCity();
        webDriverWait.until(ExpectedConditions.visibilityOf(chromeDriver.findElement(
                By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/div[1]/div/ul/li[5]/div/a/span"))));
        sleep(100);
        // нажатие на кнопку пункты выдачи, для просмотра города
        (chromeDriver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[1]/div/ul/li[5]"))).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"__nuxt\"]/div/div[1]" +
                "/div[2]/div[3]/div/div[1]")));
        // проверка совпадения городов
        WebElement cityForDelivery = chromeDriver.findElement(By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/div[2]/div[3]" +
                "/div/div[2]/div[2]/div/div[2]/div/span/span"));
        WebElement city = chromeDriver.findElement(By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/div[1]/div/button/span"));
        Assert.assertEquals(city.getAttribute("outerText"),cityForDelivery.getAttribute("textContent"));
    }

    @Step("Обновление города")
    public void updateCity() throws InterruptedException {
        WebDriverWait webDriverWait = new WebDriverWait(chromeDriver, 30);
        WebElement city = chromeDriver.findElement(By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/div[1]/div/button/span"));
        // нажатие клавиши с названием текущего города
        city.click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(city));
        // Блок для записи искомого города
        WebElement inputBox = chromeDriver.findElement(By.className("ui-au3"));
        // Вводим в строку поиска город Вольск
        inputBox.sendKeys("Вольск");
        webDriverWait.until(ExpectedConditions.attributeToBe(chromeDriver.findElement(
                By.xpath("//*[@id=\"__nuxt\"]/div/div[2]/div/div/div/div/ul/li[1]/a")), "outerText",
                "Вольск, Саратовская область"));
        // Выбираем первый город из поиска - клавиша вниз и сохраняем выбор - клавиша enter
        inputBox.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
    }
}
