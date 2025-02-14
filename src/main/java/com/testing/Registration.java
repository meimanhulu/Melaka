package com.testing;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Registration {

    public static void main(String[] args) {
        System.out.println("Selenium 4");
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://dashboard.melaka.app/register");

        try {
            testRegistrationProcess(driver);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    public static void testRegistrationProcess(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        Random random = new Random();
        long min = 8139L; // Gunakan long untuk nilai yang besar
        long max = 81391199999L;
        long randomPhoneNumber = min + (long) (random.nextDouble() * (max - min + 1));
    
        try {
            // Menggunakan By.id
            WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-name")));
            nameField.sendKeys("Sahswa");
    
            // Menggunakan By.name
            WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("phone")));
            phoneField.sendKeys(String.valueOf(randomPhoneNumber));
    
            // Menggunakan By.cssSelector
            WebElement businessNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#business-name")));
            businessNameField.sendKeys("blue struck");
    
            // Menggunakan By.className
            WebElement businessTypeField = wait.until(ExpectedConditions.elementToBeClickable(By.className("business-type")));
            businessTypeField.click();
    
            // Menggunakan By.tagName
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("input")));
            emailField.sendKeys("marcus81@gmail.com");
    
            // Menggunakan By.cssSelector dengan atribut
            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='password']")));
            passwordField.sendKeys("Valid123#");
    
            // Menggunakan By.xpath relatif
            WebElement confirmPasswordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='confirmPassword']")));
            confirmPasswordField.sendKeys("Valid123#");
    
            // Menggunakan By.cssSelector
            WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='checkbox']")));
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
    
            // Menggunakan By.cssSelector
            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
            if (submitButton.isEnabled()) {
                submitButton.click();
                wait.until(ExpectedConditions.urlContains("dashboard"));
                if (driver.getCurrentUrl().contains("dashboard")) {
                    System.out.println("Registrasi Berhasil dilakukan.");
                } else {
                    System.out.println("Registrasi gagal, sistem tidak mengarahkan ke halaman yang sesuai.");
                }
            } else {
                System.out.println("Tombol Daftar tidak aktif karena input tidak valid.");
            }
    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}