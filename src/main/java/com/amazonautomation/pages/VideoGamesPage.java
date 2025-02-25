package com.amazonautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class VideoGamesPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By freeShippingFilter = By.xpath("//span[text()='Free Shipping']/ancestor::a");
    private By newConditionFilter = By.xpath("//span[text()='New']/ancestor::a");
    private By sortDropdown = By.id("s-result-sort-select");


    // Constructor
    public VideoGamesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Click Free Shipping
    public void applyFreeShippingFilter() {
        WebElement freeShipping = wait.until(ExpectedConditions.elementToBeClickable(freeShippingFilter));
        freeShipping.click();
    }

    // Click New Condition
    public void applyNewConditionFilter() {
        WebElement newCondition = wait.until(ExpectedConditions.elementToBeClickable(newConditionFilter));
        newCondition.click();
    }

    public void sortByHighToLow() {
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(sortDropdown));
        Select select = new Select(dropdown);
        select.selectByVisibleText("Price: High to Low");
    }
}