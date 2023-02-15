package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/Users/kapil.rajak/Downloads/chromedriver_mac_arm64/chromedriver");
        WebDriver driver = new ChromeDriver();
        System.out.println("Login Success:"+loginSuccess(driver, "tomsmith", "SuperSecretPassword!"));
        driver.quit();
    }

    private static boolean loginSuccess(WebDriver driver, String id, String pass)  {
        driver.get("http://localhost:7080/login");

        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys(id);
        WebElement passwordField = driver.findElement(By.id(pass));
        passwordField.sendKeys("SuperSecretPassword!");

        WebElement loginButton = driver.findElement(By.xpath("//button[contains(.,'Login')]"));
        loginButton.click();

        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        WebElement successMessage = driver.findElement(By.xpath("//div[contains(.,'Welcome to the Secure Area')]"));
        String successMsgs = successMessage.getText();
        return "Welcome to the Secure Area. When you are done click logout below.".equalsIgnoreCase(successMsgs);
    }
}