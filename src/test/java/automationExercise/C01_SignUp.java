package automationExercise;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class C01_SignUp {
    WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://automationexercise.com");
    }

    @Test
    public void signup() {
        //https://automationexercise.com Web sitesine gidilir
        //Sign up/Login butonuna tiklanır
        driver.findElement(By.xpath("//a[@href='/login']")).click();

       // New User Signup / Name kutusuna bir veri girilir."yordin"
       // New User Signup / Email Address kutusuna veri girilir. "yordin.eduin@forkshape.com"
       // Signup butonuna tiklanir
        WebElement nameBox = driver.findElement
                (By.xpath("//input[@type='text']"));
        nameBox.sendKeys("Julia",
                Keys.TAB,"philbert.bradey@forkshape.com",
                Keys.TAB,Keys.ENTER);

        // Mrs. kutusuna tiklanir
        driver.findElement(By.xpath("//*[@id='id_gender2']")).click();

        // Password kutusuna bir veri girilir.(yordin123)
        WebElement passwordBox = driver.findElement(By.xpath("//*[@id='password']"));
        passwordBox.sendKeys("Julia123");

        // Day Dropdowdan bir veri secilir
        // Month Dropdowdan bir veri secilir
        // Year Dropdowdan bir veri secilir
        WebElement days = driver.findElement(By.xpath("//*[@id='days']"));
        WebElement months = driver.findElement(By.xpath("//*[@id='months']"));
        WebElement years = driver.findElement(By.xpath("//*[@id='years']"));

        Select selectDays =new Select(days);
        Select selectMonths =new Select(months);
        Select selectYears =new Select(years);

        selectDays.selectByVisibleText("4");
        selectMonths.selectByVisibleText("June");
        selectYears.selectByVisibleText("1982");

        // First name kutusuna bir veri girilir
        WebElement firstNameBox = driver.findElement(By.xpath("//*[@id='first_name']"));
        firstNameBox.sendKeys("Julia",
                // Last name kutusuna bir veri girilir."eduin"
                Keys.TAB,"Roberts",
                // Company kutusuna bir veri girilir."firmaxxx"
                Keys.TAB,"firmaxxx",
                // Address kutusuna bir veri girilir."mary street"
                Keys.TAB, "mary street",
                // Address 2 kutusuna bir veri girilir."george street"
                Keys.TAB,"george street");
        // Country dropdowndan  bir veri secilir.United States
        WebElement countryBox = driver.findElement
                (By.xpath("//*[@id='country']"));
        Select selectCountry = new Select(countryBox);
        selectCountry.selectByVisibleText("United States");

        // State kutusuna bir veri girilir.Dallas
        WebElement statesBox = driver.findElement
                (By.xpath("//*[@id='state']"));
        statesBox.sendKeys("Dallas",
                // City kutusuna bir veri girilir.Texas
                Keys.TAB,"Texas",
                // Zipcode kutusuna bir veri girilir.12500
                Keys.TAB, "12500",
                // Mobil Number kutusuna bir veri girilir.1 805 485 45412
                Keys.TAB, "1 805 485 45412",
                // Create Account butonuna tiklanır
                Keys.TAB,Keys.ENTER);

        //Sign in isleminin gerceklestigi dogrulanir
        Assert.assertTrue(driver.getPageSource().contains("Account Created"));
    }

    @After
    public void tearDown() throws Exception {
        Thread.sleep(3000);
        driver.close();
    }
}
