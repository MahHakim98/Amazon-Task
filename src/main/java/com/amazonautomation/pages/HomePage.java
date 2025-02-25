package com.amazonautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private static WebDriverWait wait;

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10s wait
        PageFactory.initElements(driver, this);
    }

    // Elements
    private By allmenubutton = By.id("nav-hamburger-menu");
    private By videoGamesCategory = By.xpath("//div[text()='Video Games']");
    private By allVideoGamesLink = By.xpath("/html/body/div[3]/div[2]/div[2]/ul[16]/li[3]/a\n");
    private By seemore = By.xpath("//a[@class='hmenu-item hmenu-compressed-btn' and @aria-label='See All Categories']\n");


    // Actions
    public void pressMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(allmenubutton));
        driver.findElement(allmenubutton).click();
    }

    public void openCategory() {
        wait.until(ExpectedConditions.presenceOfElementLocated(videoGamesCategory));
        driver.findElement(videoGamesCategory).click();
    }

    public void selectVideogames() {
        WebElement element = driver.findElement(allVideoGamesLink);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", element);
    }

    public void openSeeAll(){
        driver.findElement(seemore).click();
    }

}