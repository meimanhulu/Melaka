package com.testing;

import java.time.Duration;
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get("https://dashboard.melaka.app/register");

        try {
            testValidInput(driver);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    public static void testValidInput(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-:R36l6:\"]")));
            nameField.sendKeys("Cash"); 
            WebElement nameErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div[2]/div[2]/div/form/div[1]/div[2]/p")));
            if (nameErrorMessage.isDisplayed()) {
                System.out.println("Error in Nama: " + nameErrorMessage.getText());
            }

            WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-:R156l6:\"]")));
            phoneField.sendKeys("8192121");
            WebElement phoneErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div[2]/div[2]/div/form/div[2]/div/div[2]/p")));
            if (phoneErrorMessage.isDisplayed()) {
                System.out.println("Error in Phone: " + phoneErrorMessage.getText());
            }

            WebElement businessNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-:R76l6:\"]")));
            businessNameField.clear(); // Jika nama Bisnis Kosong
            WebElement businessNameErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div[2]/div[2]/div/form/div[3]/div[2]/p")));
            if (businessNameErrorMessage.isDisplayed()) {
                System.out.println("Error in Business Name: " + businessNameErrorMessage.getText()); //maka hasilnya ditampilkan disini
            } 

            WebElement businessTypeField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"retail_store-:Ra96l6:\"]")));
            businessTypeField.click();

            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-:Rb6l6:\"]")));
            emailField.sendKeys("kuculgmail.com");
            WebElement emailErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div[2]/div[2]/div/form/div[5]/div[2]/p")));
            if (emailErrorMessage.isDisplayed()) {
                System.out.println("Error in Email: " + emailErrorMessage.getText());
            }

            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-:Rd6l6:\"]")));
            passwordField.sendKeys("kami1");
            WebElement passwordErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div[2]/div[2]/div/form/div[6]/div[2]/p")));
            if (passwordErrorMessage.isDisplayed()) {
                System.out.println("Error in Password: " + passwordErrorMessage.getText());
            }

            WebElement confirmPasswordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-:Rf6l6:\"]")));
            confirmPasswordField.sendKeys("kami23");
            WebElement confirmPasswordErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div[2]/div[2]/div/form/div[7]/div[2]/p")));
            if (confirmPasswordErrorMessage.isDisplayed()) {
                System.out.println("Error in Confirm Password: " + confirmPasswordErrorMessage.getText());
            }

            WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"register__checkbox__tnc\"]")));
            checkbox.click();

            WebElement submitButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div[2]/div[2]/div/form/button")));
            if (submitButton.isEnabled()) {
                submitButton.click();
                wait.until(ExpectedConditions.urlContains("dashboard"));
                if (driver.getCurrentUrl().contains("dashboard")) {
                    System.out.println("Registrasi Berhasil Dilakukan");
                } else {
                    System.out.println("Registrasi Gagal Dilakukan");
                }
            } else {
                System.out.println("Submit button Disabled due to invalid input.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}