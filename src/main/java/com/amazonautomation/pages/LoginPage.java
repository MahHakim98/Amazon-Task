package com.amazonautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;


    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Define wait
        PageFactory.initElements(driver, this);
    }

    // Locators
    private By signInButton = By.id("nav-link-accountList");
    private By emailField = By.xpath("//input[@id='ap_email' or @id='ap_email_login']");
    private By continueButton = By.id("continue");
    private By passwordField = By.id("ap_password");
    private By loginButton = By.id("signInSubmit");

    // Actions
    public void clickSignIn() {
        wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        driver.findElement(signInButton).click();
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.presenceOfElementLocated(emailField));
        driver.findElement(emailField).sendKeys(email);
    }

    public void clickContinue() {
        driver.findElement(continueButton).click();
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }
}
