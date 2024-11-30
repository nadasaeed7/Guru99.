package guru99bank;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WithdrawalPage {
    WebDriver driver;

    // Locators for withdrawal form fields and submit button
    By accountIdField = By.name("accountno");
    By amountField = By.name("ammount");
    By descriptionField = By.name("desc");
    By submitButton = By.name("AccSubmit");

    // Constructor to initialize WebDriver instance
    public WithdrawalPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to fill in withdrawal details and submit the form
    public void withdrawAmount(String accountId, String amount, String description) {
        driver.findElement(accountIdField).sendKeys(accountId); // Enter account ID
        driver.findElement(amountField).sendKeys(amount); // Enter withdrawal amount
        driver.findElement(descriptionField).sendKeys(description); // Enter transaction description
        driver.findElement(submitButton).click(); // Submit the form to process withdrawal
    }
}
