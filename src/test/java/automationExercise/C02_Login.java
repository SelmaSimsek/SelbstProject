package automationExercise;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;

public class C02_Login {
    WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://automationexercise.com");
    }

    @Test
    public void login() throws InterruptedException {
        //Web sitesine gidilir
        //Sign up/Login butonuna tiklanır
        driver.findElement(By.xpath("//a[@href='/login']")).click();

        //Login to your account / Email Address kutusuna gecerli bir veri girilir
        //Login to your account / Password kutusuna gecerli bir veri girilir
        //Login butonuna tiklanir
        WebElement emailAddressBox = driver.findElement
                (By.xpath("(//*[@type='email'])[1]"));
        emailAddressBox.sendKeys("philbert.bradey@forkshape.com",
                Keys.TAB,"Julia123",Keys.TAB,Keys.ENTER);

        Thread.sleep(3000);
        driver.close();

    }
}
