package com.amazonautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By cartItems = By.xpath("//div[@class='sc-list-item-content']//span[@class='a-truncate-cut']");
    private By cartPrices = By.xpath("//div[@class='sc-item-price-block']//span[contains(@class, 'sc-price')]");
    private By cartIcon = By.id("nav-cart"); // Cart icon to navigate to the cart page

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Navigate to the cart page
    public void openCart() {
        WebElement element = driver.findElement(cartIcon);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", element);
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon));
        driver.findElement(cartIcon).click();
    }

    // Get all product names from the cart
    public List<WebElement> getCartProducts() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(cartItems));
    }

    // Verify all expected products are in the cart
    public boolean verifyCartItems(List<String> expectedProducts) {
        driver.get("https://www.amazon.eg/gp/cart/view.html"); // Navigate to Cart Page
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartItems));

        List<WebElement> cartProductElements = driver.findElements(cartItems);
        List<String> cartProducts = new ArrayList<>();

        for (WebElement product : cartProductElements) {
            cartProducts.add(product.getText().trim());
        }

        // Compare expected products with cart items
        for (String expectedProduct : expectedProducts) {
            if (!cartProducts.contains(expectedProduct)) {
                System.out.println("❌ Missing product in cart: " + expectedProduct);
                return false;
            }
        }

        System.out.println("✅ All products successfully added to cart!");
        return true;
    }
}