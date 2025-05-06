import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DigitalUniteWebForm {
    WebDriver driver;

    @BeforeAll
    public void bootup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }
    @Test
    public void formFillUp() throws InterruptedException {
        driver.get("https://www.digitalunite.com/practice-webform-learners");

        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
        Thread.sleep(2000);

        List<WebElement> className =  driver.findElements(By.className("form-control"));
        className.get(0).sendKeys("Generic Name");
        className.get(1).sendKeys("12345678901");
        className.get(2).sendKeys("04051998");
        className.get(3).sendKeys("xyz@gmail.com");
        className.get(4).sendKeys("Nothing to say.\nI don't like to talk.");

        Thread.sleep(1000);
        //Scroll Down

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)", "");
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        Thread.sleep(1000);

        //File Upload
//        File file = new File("Image_for_DigitalUniteWebForm.png");
//        driver.findElement(By.className("js-form-file.form-file")).click();
//        driver.findElement(By.className("js-form-file.form-file")).sendKeys(file.getAbsolutePath());


        driver.findElement(By.id("edit-age")).click();
        driver.findElement(By.id("edit-submit")).click();

        Thread.sleep(2000);
        String actualMsg = driver.findElement(By.cssSelector("h1")).getText();
        String expectedMsg = "Thank you for your";

        Assertions.assertTrue(actualMsg.contains(expectedMsg));

    }
    @AfterAll
    public void shutdown(){
        driver.quit();
    }
}
