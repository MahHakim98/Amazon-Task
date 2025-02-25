package com.amazonautomation.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class VideoGamePrices {
    private WebDriver driver;
    private WebDriverWait wait;

    public VideoGamePrices(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Increased wait time
    }

    // Locators
    private By productContainers = By.xpath("//div[contains(@class, 's-main-slot')]//div[contains(@data-component-type, 's-search-result')]");
    private By productPrices = By.xpath(".//span[@class='a-price-whole']");
    private By addToCartButtons = By.xpath(".//button[@name='submit.addToCart' and @type='button']");
    private By nextPageButton = By.xpath("//a[contains(@class, 's-pagination-next')]");

    // Method to add all products under 15,000 to cart
    public void addAllProductsUnderPrice(int maxPrice) {
        boolean productAdded = false;

        while (true) {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productContainers)); // Ensure products load
            List<WebElement> products = driver.findElements(productContainers);

            boolean foundProductOnPage = false;

            for (int i = 0; i < products.size(); i++) {
                try {
                    // Re-locate the product element to avoid StaleElementReferenceException
                    List<WebElement> freshProducts = driver.findElements(productContainers);
                    if (i >= freshProducts.size()) break; // Avoid index out of bounds

                    WebElement product = freshProducts.get(i);
                    WebElement priceElement = product.findElement(productPrices);
                    String priceText = priceElement.getText().replace(",", "").trim();
                    int price = Integer.parseInt(priceText);

                    if (price < maxPrice) {
                        foundProductOnPage = true;
                        productAdded = true;

                        // Scroll product into view
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", product);
                        wait.until(ExpectedConditions.elementToBeClickable(priceElement));

                        // Find 'Add to Cart' button and click using JavaScript for stability
                        List<WebElement> addToCartBtns = product.findElements(addToCartButtons);
                        if (!addToCartBtns.isEmpty()) {
                            WebElement addToCartBtn = addToCartBtns.get(0);
                            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartBtn);
                            System.out.println("✅ Added product (Price: " + price + ") to cart.");

                            // **Short wait after adding to cart** (ensures page state updates)
                            Thread.sleep(2000);

                            // **Re-locate all products to avoid stale elements**
                            products = driver.findElements(productContainers);
                        } else {
                            System.out.println("❌ No 'Add to Cart' button for product (Price: " + price + ").");
                        }
                    }
                } catch (StaleElementReferenceException e) {
                    System.out.println("⚠ Stale element detected. Refreshing...");
                    driver.navigate().refresh();  // Refresh page to get fresh elements
                    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productContainers));
                    break; // Restart loop after refresh
                } catch (NoSuchElementException e) {
                    System.out.println("⚠ Skipping product due to missing price/button.");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            // Stop searching if we found and added products
            if (productAdded) {
                System.out.println("✅ All products under " + maxPrice + " added to cart. Stopping...");
                break;
            }

            // If no products under 15K were found, move to the next page
            List<WebElement> nextPage = driver.findElements(nextPageButton);
            if (!foundProductOnPage && !nextPage.isEmpty()) {
                System.out.println("➡ Moving to next page...");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextPage.get(0));
                wait.until(ExpectedConditions.presenceOfElementLocated(productContainers));
            } else {
                System.out.println("⛔ No more pages or products under " + maxPrice + " found.");
                break;
            }
        }
    }
}
