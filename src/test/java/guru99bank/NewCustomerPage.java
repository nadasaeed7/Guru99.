package guru99bank;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewCustomerPage {
    WebDriver driver;

    // Locators for new customer form fields and submit button
    By customerNameField = By.name("name");
    By genderMaleRadio = By.xpath("//input[@value='m']");
    By dateOfBirthField = By.name("dob");
    By addressField = By.name("addr");
    By cityField = By.name("city");
    By stateField = By.name("state");
    By pinField = By.name("pinno");
    By mobileNumberField = By.name("telephoneno");
    By emailField = By.name("emailid");
    By passwordField = By.name("password");
    By submitButton = By.name("sub");

    // Locator for capturing the Customer ID after creation
    By customerIdText = By.xpath("//td[text()='Customer ID']/following-sibling::td");

    // Constructor to initialize WebDriver instance
    public NewCustomerPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to fill in new customer details and submit the form
    public void createNewCustomer(String name, String dob, String address, String city, String state, String pin, String mobile, String email, String password) {
        driver.findElement(customerNameField).sendKeys(name); // Enter customer name
        driver.findElement(genderMaleRadio).click(); // Select gender as male
        driver.findElement(dateOfBirthField).sendKeys(dob); // Enter date of birth
        driver.findElement(addressField).sendKeys(address); // Enter address
        driver.findElement(cityField).sendKeys(city); // Enter city
        driver.findElement(stateField).sendKeys(state); // Enter state
        driver.findElement(pinField).sendKeys(pin); // Enter PIN
        driver.findElement(mobileNumberField).sendKeys(mobile); // Enter mobile number
        driver.findElement(emailField).sendKeys(email); // Enter email
        driver.findElement(passwordField).sendKeys(password); // Enter password
        driver.findElement(submitButton).click(); // Submit the form to create customer
    }

    // Retrieve the Customer ID displayed after customer creation
    public String getCustomerId() {
        return driver.findElement(customerIdText).getText();
    }
}
