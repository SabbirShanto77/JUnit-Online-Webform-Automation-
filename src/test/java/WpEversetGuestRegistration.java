import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WpEversetGuestRegistration {

    WebDriver driver;

    @BeforeAll
    public void bootup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void fillup() throws InterruptedException {
        driver.get("https://demo.wpeverest.com/user-registration/guest-registration-form/");

        driver.findElement(By.id("first_name")).sendKeys("Generic");
        driver.findElement(By.id("last_name")).sendKeys("Name");
        driver.findElement(By.id("user_email")).sendKeys("alphaalphaalphaalpha@gmail.com");

        driver.findElement(By.id("user_pass")).sendKeys("abcABC123#$%&");

        driver.findElement(By.id("radio_1665627729_Male")).click();

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("window.scrollBy(0,400)", "");

        Thread.sleep(1000);

        WebElement dateInput = driver.findElement(By.cssSelector("input[data-id='date_box_1665628538']"));
        js.executeScript("arguments[0].removeAttribute('readonly');", dateInput);

        dateInput.sendKeys("2000-01-01");


        driver.findElement(By.id("input_box_1665629217")).sendKeys("Bangladeshi");

        js.executeScript("window.scrollBy(0,500)", "");

        Thread.sleep(1000);

        List<WebElement> phoneNumber = driver.findElements(By.id("phone_1665627880"));
        phoneNumber.get(1).sendKeys("1234567890");

        Select select = new Select(driver.findElement(By.id("country_1665629257")));
        select.selectByContainsVisibleText("Bangladesh");

        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        Thread.sleep(2000);

        driver.findElement(By.id("privacy_policy_1665633140")).click();

        List<WebElement> submitButton = driver.findElements(By.cssSelector("[type=submit]"));
        submitButton.get(2).click();

        Thread.sleep(4000);

        String actualmsg = driver.findElement(By.xpath("//ul[contains(text(),\"User\")]")).getText();
        String expectedmsg = "User successfully";
        Assertions.assertTrue(actualmsg.contains(expectedmsg));
    }

    @AfterAll
    public void teardown() {
        driver.quit();
    }
}
