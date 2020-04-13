package ru.ozon;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.security.Key;

import static java.lang.Thread.sleep;

public class SecondScene extends WebDriverSettings{
    @Test
    public void updateCity() throws InterruptedException {
        // нажатие клавиши с названием текущего города
        WebElement city = chromeDriver.findElement(By.tagName("SPAN"));
        city.click();
        // Блок для записи искомого города
        WebElement inputBox = chromeDriver.findElement(By.className("ui-au3"));
        // Вводим в строку поиска город Вольск
        inputBox.sendKeys((CharSequence) "Вольск");
        sleep(1000);
        // Выбираем первый город из поиска - клавиша вниз и сохраняем выбор - клавиша enter
        inputBox.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        String newCity = city.getText();
        String myCity = "Вольск";
        System.out.println(newCity.equals(myCity));
    }
}
