package guru99bank;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditCustomerPage {
    WebDriver driver;

    // Locator for customer ID input field and submit button
    By customerIdField = By.name("cusid");
    By submitButton = By.name("AccSubmit");

    // Constructor to initialize WebDriver instance
    public EditCustomerPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to enter customer ID and submit for editing
    public void editCustomer(String customerId) {
        driver.findElement(customerIdField).sendKeys(customerId); // Enter customer ID
        driver.findElement(submitButton).click(); // Click submit button to proceed
    }
}
