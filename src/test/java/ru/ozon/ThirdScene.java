package ru.ozon;

import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.ui.Select;

import static java.lang.Thread.sleep;

public class ThirdScene extends WebDriverSettings {
    @Test
    public void openCatalog() throws InterruptedException {
        // нажатие на кнопку - электроника, чтобы перейти к технике
        (chromeDriver.findElement(By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/header/div[2]/div/ul/li[8]"))).click();
        sleep(2000); // ожидание пока загрузится
        // нажатие на кнопку - бытовая техника, чтобы открыть каталог бытовой техники
        (chromeDriver.findElement(By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/div[3]/div[3]/div/div/div/div/div[6]")))
                .click();
        sleep(2000);// ожидание пока загрузится
        ((JavascriptExecutor)chromeDriver).executeScript("arguments[0].scrollIntoView();"
                , chromeDriver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[3]/div/div[1]/aside/div[2]/a")));
        // нажатие на кнопку - техника для кухни
        (chromeDriver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[3]/div/div[1]/aside/div[2]/a"))).click();
        sleep(2000);// ожидание пока загрузится
        String juiceMix = "//*[@id=\"__nuxt\"]/div/div[1]/div[3]/div[2]/div[1]/div/aside/section/main/div[2]/div[2]" +
                "/div[6]/div/a";
        // скроллим до соковыжималок
        ((JavascriptExecutor)chromeDriver).executeScript("arguments[0].scrollIntoView();"
                ,chromeDriver.findElement(By.xpath(juiceMix)));
        // нажатие на кнопку - соковыжималки
        (chromeDriver.findElement(By.xpath(juiceMix))).click();
        sleep(2000);
        // поле со значением минимальной стоимости
        WebElement minCost = chromeDriver.findElement(By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/div[3]/div[2]/div" +
                "[1]/div/aside/div[2]/div[2]/div[2]/div[1]/input"));
        // стираем записанное значение
        minCost.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        // пишем новое
        minCost.sendKeys("3000");
        // поле записи макс стоимости
        WebElement maxCost = chromeDriver.findElement(By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/div[3]/div[2]/div[1]" +
                "/div/aside/div[2]/div[2]/div[2]/div[2]/input"));
        maxCost.click();
        sleep(1000);
        // стираем имеющееся значение
        maxCost.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        // записываем новое значение
        maxCost.sendKeys("4000");
        // запускаем сортировку
        maxCost.sendKeys(Keys.ENTER);
        sleep(100000);
    }
}
