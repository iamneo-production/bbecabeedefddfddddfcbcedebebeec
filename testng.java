import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;

public class SnapdealLoginTest {

    WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "path_to_chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void snapdealLoginTest() {
        driver.get("http://www.snapdeal.com");
        
        // Move to Sign In Button
        WebElement signInButton = driver.findElement(By.xpath("//div[@class='accountInner']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(signInButton).build().perform();

        // Click on Sign In
        WebElement signInLink = driver.findElement(By.linkText("login"));
        signInLink.click();

        // Switch to the login frame
        driver.switchTo().frame("loginIframe");

        // Enter a valid Email Id and click continue
        WebElement emailInput = driver.findElement(By.id("userName"));
        emailInput.sendKeys("your_email@example.com");
        WebElement continueButton = driver.findElement(By.id("checkUser"));
        continueButton.click();

        // Enter the valid password and click LOGIN
        WebElement passwordInput = driver.findElement(By.id("j_password_login_uc"));
        passwordInput.sendKeys("your_password");
        WebElement loginButton = driver.findElement(By.id("submitLoginUC"));
        loginButton.click();

        // Verify that the user is logged in successfully
        WebElement loggedInUserName = driver.findElement(By.xpath("//span[@class='accountUserName col-xs-12 reset-padding']"));
        String userName = loggedInUserName.getText();
        System.out.println("Logged in user: " + userName);
        // You can add additional assertions to check if the login was successful.
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
