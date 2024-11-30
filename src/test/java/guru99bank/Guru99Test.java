package guru99bank;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Guru99Test {
    WebDriver driver;
    String baseURL = "https://www.demo.guru99.com/V4/index.php";
    String customerId;
    String accountId;

    // Set up the ChromeDriver and navigate to the login page before starting the tests
    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(baseURL);

        // Enter login credentials and submit the login form
        driver.findElement(By.name("uid")).sendKeys("mngr596973");
        driver.findElement(By.name("password")).sendKeys("tuqYbAd");
        driver.findElement(By.name("btnLogin")).click();
    }

    // Test to create a new customer in the system
    @Test
    public void createNewCustomerTest() throws InterruptedException {
        driver.findElement(By.linkText("New Customer")).click(); // Navigate to New Customer page

        // Fill out the form with customer information
        driver.findElement(By.name("name")).sendKeys("test name");
        driver.findElement(By.name("rad1")).click(); // Select gender
        driver.findElement(By.name("dob")).sendKeys("01-01-1990");
        driver.findElement(By.name("addr")).sendKeys("123 Main St");
        driver.findElement(By.name("city")).sendKeys("Anytown");
        driver.findElement(By.name("state")).sendKeys("CA");
        driver.findElement(By.name("pinno")).sendKeys("123456");
        driver.findElement(By.name("telephoneno")).sendKeys("1234567890");
        driver.findElement(By.name("emailid")).sendKeys("tester7899@example.com");
        driver.findElement(By.name("password")).sendKeys("password123");
        driver.findElement(By.name("sub")).click(); // Submit the form

        // Wait for page to load or confirmation message
        Thread.sleep(3000);

        // Check if an alert with the Customer ID is displayed, otherwise retrieve it from the confirmation page
        try {
            String alertText = driver.switchTo().alert().getText();
            driver.switchTo().alert().accept(); // Accept alert
            String[] parts = alertText.split(" ");
            this.customerId = parts[parts.length - 1]; // Extract Customer ID from alert
        } catch (NoAlertPresentException e) {
            this.customerId = driver.findElement(By.xpath("//*[@id=\"customer\"]/tbody/tr[4]/td[2]")).getText();
        }

        System.out.println("Created Customer ID: " + this.customerId);
    }

    // Test to edit the existing customer information
    @Test(priority = 2, dependsOnMethods = "createNewCustomerTest")
    public void editCustomerTest() {
        driver.findElement(By.linkText("Edit Customer")).click(); // Navigate to Edit Customer page

        driver.findElement(By.name("cusid")).sendKeys(customerId); // Enter Customer ID
        driver.findElement(By.name("AccSubmit")).click(); // Submit ID

        // Update customer information 
        WebElement addressField = driver.findElement(By.name("addr"));
        addressField.clear();
        addressField.sendKeys("456 New Address");

        driver.findElement(By.name("city")).clear();
        driver.findElement(By.name("city")).sendKeys("New City");

        driver.findElement(By.name("state")).clear();
        driver.findElement(By.name("state")).sendKeys("New State");

        driver.findElement(By.name("pinno")).clear();
        driver.findElement(By.name("pinno")).sendKeys("654321");

        driver.findElement(By.name("telephoneno")).clear();
        driver.findElement(By.name("telephoneno")).sendKeys("0987654321");

        driver.findElement(By.name("sub")).click(); // Submit updated information
    }

    // Test to create a new account for the created customer
    @Test(priority = 3, dependsOnMethods = {"createNewCustomerTest"})
    public void createNewAccountTest() throws InterruptedException {
        handleUnexpectedAlert(); // Handle any unexpected alerts before starting

        driver.findElement(By.linkText("New Account")).click(); // Navigate to New Account page

        // Fill in account details
        driver.findElement(By.name("cusid")).sendKeys(customerId); // Use dynamic Customer ID
        driver.findElement(By.name("selaccount")).sendKeys("Savings"); // Select account type
        driver.findElement(By.name("inideposit")).sendKeys("5000"); // Enter initial deposit

        driver.findElement(By.name("button2")).click(); // Submit the form

        // Retrieve and store the generated Account ID
        this.accountId = driver.findElement(By.xpath("//td[text()='Account ID']/following-sibling::td")).getText();
        System.out.println("Account created for Customer ID: " + customerId + ", Account ID: " + accountId);
    }

    // Test to withdraw an amount from the created account
    @Test(priority = 4, dependsOnMethods = "createNewAccountTest")
    public void withdrawalTest() {
        driver.findElement(By.linkText("Withdrawal")).click(); // Navigate to Withdrawal page

        // Enter withdrawal details
        driver.findElement(By.name("accountno")).sendKeys(accountId);
        driver.findElement(By.name("ammount")).sendKeys("1000");
        driver.findElement(By.name("desc")).sendKeys("Test Withdrawal");

        driver.findElement(By.name("AccSubmit")).click(); // Submit the withdrawal form

        // Handle any alert if it appears after withdrawal
        handleUnexpectedAlert();
    }

    // Helper method to handle unexpected alerts
    private void handleUnexpectedAlert() {
        try {
            Alert alert = driver.switchTo().alert();
            System.out.println("Unexpected alert: " + alert.getText());
            alert.accept(); // Accept the alert if present
        } catch (NoAlertPresentException e) {
            // No alert was present
        }
    }

    // Tear down the WebDriver after tests are complete
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Close browser and end session
        }
    }
}
