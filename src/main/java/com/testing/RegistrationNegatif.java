package com.testing;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.NoSuchElementException;

public class RegistrationNegatif {

    public static void main(String[] args) {
        System.out.println("Selenium 4");
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // driver.manage().window().maximize();
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
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(30)) 
            .pollingEvery(Duration.ofMillis(500))
            .ignoring(NoSuchElementException.class);

        boolean isFailure = false;

        try {
            WebElement nameField = fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-:R36l6:\"]")));
            nameField.sendKeys("Aji");
            WebElement errorMessage = fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div[2]/div[2]/div/form/div[1]/div[2]/p")));
            if (errorMessage.isDisplayed()) {
                isFailure = true;
                System.out.println("Error: Wajib diisi, min. 5 karakter.");
            }

            WebElement phoneField = fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-:R156l6:\"]")));
            phoneField.sendKeys("84170");
            errorMessage = fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div[2]/div[2]/div/form/div[2]/div/div[2]/p")));
            if (errorMessage.isDisplayed()) {
                isFailure = true;
                System.out.println("Error: Wajib diisi. Nomor telepon tidak boleh kurang dari 10 atau lebih dari 12 karakter.");
            }

            WebElement businessNameField = fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-:R76l6:\"]")));
            businessNameField.sendKeys("Spy");
            errorMessage = fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div[2]/div[2]/div/form/div[3]/div[2]/p")));
            if (errorMessage.isDisplayed()) {
                isFailure = true;
                System.out.println("Error: Wajib diisi, nama bisnis tidak boleh kurang dari 5 karakter.");
            }

            WebElement businessTypeField = fluentWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"online_seller-:Ri96l6:\"]")));
            businessTypeField.click();

            WebElement emailField = fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-:Rb6l6:\"]")));
            emailField.sendKeys("markes62gmail.com");
            errorMessage = fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div[2]/div[2]/div/form/div[5]/div[2]/p")));
            if (errorMessage.isDisplayed()) {
                isFailure = true;
                System.out.println("Error: Harap isi dengan format yang benar.");
            }

            WebElement passwordField = fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-:Rd6l6:\"]")));
            passwordField.sendKeys("Uk812");
            errorMessage = fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div[2]/div[2]/div/form/div[6]/div[2]/p")));
            if (errorMessage.isDisplayed()) {
                isFailure = true;
                System.out.println("Error: Min. 8 karakter, harus kombinasi dari huruf dan angka.");
            }

            WebElement confirmPasswordField = fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[2]/div[2]/div/form/div[7]/div[1]/input")));
            confirmPasswordField.sendKeys("Ukikwa");

            WebElement errorMessageConfirm = null;
            try {

                errorMessageConfirm = fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div[2]/div[2]/div/form/div[7]/div[2]/p")));
            } catch (TimeoutException e) {
                System.out.println("Error message for confirm password not found or not visible.");
            }

            if (errorMessageConfirm != null && errorMessageConfirm.isDisplayed()) {
                isFailure = true;
                System.out.println("Error: Belum sesuai dengan kata sandi.");
            }

            WebElement checkbox = fluentWait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[2]/div[2]/div/form/div[8]/div/input")));
            if (!checkbox.isSelected() && isFailure) {
                checkbox.click();
            }

            WebElement submitButton = fluentWait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[2]/div[2]/div/form/button")));
            if (submitButton.isEnabled()) {
                submitButton.click();
                fluentWait.until(ExpectedConditions.urlContains("dashboard"));
                if (driver.getCurrentUrl().contains("dashboard")) {
                    System.out.println("Registrasi Gagal.");
                } else {
                    System.out.println("Registrasi Berhasil.");
                }
            } else {
                System.out.println("Tombol Submit tidak aktif.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
