package ru.ozon;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static java.lang.Thread.sleep;

public class ActionsWithShopPage {
    // open catalog
    private By appliance = By.className("c3b1");
    private By kitchenAppl = By.className("b3s7");
    private By juicers = By.className("b6f2");

    // search with range price
    private By range = By.cssSelector("input[class=\"ui-av9 ui-av4 ui-c7\"]");
    private WebElement minRange;
    private WebElement maxRange;

    // sortByPriceAndAddProduct
    private By sorterBy;
    private WebElement sorterWeb;

    // changeCountAndCheckPrice
    private By openBusket;
    private By priceBy;
    private WebElement priceWeb;
    private WebElement changeCount;

    // sortByPower
    private WebElement minPower;

    public void openCatalog(ChromeDriver chromeDriver, WebDriverWait waiting) {
        //электроника
        chromeDriver.findElements(By.className("c3c3")).get(7).click();
        waiting.until(ExpectedConditions.presenceOfElementLocated(appliance));
        // бытовая техника
        chromeDriver.findElements(appliance).get(5).click();
        waiting.until(ExpectedConditions.presenceOfElementLocated(kitchenAppl));
        // техника для кухни
        chromeDriver.findElements(kitchenAppl).get(5).click();
        waiting.until(ExpectedConditions.presenceOfElementLocated(juicers));
        // соковыжималки
        chromeDriver.findElements(juicers).get(7).click();
    }

    public boolean searchWithPriceRange(ChromeDriver chromeDriver, WebDriverWait waiting) throws InterruptedException {
        waiting.until(ExpectedConditions.presenceOfElementLocated(range));
        ((JavascriptExecutor) chromeDriver).executeScript("arguments[0].scrollIntoView();"
                , chromeDriver.findElements(range).get(0));
        sleep(1000);
        minRange = chromeDriver.findElements(range).get(0);
        sleep(1000);
        minRange.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        sleep(100);
        minRange.sendKeys("3000");
        sleep(2000);
        maxRange = chromeDriver.findElements(range).get(1);
        maxRange.click();
        maxRange.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        sleep(1000);
        maxRange.sendKeys("4000", Keys.ENTER);
        sleep(2000);
        return checkJuicers(chromeDriver, waiting);
    }

    public void sortByPriceAndAddProduct(ChromeDriver chromeDriver, WebDriverWait waiting) throws InterruptedException {
        sorterBy = By.cssSelector("input[class=\"ui-a1f3\"]");
        waiting.until(ExpectedConditions.presenceOfElementLocated(sorterBy));
        sorterWeb = chromeDriver.findElement(sorterBy);
        sorterWeb.click();
        sorterWeb.sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
        sleep(70000); // для ввода защиты от роботов
        List<WebElement> lst = chromeDriver.findElements(By.cssSelector("div[class=\"ui-b ui-f8\"]"));
        lst.get(1).click();
    }

    public boolean changeCountAndCheckPrice(ChromeDriver chromeDriver, WebDriverWait waiting) throws InterruptedException {
        openBusket = By.cssSelector("a[class=\"a0c1 b8b9 c2v0\"]");
        waiting.until(ExpectedConditions.presenceOfElementLocated(openBusket));
        List<WebElement> lst = chromeDriver.findElements(openBusket);
        lst.get(lst.size() - 1).click();
        priceBy = By.className("a4v1");
        waiting.until(ExpectedConditions.presenceOfElementLocated(priceBy));
        priceWeb = chromeDriver.findElement(priceBy);
        int price = parser(priceWeb.getAttribute("innerText"));
        changeCount = chromeDriver.findElement(By.className("ui-a1f3"));
        changeCount.click();
        changeCount.sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
        sleep(3000);
        int priceNow = parser(priceWeb.getAttribute("innerText"));
        String count = chromeDriver.findElement(By.cssSelector("span[class=\"a4u7\"]")).getAttribute("innerText");
        int counter = parser(String.valueOf(count.charAt(count.indexOf(")") - 1)));
        return price * counter == priceNow;
    }

    public void sortByPower(ChromeDriver chromeDriver, WebDriverWait waiting){
        minPower = chromeDriver.findElements(range).get(2);
        minPower.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        minPower.sendKeys("1000", Keys.ENTER);
    }
    public boolean checkJuicers(ChromeDriver chromeDriver, WebDriverWait waiting) {
        List<WebElement> juicers = chromeDriver.findElementsByCssSelector("div[class=\"a3s3\"]");
        boolean flag = true;
        for (int i = 0; i < juicers.size() / 5; i++) {
            String cost = juicers.get(i).getAttribute("textContent");
            StringBuilder price = new StringBuilder();
            cost = cost.substring(cost.indexOf("₽") - 6, cost.indexOf("₽"));
            for (int j = 0; j < cost.length(); j++) {
                if (Character.isDigit(cost.charAt(j))) {
                    price.append(cost.charAt(j));
                }
            }
            int intPrice = Integer.parseInt(price.toString());
            if (intPrice < 3000 || intPrice > 4000) {
                flag = false;
            }
        }
        return flag;
    }
    private int parser(String inputString){
        StringBuilder sB1 = new StringBuilder();
        for (int i = 0; i < inputString.length(); i++) {
            if (Character.isDigit(inputString.charAt(i))) {
                sB1.append(inputString.charAt(i));
            }
        }
       return Integer.parseInt(sB1.toString());
    }
}
