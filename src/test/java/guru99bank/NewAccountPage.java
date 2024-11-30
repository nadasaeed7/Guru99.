package guru99bank;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewAccountPage {
    WebDriver driver;

    // Locators for new account form fields and submit button
    By customerIdField = By.name("cusid");
    By accountTypeDropdown = By.name("selaccount");
    By initialDepositField = By.name("inideposit");
    By submitButton = By.name("button2");

    // Locator for capturing the Account ID after account creation
    By accountIdText = By.xpath("//td[text()='Account ID']/following-sibling::td");

    // Constructor to initialize WebDriver instance
    public NewAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to fill in new account details and submit the form
    public void createNewAccount(String customerId, String accountType, String initialDeposit) {
        driver.findElement(customerIdField).sendKeys(customerId); // Enter customer ID
        driver.findElement(accountTypeDropdown).sendKeys(accountType); // Select account type
        driver.findElement(initialDepositField).sendKeys(initialDeposit); // Enter initial deposit
        driver.findElement(submitButton).click(); // Submit the form to create account
    }

    // Retrieve the Account ID displayed after account creation
    public String getAccountId() {
        return driver.findElement(accountIdText).getText();
    }
}
