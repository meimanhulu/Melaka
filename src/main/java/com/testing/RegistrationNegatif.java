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
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(40)) // Set timeout to 30 seconds
            .pollingEvery(Duration.ofMillis(500)) // Poll every 500 milliseconds
            .ignoring(NoSuchElementException.class);

        boolean isFailure = false;

        try {
            WebElement nameField = fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-:R36l6:\"]")));
            nameField.sendKeys("Sama");
            WebElement errorMessage = fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div[2]/div[2]/div/form/div[1]/div[2]/p")));
            if (errorMessage.isDisplayed()) {
                isFailure = true;
                System.out.println("Error: Wajib diisi, min. 5 karakter.");
            }

            WebElement phoneField = fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-:R156l6:\"]")));
            phoneField.sendKeys("81345");
            errorMessage = fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div[2]/div[2]/div/form/div[2]/div/div[2]/p")));
            if (errorMessage.isDisplayed()) {
                isFailure = true;
                System.out.println("Error: Wajib diisi. Nomor telepon tidak boleh kurang dari 10 atau lebih dari 12 karakter.");
            }

            WebElement businessNameField = fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-:R76l6:\"]")));
            businessNameField.sendKeys("Lop");
            errorMessage = fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div[2]/div[2]/div/form/div[3]/div[2]/p")));
            if (errorMessage.isDisplayed()) {
                isFailure = true;
                System.out.println("Error: Wajib diisi, nama bisnis tidak boleh kurang dari 5 karakter.");
            }

            WebElement businessTypeField = fluentWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"brand-:Re96l6:\"]")));
            businessTypeField.click();

            WebElement emailField = fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-:Rb6l6:\"]")));
            emailField.sendKeys("markus1gmail.com");
            errorMessage = fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div[2]/div[2]/div/form/div[5]/div[2]/p")));
            if (errorMessage.isDisplayed()) {
                isFailure = true;
                System.out.println("Error: Harap isi dengan format yang benar.");
            }

            WebElement passwordField = fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-:Rd6l6:\"]")));
            passwordField.sendKeys("Ukko2");
            errorMessage = fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div[2]/div[2]/div/form/div[6]/div[2]/p")));
            if (errorMessage.isDisplayed()) {
                isFailure = true;
                System.out.println("Error: Min. 8 karakter, harus kombinasi dari huruf dan angka.");
            }

            WebElement confirmPasswordField = fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-:Rf6l6:\"]")));
            confirmPasswordField.sendKeys("Uki912");

            WebElement errorMessageConfirm = null;
            try {

                errorMessageConfirm = fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(text(), 'Belum sesuai dengan kata sandi')]")));
            } catch (TimeoutException e) {
                System.out.println("Error: Belum sesuai dengan kata sandi.");
            }

            if (errorMessageConfirm != null && errorMessageConfirm.isDisplayed()) {
                isFailure = true;
                System.out.println("Error: Belum sesuai dengan kata sandi.");
            }

            WebElement checkbox = fluentWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"register__checkbox__tnc\"]")));
            if (!checkbox.isSelected() && isFailure) {
                checkbox.click();
            }

            WebElement submitButton = fluentWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"__next\"]/div/div[2]/div[2]/div/form/button")));
            if (submitButton.isEnabled()) {
                submitButton.click();
                fluentWait.until(ExpectedConditions.urlContains("dashboard"));
                if (driver.getCurrentUrl().contains("dashboard")) {
                    System.out.println("Registrasi Berhasil.");
                } else {
                    System.out.println("Registrasi Gagal.");
                }
            } else {
                System.out.println("Tombol Submit tidak aktif.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}