package TestCases;

import Base.BaseTest;
import com.amazonautomation.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class AmazonTest extends BaseTest {
    private List<String> addedProductNames = new ArrayList<>();


    @Test
    public void testLogin() {
        // Initialize LoginPage with WebDriver
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        VideoGamesPage videoGamesPage = new VideoGamesPage(driver);
        VideoGamePrices videoGamePrices = new VideoGamePrices(driver);
        CartPage cartPage = new CartPage(driver);
        ProceedToBuy proceedToBuy = new ProceedToBuy(driver);

        // Perform Login Steps
        loginPage.clickSignIn();
        loginPage.enterEmail("dagnder1@gmail.com");  // Replace with test email
        loginPage.clickContinue();
        loginPage.enterPassword("010164Aa");  // Replace with test password
        loginPage.clickLogin();

        // Home page handling
        homePage.pressMenu();
        homePage.openSeeAll();
        homePage.openCategory();
        homePage.selectVideogames();


        // Applying filtering and sorting
        videoGamesPage.applyFreeShippingFilter();
        videoGamesPage.applyNewConditionFilter();
        videoGamesPage.sortByHighToLow();
        videoGamePrices.addAllProductsUnderPrice(15000);

        // Cart opening and asserting products added
        cartPage.openCart();
        boolean allItemsPresent = cartPage.verifyCartItems(addedProductNames);
        Assert.assertTrue(allItemsPresent, "Not all products were found in the cart!");

        // Proceeding the request and filling data
        proceedToBuy.pressProceed();
        proceedToBuy.pressAddress();
        proceedToBuy.FillData();


    }

}
