package ru.ozon;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverSettings {
    public ChromeDriver chromeDriver;
    @Before
    public void openCite(){
        System.setProperty("webdriver.chrome.driver", "C:\\QA\\chromedriver.exe");
        chromeDriver = new ChromeDriver();
        chromeDriver.get("https://www.ozon.ru/");
        String title = chromeDriver.getTitle();
        Assert.assertTrue(title.equals("OZON — интернет-магазин. Миллионы товаров по выгодным ценам"));
    }
    @After
    public void closeCite(){
        chromeDriver.quit();
    }
}
