package com.amazonautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProceedToBuy {
    private WebDriver driver;
    private static WebDriverWait wait;

    // Constructor
    public ProceedToBuy(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10s wait
        PageFactory.initElements(driver, this);
    }

    // Elements
    private By ProceedButton = By.xpath("//input[@name='proceedToRetailCheckout' and @type='submit']\n");
    private By addressbutton = By.id("add-new-address-desktop-sasp-tango-link");
    private By Fullname = By.id("address-ui-widgets-enterAddressFullName");
    private By Phonenumber = By.id("address-ui-widgets-enterAddressPhoneNumber");
    private By Addressline = By.id("address-ui-widgets-enterAddressLine1");
    private By BuildNo = By.id("address-ui-widgets-enter-building-name-or-number");
    private By Cityarea = By.id("address-ui-widgets-enterAddressCity");
    private By district = By.id("address-ui-widgets-enterAddressDistrictOrCounty");
    private By useaddress = By.id("checkout-primary-continue-button-id");





    // Actions
    public void pressProceed() {
        wait.until(ExpectedConditions.elementToBeClickable(ProceedButton));
        driver.findElement(ProceedButton).click();

    }

    public void pressAddress() {
        wait.until(ExpectedConditions.presenceOfElementLocated(addressbutton));
        driver.findElement(addressbutton).click();
    }

    public void writename() {
        wait.until(ExpectedConditions.presenceOfElementLocated(Fullname));
        driver.findElement(Fullname).sendKeys("TestEngineer");
    }

    public void writephone() {
        wait.until(ExpectedConditions.presenceOfElementLocated(Phonenumber));
        driver.findElement(Phonenumber).sendKeys("1000132739");
    }

    public void writestreet() {
        wait.until(ExpectedConditions.presenceOfElementLocated(Addressline));
        driver.findElement(Addressline).sendKeys("TestingTheStreet");
    }

    public void writebuildno() {
        wait.until(ExpectedConditions.presenceOfElementLocated(BuildNo));
        driver.findElement(BuildNo).sendKeys("12");
    }

    public void writecity() {
        wait.until(ExpectedConditions.presenceOfElementLocated(Cityarea));
        driver.findElement(Cityarea).sendKeys("10th of Ramadan City");
    }

    public void writedistrict() {
        wait.until(ExpectedConditions.presenceOfElementLocated(district));
        driver.findElement(district).sendKeys("10th District");
    }

    public void Useaddressbutton() {
        wait.until(ExpectedConditions.presenceOfElementLocated(useaddress));
        driver.findElement(useaddress).click();
    }

    public void FillData(){
        writename();
        writephone();
        writestreet();
        writebuildno();
        writecity();
        writedistrict();
        Useaddressbutton();
    }

}