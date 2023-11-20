import com.github.javafaker.Faker;
import com.makersacademy.acebook.Application;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class SignUpTest {

    WebDriver driver;
    Faker faker;

    @Before
    public void setup() throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", "http://localhost:4444/wd/hub");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        faker = new Faker();
    }
    // @Before
    // public void setup() throws MalformedURLException {
    //     File chromedriverFile = new File("/usr/local/bin/chromedriver");
    //     if (chromedriverFile.exists()) {
    //         System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
    //         driver = new ChromeDriver();
    //     } else {
    //         System.setProperty("webdriver.chrome.driver", "http://localhost:4444/wd/hub");
    //         DesiredCapabilities capabilities = DesiredCapabilities.chrome();
    //         capabilities.setCapability("browserVersion", "latest");
    //         capabilities.setCapability("platform", Platform.ANY);
    //         driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
    //     }
    //     faker = new Faker();
    // }
    
    @After
    public void tearDown() {
        driver.close();
    }

    @Test
    public void successfulSignUpRedirectsToSignIn() {
        driver.get("http://localhost:8080/users/new");
        driver.findElement(By.id("username")).sendKeys(faker.name().firstName());
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.id("submit")).click();
        String title = driver.getTitle();
        Assert.assertEquals("Please sign in", title);
    }
}
