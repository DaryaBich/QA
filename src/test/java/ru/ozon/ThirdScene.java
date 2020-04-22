package ru.ozon;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Predicate;

import static java.lang.Thread.sleep;

public class ThirdScene extends WebDriverSettings {
    @Test
    public void third() throws InterruptedException {
        WebDriverWait webDriverWait = new WebDriverWait(chromeDriver, 60);
        Assert.assertTrue(searchJuicer(chromeDriver, webDriverWait));
    }

    public boolean searchJuicer(ChromeDriver chromeDriver, WebDriverWait webDriverWait) throws InterruptedException {
        searchJuicersCatalog(chromeDriver, webDriverWait);
        searchJuicerWithCondition(chromeDriver, webDriverWait);
        sleep(1000);
        chooseSorting(chromeDriver, webDriverWait);
        return addProduct(chromeDriver, webDriverWait);
    }

    public void searchJuicersCatalog(ChromeDriver chromeDriver, WebDriverWait webDriverWait)
            throws InterruptedException {
        (chromeDriver.findElement(By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/header/div[1]/div[2]/div/div[1]/button")))
                .click();
        (chromeDriver.findElement(
                By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/header/div[1]/div[2]/div/div[2]/div/div[1]/div/a[12]")))
                .click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/div" +
                "[2]/div/div[1]/aside/div[2]/span")));
        ((JavascriptExecutor) chromeDriver).executeScript("arguments[0].scrollIntoView();"
                , chromeDriver.findElement(By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/div[2]/div/div[1]/aside/div[2]" +
                        "/span")));
        // нажатие на кнопку - техника для кухни
        (chromeDriver.findElement(By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/div[2]/div/div[1]/aside/div[2]/span")))
                .click();
        String juiceMix = "//*[@id=\"__nuxt\"]/div/div[1]/div[2]/div/div[1]/aside/div[2]/div[6]";
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(juiceMix)));
        // скроллим до соковыжималок
        ((JavascriptExecutor) chromeDriver).executeScript("arguments[0].scrollIntoView();"
                , chromeDriver.findElement(By.xpath(juiceMix)));
        // нажатие на кнопку - соковыжималки
        (chromeDriver.findElement(By.xpath(juiceMix))).click();
    }

    void searchJuicerWithCondition(ChromeDriver chromeDriver, WebDriverWait webDriverWait)
            throws InterruptedException {
        By min = By.xpath("/html/body/div[1]/div/div[1]/div[2]/div[2]/div[1]/div/aside/div[2]/div[2]/div[2]/div[1]" +
                "/input");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(min));
        ((JavascriptExecutor) chromeDriver).executeScript("arguments[0].scrollIntoView();"
                , chromeDriver.findElement(min));
        WebElement minCost = chromeDriver.findElement(min);
        // поле со значением минимальной стоимости
        // стираем записанное значение
        minCost.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        // пишем новое
        minCost.sendKeys("3000");
        // поле записи макс стоимости
        WebElement maxCost = chromeDriver.findElement(By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/div[2]/div[2]/div[1]" +
                "/div/aside/div[2]/div[2]/div[2]/div[2]/input"));
        maxCost.click();
        sleep(1000);
        // стираем имеющееся значение
        maxCost.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        // записываем новое значение
        maxCost.sendKeys("4000");
        sleep(1000);
        // запускаем сортировку
        maxCost.sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/div[2]/div[2]/div[2]/div[3]/div[1]")));
        Assert.assertTrue(checkingElements(chromeDriver,
                webDriverWait, j -> Double.parseDouble(j) >= 3000.0 && Double.parseDouble(j) <= 4000.0));
    }

    public boolean checkingElements(ChromeDriver chromeDriver, WebDriverWait webDriverWait,
                                    Predicate<String> condition) throws InterruptedException {
        sleep(1000);
        List<WebElement> juicers = chromeDriver.findElementsByCssSelector("div[class=\"a4b4 a4b7 a4b6\"]");
        List<String> prices = new ArrayList<>();
        for (WebElement w : juicers) {
            WebElement webElement = w.findElement(By.cssSelector("div[class=\"a5n1 a5n8 a5n3\"]"));
            String cost = webElement.getAttribute("textContent");
            prices.add(cost.substring(0, cost.indexOf("₽"))
                    .replace("₽", "")
                    .replace(" ", ""));
        }

        for (String price : prices) {
            if (!condition.test(price)) {
                return false;
            }
        }
        return true;
    }

    public void chooseSorting(ChromeDriver chromeDriver, WebDriverWait webDriverWait) throws InterruptedException {
        By sort = By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/div[2]/div[2]/div[2]/div[2]/div[1]/div/div");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(sort));
        WebElement sorter = chromeDriver.findElement(sort);
        sorter.click();
        sleep(1000);
        (chromeDriver.findElement(By.cssSelector(
                "input[class=\"ui-a1f3\"]")))
                .sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
        WebElement sortingBy = chromeDriver.findElement(By.cssSelector("div[class=\"ui-a1k9\"]"));
        Assert.assertEquals(sortingBy.getAttribute("outerText"), "Сначала дешевые");
    }

    public boolean addProduct(ChromeDriver chromeDriver, WebDriverWait webDriverWait)
            throws InterruptedException {
        By adding = By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/div[2]/div[2]/div[2]/div[3]/div[1]/div/div/div[1]/div/div" +
                "/div[3]/div[2]/div/div/button/div");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(adding));
        (chromeDriver.findElement(adding)).click();
        sleep(1000);
        (chromeDriver.findElement(By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/header/div[1]/div[4]/a[2]"))).click();
        By addFive = By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/div/div/div[3]/div[5]/div[1]/div[1]/div/div[2]/div[3]/" +
                "div[4]/div/div[1]/div/div[1]/div/div");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(addFive));
        WebElement summa = chromeDriver.findElement(By.cssSelector("span[class=\"b7w4\"]"));
        StringBuilder sB = new StringBuilder();
        String s = summa.getAttribute("outerText");
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                sB.append(s.charAt(i));
            }
        }
        double sum = Double.parseDouble(sB.toString());
        chromeDriver.findElement(addFive).click();
        (chromeDriver.findElement(By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/div/div/div[3]/div[5]/div[1]/div[1]/div/" +
                "div[2]/div[3]/div[4]/div/div[1]/div/div[1]/div/div/input")))
                .sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
        sleep(1000);
        StringBuilder sB2 = new StringBuilder();
        String s2 = summa.getAttribute("outerText");
        for (int i = 0; i < s2.length(); i++) {
            if (Character.isDigit(s2.charAt(i))) {
                sB2.append(s2.charAt(i));
            }
        }
        double d = Double.parseDouble(sB2.toString());
        return d == sum * 5;
    }
}
