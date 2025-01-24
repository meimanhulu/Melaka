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
            // Mengisi kolom dengan data yang salah untuk memicu error
            WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-:R36l6:\"]")));
            nameField.sendKeys("fssa"); // Menggunakan input yang salah untuk Nama

            WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-:R156l6:\"]")));
            phoneField.sendKeys("+62 7381"); // Format telepon yang salah

            WebElement businessNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-:R76l6:\"]")));
            businessNameField.sendKeys(""); // Tidak mengisi Nama Bisnis

            WebElement radioButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"retail_store-:Ra96l6:\"]")));
            radioButton.click();

            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-:Rb6l6:\"]")));
            emailField.sendKeys("ruhykgmail.com"); // Email dengan format salah

            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-:Rd6l6:\"]")));
            passwordField.sendKeys("Hulkdola"); // Password tidak memenuhi ketentuan

            WebElement confirmPasswordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-:Rf6l6:\"]")));
            confirmPasswordField.sendKeys("Hulkdola");

            WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"register__checkbox__tnc\"]")));
            checkbox.click();

            // Menunggu tombol Submit
            WebElement submitButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div[2]/div[2]/div/form/button")));

            // Memeriksa apakah tombol Submit dinonaktifkan
            if (!submitButton.isEnabled()) {
                System.out.println("Submit button is disabled due to invalid input.");
            } else {
                // Jika tombol submit bisa diklik, klik tombol submit
                submitButton.click();
                wait.until(ExpectedConditions.urlContains("dashboard"));

                if (driver.getCurrentUrl().contains("dashboard")) {
                    System.out.println("Valid Input Test Passed");
                } else {
                    System.out.println("Valid Input Test Failed");
                }
            }
            
            // Verifikasi apakah pesan kesalahan muncul di setiap kolom yang salah
            WebElement nameErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div[2]/div[2]/div/form/div[1]/div[2]/p")));
            System.out.println("Name Error: " + nameErrorMessage.getText());

            WebElement phoneErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div[2]/div[2]/div/form/div[2]/div/div[2]/p")));
            System.out.println("Phone Error: " + phoneErrorMessage.getText());

            WebElement businessNameErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div[2]/div[2]/div/form/div[3]/div[2]/p")));
            System.out.println("Business Name Error: " + businessNameErrorMessage.getText());

            WebElement emailErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div[2]/div[2]/div/form/div[5]/div[2]/p")));
            System.out.println("Email Error: " + emailErrorMessage.getText());

            WebElement passwordErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div[2]/div[2]/div/form/div[6]/div[2]/p")));
            System.out.println("Password Error: " + passwordErrorMessage.getText());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}