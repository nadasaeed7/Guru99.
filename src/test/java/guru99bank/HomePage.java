package guru99bank;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class HomePage {
    WebDriver driver;

    // Constructor to initialize WebDriver instance
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators for navigation elements
    By newCustomerLink = By.linkText("New Customer");
    By editCustomerLink = By.linkText("Edit Customer");
    By newAccountLink = By.linkText("New Account");
    By withdrawalLink = By.linkText("Withdrawal");
    By logoutLink = By.linkText("Log out");

    // Navigate to 'New Customer' page
    public void goToNewCustomer() {
        driver.findElement(newCustomerLink).click();
    }

    // Navigate to 'Edit Customer' page
    public void goToEditCustomer() {
        driver.findElement(editCustomerLink).click();
    }

    // Navigate to 'New Account' page
    public void goToNewAccount() {
        driver.findElement(newAccountLink).click();
    }

    // Navigate to 'Withdrawal' page
    public void goToWithdrawal() {
        driver.findElement(withdrawalLink).click();
    }

    // Perform logout and accept alert confirmation
    public void logout() {
        driver.findElement(logoutLink).click();
        driver.switchTo().alert().accept(); // Accept logout alert
    }
}
