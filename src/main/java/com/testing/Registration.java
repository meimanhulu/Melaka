package com.testing;

import java.time.Duration;
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
        try {
            // Input valid data
            WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-:R36l6:\"]")));
            nameField.sendKeys("Dimsan");

            WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-:R156l6:\"]")));
            phoneField.sendKeys("81583143682");

            WebElement businessNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-:R76l6:\"]")));
            businessNameField.sendKeys("Toko Karuan");

            WebElement businessTypeField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"online_seller-:Ri96l6:\"]")));
            businessTypeField.click();

            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-:Rb6l6:\"]")));
            emailField.sendKeys("markus12@gmail.com");

            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-:Rd6l6:\"]")));
            passwordField.sendKeys("Valid123#");

            WebElement confirmPasswordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-:Rf6l6:\"]")));
            confirmPasswordField.sendKeys("Valid123#");

            WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"register__checkbox__tnc\"]")));
            if (!checkbox.isSelected()) {
                checkbox.click();
                System.out.println("Checkbox Konfirmasi dicentang.");
            }

            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"__next\"]/div/div[2]/div[2]/div/form/button")));
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