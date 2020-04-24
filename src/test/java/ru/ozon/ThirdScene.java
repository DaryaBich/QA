package ru.ozon;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

import static java.lang.Thread.sleep;

public class ThirdScene extends WebDriverSettings {
    @Test
   @Description(value = "поиск соковыжималки и добавление в корзину")
    public void third() throws InterruptedException {
        WebDriverWait webDriverWait = new WebDriverWait(chromeDriver, 60);
        Assert.assertTrue(searchJuicer(chromeDriver, webDriverWait));
    }
   @Step("Открытие каталога, сортировка и доабвление в корзину")
    public boolean searchJuicer(ChromeDriver chromeDriver, WebDriverWait webDriverWait) throws InterruptedException {
        searchJuicersCatalog(chromeDriver, webDriverWait);
        sleep(1000);
       boolean res1 = chooseSorting(chromeDriver, webDriverWait);
       sleep(1000);
        boolean res2 = searchJuicerWithCondition(chromeDriver, webDriverWait);
        sleep(1000);
        boolean res3 =  addProduct(chromeDriver, webDriverWait);
        return res1 && res2 && res3;
    }
@Step("Открытие каталога соковыжималок")
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
@Step("Выставление условий сортировки")
    public boolean searchJuicerWithCondition(ChromeDriver chromeDriver, WebDriverWait webDriverWait)
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
        return checkingElements(chromeDriver, webDriverWait);
    }
@Step("Проверка соковыжималок на стоимость")
    public boolean checkingElements(ChromeDriver chromeDriver, WebDriverWait webDriverWait) throws InterruptedException,
        NumberFormatException{
        sleep(1000);
        List<WebElement> juicers = chromeDriver.findElementsByCssSelector("div[class=\"a1r4 a1r7 a1r6\"]");
        List<String> prices = new ArrayList<String>();
        for (WebElement w : juicers) {
            WebElement webElement = w.findElement(By.cssSelector("div[class=\"a4c5 a4d1 a4c7\"]"));
            String cost = webElement.getAttribute("textContent");
            prices.add(cost.substring(0, cost.indexOf("₽"))
                    .replace(" ", "")
                    .replace(" ", ""));
        }
        sleep(1000);
        WebElement w =  juicers.get(0).findElement(By.cssSelector("a[class=\"a3p5 tile-hover-target\"]"));
       w.click();
       By put = By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/div[3]/div[2]/div[2]/div/div[3]/div[2]/div/div[1]/div/div/" +
               "div/div/div[5]/div/div/div/div/div/div/button/div/div");
       webDriverWait.until(ExpectedConditions.presenceOfElementLocated(put));
       WebElement w2 = chromeDriver.findElement(put);
       w2.click();
        for (String price : prices) {
            if (Integer.parseInt(price) < 3000.0 || Integer.parseInt(price) > 4000.0) {
                return false;
            }
        }
        return true;
    }
@Step("Сортировка по возрастанию и проверка")
    public boolean chooseSorting(ChromeDriver chromeDriver, WebDriverWait webDriverWait) throws InterruptedException {
        By sort = By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/div[2]/div[2]/div[2]/div[2]/div[1]/div/div/div[1]");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(sort));
    ((JavascriptExecutor) chromeDriver).executeScript("arguments[0].scrollIntoView();"
            , chromeDriver.findElement(sort));
        WebElement sorter = chromeDriver.findElement(sort);
        sorter.click();
        sleep(1000);
        (chromeDriver.findElement(By.cssSelector(
                "input[class=\"ui-a1f3\"]")))
                .sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
        WebElement sortingBy = chromeDriver.findElement(By.cssSelector("div[class=\"ui-a1k9\"]"));
        return  sortingBy.getAttribute("outerText").equals("Сначала дешевые");
    }
@Step("Добавление в корзину и изменения количества")
    public boolean addProduct(ChromeDriver chromeDriver, WebDriverWait webDriverWait)
            throws InterruptedException {
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
