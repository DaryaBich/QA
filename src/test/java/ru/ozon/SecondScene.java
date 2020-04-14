package ru.ozon;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import static java.lang.Thread.sleep;

public class SecondScene extends WebDriverSettings  {
    public WebElement city;

    @Test
    // Изменение города и проверка, что он изменился
    public void updateCityTest() throws InterruptedException {
        updateCity();
        sleep(1000);
        // сравниваем текущий город с выставленным
        Assert.assertEquals(city.getAttribute("outerText"), "Вольск");
    }

    @Test
    // вход в аккаунт, изменение города и сравнение города доставки и текущего
    public void loginAndCheck() throws InterruptedException {
        (new FirstScene()).loginAccount(chromeDriver); // авторизация на сайте
        sleep(20000);
        // меняем город
        updateCity();
        sleep(1000);
        // нажатие на кнопку пункты выдачи, для просмотра города
        (chromeDriver.findElement(By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/div[2]/div/ul/li[5]/div/a/span"))).click();
        // проверка совпадения городов
        String cityForDelivery = "//*[@id=\"__nuxt\"]/div/div[1]/div[3]/div[3]/div/div[2]/div[2]/div/div[2]/div/span" +
                "/span";
        city = chromeDriver.findElement(By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/div[2]/div/button/span"));
        Assert.assertEquals(city.getAttribute("outerText"),
                chromeDriver.findElement(By.xpath(cityForDelivery)).getAttribute("textContent"));
    }

    // обновление города
    public void updateCity() throws InterruptedException {
        city = chromeDriver.findElement(By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/div[2]/div/button"));
        // нажатие клавиши с названием текущего города
        city.click();
        // Блок для записи искомого города
        WebElement inputBox = chromeDriver.findElement(By.className("ui-au3"));
        // Вводим в строку поиска город Вольск
        inputBox.sendKeys("Вольск");
        sleep(1000);
        // Выбираем первый город из поиска - клавиша вниз и сохраняем выбор - клавиша enter
        inputBox.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
    }
}
