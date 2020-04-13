package ru.ozon;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.EmptyStackException;

import static java.lang.Thread.sleep;

public class FirstScene extends WebDriverSettings{
    @Test
    public void firstTest() throws InterruptedException {
        By EMAIL_FIELD = By.tagName("INPUT");
        // кнопка войти
        (chromeDriver.findElement(By.className("c2b1"))).click();
        // нажатие на поле ввода для установки курсора
        WebElement webElement = chromeDriver.findElement(EMAIL_FIELD);
        (webElement).click();
        // Ввод номера телефона в поле
        (chromeDriver.findElement(By.cssSelector("input[type='text']"))).sendKeys("9271091935");
        // Нажатие кнопки получить код
        (chromeDriver.findElement(By.xpath("/html/body/div[3]/div/div/div/div/div/div/div/div/div[3]/button"))).click();
        sleep(10000);
        //Assert.assertTrue(chromeDriver.findElement(By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/header/div[1]/div[4]/div[1]/a/span")).getText().equals("Кабинет"));
    }

}
