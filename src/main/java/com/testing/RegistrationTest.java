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
            nameField.sendKeys("Saskety"); 
            WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-:R156l6:\"]")));
            phoneField.sendKeys("81481323412"); 
            WebElement businessNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-:R76l6:\"]")));
            String businessName = "Indoapril";  // Simulating input for business name
            
            if (businessName.isEmpty()) {
                WebElement businessNameErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div[2]/div[2]/div/form/div[3]/div[2]/p")));
                System.out.println("Error: " + businessNameErrorMessage.getText());
                return;  // Stop execution if business name is empty
            }
            businessNameField.sendKeys(businessName); 

            WebElement radioButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"retail_store-:Ra96l6:\"]")));
            radioButton.click();
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-:Rb6l6:\"]")));
            emailField.sendKeys("markushulu@gmail.com");
            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-:Rd6l6:\"]")));
            passwordField.sendKeys("Sutarno123"); 
            WebElement confirmPasswordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-:Rf6l6:\"]")));
            confirmPasswordField.sendKeys("Sutarno123");
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
                System.out.println("Submit button is disabled due to invalid input.");
            }

            boolean isErrorPresent = false;
            try {
                WebElement nameErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div[2]/div[2]/div/form/div[1]/div[2]/p")));
                if (nameErrorMessage.isDisplayed()) {
                    isErrorPresent = true;
                }
            } catch (Exception e) { 
            }

            try {
                WebElement phoneErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div[2]/div[2]/div/form/div[2]/div/div[2]/p")));
                if (phoneErrorMessage.isDisplayed()) {
                    isErrorPresent = true;
                }
            } catch (Exception e) { 
            }

            try {
                WebElement businessNameErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div[2]/div[2]/div/form/div[3]/div[2]/p")));
                if (businessNameErrorMessage.isDisplayed()) {
                    isErrorPresent = true;
                    System.out.println("Error in Business Name: " + businessNameErrorMessage.getText());
                }
            } catch (Exception e) { 
            }

            try {
                WebElement emailErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div[2]/div[2]/div/form/div[5]/div[2]/p")));
                if (emailErrorMessage.isDisplayed()) {
                    isErrorPresent = true;
                }
            } catch (Exception e) { 
            }

            try {
                WebElement passwordErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div[2]/div[2]/div/form/div[6]/div[2]/p")));
                if (passwordErrorMessage.isDisplayed()) {
                    isErrorPresent = true;
                }
            } catch (Exception e) { 
            }

            if (!isErrorPresent) {
                System.out.println("Registrasi Berhasil Dilakukan");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
