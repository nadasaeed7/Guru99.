package guru99bank;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class LoginPage {
    WebDriver driver;

    // Constructor to initialize WebDriver instance
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators for login elements
    By userIdField = By.name("uid");
    By passwordField = By.name("password");
    By loginButton = By.name("btnLogin");

    // Method to perform login action
    public void login(String userId, String password) {
        driver.findElement(userIdField).sendKeys(userId); // Enter user ID
        driver.findElement(passwordField).sendKeys(password); // Enter password
        driver.findElement(loginButton).click(); // Click on login button
    }
}
