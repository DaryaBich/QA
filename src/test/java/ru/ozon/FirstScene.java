package ru.ozon;

import io.qameta.allure.*;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import static java.lang.Thread.sleep;

public class FirstScene extends WebDriverSettings {
    @Test
    @Description(value = "Вход в аккаунт и проверка значка на Кабинет")
    public void loginAccountTest() throws InterruptedException {
        loginAccount(chromeDriver);
        Assert.assertEquals("0 Кабинет", chromeDriver.findElement(
                By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/header/div[1]/div[4]/div[1]/a"))
                .getAttribute("textContent"));
    }
    @Step("Вход в аккаунт")
    public void loginAccount(ChromeDriver chromeDriver) throws InterruptedException {
        WebDriverWait webDriverWait = new WebDriverWait(chromeDriver, 30);
        WebElement buttonIn = chromeDriver.findElement(By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/header/div[1]/div[4]" +
                "/div[1]/div[1]"));
        // кнопка войти
        buttonIn.click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[3]/div/div/div/div/div/div/div/div/div[4]/a[1]")));
        // войти по почте
        (chromeDriver.findElement(By.xpath("/html/body/div[3]/div/div/div/div/div/div/div/div/div[4]/a[1]"))).click();
        // клик по полю ввода
        (chromeDriver.findElement(By.xpath("/html/body/div[3]/div/div/div/div/div/div/div/div[2]/label/div"))).click();
        // Ввод номера телефона в поле
        (chromeDriver.findElement(By.xpath("/html/body/div[3]/div/div/div/div/div/div/div/div[2]/label/div/input")))
                .sendKeys("ya.cool-Darya1202@yandex.ru");
        // Нажатие кнопки получить код
        (chromeDriver.findElement(By.xpath("/html/body/div[3]/div/div/div/div/div/div/div/div[3]/button"))).click();
        sleep(15000);
    }
}
