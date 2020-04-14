package ru.ozon;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.EmptyStackException;

import static java.lang.Thread.sleep;

public class FirstScene extends WebDriverSettings {
    @Test
    public void loginAccountTest() throws InterruptedException {
        loginAccount(chromeDriver);
        sleep(20000); // время для ввода пароля
        Assert.assertEquals("Кабинет", chromeDriver.findElement(
                By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/header/div[1]/div[4]/div[1]/a/span")).getAttribute("textContent"));
    }

    public void loginAccount(ChromeDriver chromeDriver) throws InterruptedException {
        // кнопка войти
        (chromeDriver.findElement(By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/header/div[1]/div[4]/div[1]/div[1]")))
                .click();
        sleep(100);
        // войти по почте
        (chromeDriver.findElement(By.xpath("/html/body/div[3]/div/div/div/div/div/div/div/div/div[4]/a[1]"))).click();
        // клик по полю ввода
        (chromeDriver.findElement(By.xpath("/html/body/div[3]/div/div/div/div/div/div/div/div[2]/label/div"))).click();
        // Ввод номера телефона в поле
        (chromeDriver.findElement(By.xpath("/html/body/div[3]/div/div/div/div/div/div/div/div[2]/label/div/input")))
                .sendKeys("ya.cool-Darya1202@yandex.ru");
        // Нажатие кнопки получить код
        (chromeDriver.findElement(By.xpath("/html/body/div[3]/div/div/div/div/div/div/div/div[3]/button"))).click();
    }
}
