package com.testing;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationTest {

    public static void main(String[] args) {
        System.out.println("Selenium 4");
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        driver.get("https://dashboard.melaka.app/register");

        testValidInput(driver);
        testRadioButtonSelection(driver, "distributor");
        testRadioButtonSelection(driver, "tokoRetail");
        testRadioButtonSelection(driver, "brand");
        testRadioButtonSelection(driver, "penjualOnline");
        driver.quit();
    }


    public static void testValidInput(WebDriver driver) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Nama']")));
        nameField.sendKeys("Ryland");

      
        WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Nomor Telepon']")));
        phoneField.sendKeys("82189678292");
        WebElement businessNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Nama Bisnis']")));
        businessNameField.sendKeys("Toko Kue");

  
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Email']")));
        emailField.sendKeys("meiman@gmail.com");
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Kata Sandi']")));
        passwordField.sendKeys("Suriname132");

       
        WebElement confirmPasswordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Konfirmasi Kata Sandi']")));
        confirmPasswordField.sendKeys("Suriname132");
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='checkbox']")));
        checkbox.click();
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Daftar']")));
        submitButton.click();

     
        wait.until(ExpectedConditions.urlContains("dashboard"));

        if (driver.getCurrentUrl().contains("dashboard")) {
            System.out.println("Valid Input Test Passed");
        } else {
            System.out.println("Valid Input Test Failed");
        }
    }

    
    public static void testRadioButtonSelection(WebDriver driver, String buttonId) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.id(buttonId)));
        button.click();
        if (button.isSelected()) {
            System.out.println(buttonId + " is selected.");
        } else {
            System.out.println(buttonId + " is NOT selected.");
        }
    }
}