import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.swing.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class FirstTest {
    WebDriver driver;
    Actions Actions;
    String email, password, confirmpassword, phoneNumber, FirstName, LastName;
    String newEmail, Adress1, Adress2, City, State, Zip, newPhone, Country;
    String nameCheck,lastNameCheck,emailCheck,address1Check,address2Check,cityCheck,stateCheck,zipCheck,countryCheck,phoneCheck;

    @BeforeClass
    public void beforeMeto()
    {
        System.setProperty("webdriver.chrome.driver","C:/maven/ChromeDriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
        driver.get("https://phptravels.net/home");

        driver.findElement((By.xpath("*//button[contains(@class, 'cc-btn cc-dismiss')]"))).click();

    }

    @Test (priority = 1)
    public void CreateNewUser() throws InterruptedException {
        //Arrange
        FirstName = "Kevin";
        LastName = "Doter";
        phoneNumber = "+123433422";
        email = "sobaka"+(int)(0+Math.random()*10000)+"@sob.aca";
        password = "12345kevin";
        confirmpassword = password;

        //Act
        driver.findElement(By.cssSelector(".dropdown-login")).click();
        driver.findElement(By.xpath("*//div[contains(@class, 'dropdown-login')]//a[contains(text(), 'Sign Up')]")).click();
        driver.manage().timeouts();
        Boolean SignUpCheck = driver.findElement(By.xpath("//h3[contains(text(), 'Sign Up')]")).isEnabled();
        driver.findElement((By.xpath("*//input[@name = 'firstname']"))).sendKeys(FirstName);
        driver.findElement((By.xpath("*//input[@name = 'lastname']"))).sendKeys(LastName);
        driver.findElement((By.xpath("*//input[@name = 'phone']"))).sendKeys(phoneNumber);
        driver.findElement((By.xpath("*//input[@name = 'email']"))).sendKeys(email);
        driver.findElement((By.xpath("*//input[@name = 'password']"))).sendKeys(password);
        driver.findElement((By.xpath("*//input[@name = 'confirmpassword']"))).sendKeys(confirmpassword);
        driver.findElement((By.xpath("*//button[contains(@class, 'btn-block')]"))).click();
        Thread.sleep(3000);
        String actual = driver.findElement(By.cssSelector(".text-align-left")).getText();

        //Assert
        Assert.assertTrue(SignUpCheck);
        Assert.assertEquals(actual, "Hi, " + FirstName + " " + LastName);

    }
    @Test (priority = 2)
    public void LoginToCreatedUser() throws InterruptedException {

        //Act
        driver.findElement(By.cssSelector(".dropdown-login")).click();
        driver.findElement(By.xpath("*//div[contains(@class, 'dropdown-login')]//a[contains(text(), 'Logout')]")).click();
        driver.manage().timeouts();
        Boolean LoginUpChecks = driver.findElement(By.xpath("//h3[contains(text(), 'Login')]")).isEnabled();
        driver.findElement((By.xpath("*//input[@name = 'username']"))).sendKeys(email);
        driver.findElement((By.xpath("*//input[@name = 'password']"))).sendKeys(password);
        driver.findElement((By.xpath("*//button[contains(@class, 'loginbtn')]"))).click();
        Thread.sleep(3000);
        String actualAfterLogin = driver.findElement(By.cssSelector(".text-align-left")).getText();



        //Assert
        Assert.assertTrue(LoginUpChecks);
        Assert.assertEquals(actualAfterLogin, "Hi, " + FirstName + " " + LastName);

    }

    @Test (priority = 3)
    public void ChangeInformationAboutUs() throws InterruptedException {
        //Arrange
        newEmail = "sobaka"+(int)(0+Math.random()*10000)+"@nesob.aca";
        Adress1 = "Toronto, VasyaPupkin Street, 12a";
        Adress2 = "New York, VasyaPupkinNY Street, 12a";
        City = "Toronto";
        State = "Toronto States";
        Zip = "0231";
        newPhone = "+9870963";
        Country = "Canada";

        //Act
        System.out.println("Testing 3 auto is started");
        driver.findElement(By.xpath("*//a[contains(@href, '#profile')]")).click();
        driver.manage().timeouts();
        driver.findElement((By.xpath("*//input[@name = 'email']"))).clear();
        driver.findElement((By.xpath("*//input[@name = 'email']"))).sendKeys(newEmail);
        driver.findElement((By.xpath("*//input[@name = 'address1']"))).sendKeys(Adress1);
        driver.findElement((By.xpath("*//input[@name = 'address2']"))).sendKeys(Adress2);
        driver.findElement((By.xpath("*//input[@name = 'city']"))).sendKeys(City);
        driver.findElement((By.xpath("*//input[@name = 'state']"))).sendKeys(State);
        driver.findElement((By.xpath("*//input[@name = 'zip']"))).sendKeys(Zip);
        //driver.findElement((By.xpath("*//a[@class = 'chosen-single']"))).click();     Це потім із вибором країни
        //driver.findElement((By.xpath("*//input[contains(@class, 'chosen-search-input')]"))).sendKeys(Country);
        driver.findElement((By.xpath("*//input[@name = 'phone']"))).clear();
        driver.findElement((By.xpath("*//input[@name = 'phone']"))).sendKeys(newPhone);
        driver.findElement((By.xpath("*//button[contains(@class, 'updateprofile')]"))).click();
        Thread.sleep(3000);
        System.out.println("first detecte");
        String ThePageIsRefreshed = driver.findElement(By.xpath("*//a[@class = 'nav-link go-text-right active']")).getText();
        System.out.println("second detect");
        driver.manage().timeouts();


        WebElement new_butt = driver.findElement((By.xpath("*//a[contains(@href, '#profile')]")));
        Actions action= new Actions(driver);
        action.moveToElement(new_butt).build().perform();

        driver.findElement((By.xpath("*//a[contains(@href, '#profile')]"))).click();
        System.out.println("Third detect");
        Thread.sleep(4000);
        emailCheck = driver.findElement((By.xpath("*//input[@name = 'email']"))).getAttribute("value");
        System.out.println(emailCheck);
        address1Check = driver.findElement((By.xpath("*//input[@name = 'address1']"))).getAttribute("value");
        action.moveToElement(driver.findElement((By.xpath("*//input[@name = 'address1']")))).build().perform();
        System.out.println(address1Check+" Address1");
        address2Check = driver.findElement((By.xpath("*//input[@name = 'address2']"))).getAttribute("value");
        System.out.println(address2Check+" Address1");
        cityCheck = driver.findElement((By.xpath("*//input[@name = 'city']"))).getAttribute("value");
        System.out.println(cityCheck+" City");
        stateCheck = driver.findElement((By.xpath("*//input[@name = 'state']"))).getAttribute("value");
        System.out.println(stateCheck+" State");
        zipCheck = driver.findElement((By.xpath("*//input[@name = 'zip']"))).getAttribute("value");
        System.out.println(zipCheck);
        //countryCheck = driver.findElement((By.xpath("*//a[@class = 'chosen-single']"))).getText();
        phoneCheck = driver.findElement((By.xpath("*//input[@name = 'phone']"))).getAttribute("value");
        System.out.println(phoneCheck);



        //Assert
        Assert.assertEquals(ThePageIsRefreshed, "BOOKINGS");
        /*
        Це зробити пізніше перевірку чи все змінилося так як потрібно

        Assert.assertEquals(nameCheck, FirstName);
        Assert.assertEquals(lastNameCheck, LastName);
        Assert.assertEquals(emailCheck, newEmail);
        Assert.assertEquals(address1Check, Adress1);
        Assert.assertEquals(address2Check, Adress2);
        Assert.assertEquals(cityCheck, City);
        Assert.assertEquals(stateCheck, State);
        Assert.assertEquals(zipCheck, Zip);
        //Assert.assertEquals(countryCheck, Country);
        Assert.assertEquals(phoneCheck, newPhone);*/
    }
    @Test (priority = 4)
    public void loginAfterChangingEmail()
    {
        //Act
        driver.findElement(By.cssSelector(".dropdown-login")).click();
        driver.findElement(By.xpath("*//div[contains(@class, 'dropdown-login')]//a[contains(text(), 'Logout')]")).click();
        driver.manage().timeouts();
        Boolean LoginUpCheck = driver.findElement(By.xpath("//h3[contains(text(), 'Login')]")).isEnabled();
        driver.findElement((By.xpath("*//input[@name = 'username']"))).sendKeys(newEmail);
        driver.findElement((By.xpath("*//input[@name = 'password']"))).sendKeys(password);
        driver.findElement((By.xpath("*//button[contains(@class, 'loginbtn')]"))).click();
        driver.manage().timeouts();
        String actualAfterLogin = driver.findElement(By.cssSelector(".text-align-left")).getText();



        //Assert
        Assert.assertTrue(LoginUpCheck);
        Assert.assertEquals(actualAfterLogin, "Hi, " + FirstName + " " + LastName);

    }


    @AfterClass
    public void AfterMed()
    {
        driver.quit();
    }

}
