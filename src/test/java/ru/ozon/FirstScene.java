package ru.ozon;
import org.junit.Assert;
import org.junit.Test;

public class TestLogIn extends WebDriverSettings{
    @Test
    public void firstTest(){
        chromeDriver.get("https://www.ozon.ru/");
        String title = chromeDriver.getTitle();
        Assert.assertTrue(title.equals("OZON — интернет-магазин. Миллионы товаров по выгодным ценам"));
    }

}
