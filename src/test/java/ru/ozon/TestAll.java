package ru.ozon;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestAll extends WebDriverSettings {
    WebDriverWait waiting;
    @Test
    public void thirdScene() throws InterruptedException {
        waiting = new WebDriverWait(chromeDriver, 60);
        ActionsWithShopPage actionsWithShopPage = new ActionsWithShopPage();
        actionsWithShopPage.openCatalog(chromeDriver, waiting);
        boolean checkJuicerOnPrice = actionsWithShopPage.searchWithPriceRange(chromeDriver, waiting);
        actionsWithShopPage.sortByPriceAndAddProduct(chromeDriver, waiting, false);
        boolean checkPrice = actionsWithShopPage.changeCountAndCheckPrice(chromeDriver, waiting);
        Assert.assertTrue(checkJuicerOnPrice && checkPrice);
    }
    @Test
    public void firstScene() throws InterruptedException {
        waiting = new WebDriverWait(chromeDriver, 60);
        boolean result = (new ActionsWithAccount()).logInToAccount(chromeDriver, waiting);
        Assert.assertTrue(result);
    }

    @Test
    public void secondScene() throws InterruptedException {
        waiting = new WebDriverWait(chromeDriver, 60);
        boolean checkLogInToAccount = (new ActionsWithAccount()).logInToAccount(chromeDriver, waiting);
        boolean checkChangingCity = (new ActionsWithCity()).changeCityAndCheck(chromeDriver, waiting);
        Assert.assertTrue(checkChangingCity );//&& checkLogInToAccount);
    }

    @Test
    public  void forthScene() throws InterruptedException {
        waiting = new WebDriverWait(chromeDriver, 60);
        ActionsWithShopPage actionsWithShopPage = new ActionsWithShopPage();
        actionsWithShopPage.openCatalog(chromeDriver, waiting);
        boolean checkJuicerOnPrice = actionsWithShopPage.searchWithPriceRange(chromeDriver, waiting);
        actionsWithShopPage.sortByPower(chromeDriver, waiting);
        actionsWithShopPage.sortByPriceAndAddProduct(chromeDriver, waiting, true);
        boolean checkPrice = actionsWithShopPage.changeCountAndCheckPrice(chromeDriver, waiting);
        Assert.assertTrue(checkJuicerOnPrice && checkPrice);
    }
}
