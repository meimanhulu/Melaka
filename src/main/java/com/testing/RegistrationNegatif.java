package com.testing;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        boolean isFailure = false;
        
        try {
            WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-:R36l6:\"]")));
            nameField.sendKeys("YUA");
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div[2]/div[2]/div/form/div[1]/div[2]/p")));
            if (errorMessage == null) {
                isFailure = true;
                System.out.println("Error: Wajib diisi, min. 5 karakter.");
            }

            WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-:R156l6:\"]")));
            phoneField.sendKeys("08213");
            errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div[2]/div[2]/div/form/div[2]/div/div[2]/p")));
            if (errorMessage == null) {
                isFailure = true;
                System.out.println("Error: Wajib diisi. Nomor telepon tidak boleh kurang dari 10 atau lebih dari 12 karakter.");
            }

            WebElement businessNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-:R76l6:\"]")));
            businessNameField.sendKeys("SUT");
            errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div[2]/div[2]/div/form/div[3]/div[2]/p")));
            if (errorMessage == null) {
                isFailure = true;
                System.out.println("Error: Wajib diisi, nama bisnis tidak boleh kurang dari 5 karakter.");
            }

            WebElement businessTypeField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"brand-:Re96l6:\"]")));
            businessTypeField.click();

            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-:Rb6l6:\"]")));
            emailField.sendKeys("syukurgmail.com");
            errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div[2]/div[2]/div/form/div[5]/div[2]/p")));
            if (errorMessage == null) {
                isFailure = true;
                System.out.println("Error: Harap isi dengan format yang benar.");
            }

            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-:Rd6l6:\"]")));
            passwordField.sendKeys("Uk812");
            errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div[2]/div[2]/div/form/div[6]/div[2]/p")));
            if (errorMessage == null) {
                isFailure = true;
                System.out.println("Error: Min. 8 karakter, harus kombinasi dari huruf dan angka.");
            }

            WebElement confirmPasswordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-:Rf6l6:\"]")));
            confirmPasswordField.sendKeys("Uki123");
            errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div[2]/div[2]/div/form/div[7]/div[2]/p")));
            if (errorMessage == null) {
                isFailure = true;
                System.out.println("Error: Belum sesuai dengan kata sandi.");
            }

            WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"register__checkbox__tnc\"]")));
            if (!checkbox.isSelected()) {
                checkbox.click();
            }

            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"__next\"]/div/div[2]/div[2]/div/form/button")));
            if (submitButton.isEnabled()) {
                submitButton.click();
                wait.until(ExpectedConditions.urlContains("dashboard"));
                if (driver.getCurrentUrl().contains("dashboard")) {
                    System.out.println("Registrasi Gagal");
                } else {
                    System.out.println("Registrasi Berhasil.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}