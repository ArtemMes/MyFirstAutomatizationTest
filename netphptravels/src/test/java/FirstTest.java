import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class FirstTest {
    WebDriver driver;
    String email, password, confirmpassword, phoneNumber, FirstName, LastName;

    @BeforeMethod
    public void beforeMeto()
    {
        System.setProperty("webdriver.chrome.driver","C:/maven/ChromeDriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://phptravels.net/home");
        FirstName = "Kevin";
        LastName = "Doter";
        phoneNumber = "+123433422";
        email = "sobaka"+(int)(0+Math.random()*10000)+"@sob.aca";
        password = "12345kevin";
        confirmpassword = password;
    }

    @Test
    public void CreateNewUser() throws InterruptedException {
        driver.findElement(By.cssSelector(".dropdown-login")).click();
        driver.findElement(By.xpath("*//div[contains(@class, 'dropdown-login')]//a[contains(text(), 'Sign Up')]")).click();
        String URLRegis = driver.getCurrentUrl();
        //when not register page
        //System.out.println(URLRegis);
        Assert.assertEquals(URLRegis, "https://phptravels.net/register");
        driver.findElement((By.xpath("*//input[@name = 'firstname']"))).sendKeys(FirstName);
        driver.findElement((By.xpath("*//input[@name = 'lastname']"))).sendKeys(LastName);
        driver.findElement((By.xpath("*//input[@name = 'phone']"))).sendKeys(phoneNumber);
        driver.findElement((By.xpath("*//input[@name = 'email']"))).sendKeys(email);
        driver.findElement((By.xpath("*//input[@name = 'password']"))).sendKeys(password);
        driver.findElement((By.xpath("*//input[@name = 'confirmpassword']"))).sendKeys(confirmpassword);
        driver.findElement((By.xpath("*//button[contains(@class, 'btn-block')]"))).click();
        Thread.sleep(1000);
        String URL = driver.getCurrentUrl();
        //System.out.println(URL);
        Assert.assertEquals(URL, "https://phptravels.net/account/");
    }
    @Test
    public void LoginToCreatedUser() throws InterruptedException {
        driver.findElement(By.cssSelector(".dropdown-login")).click();
        driver.findElement((By.xpath("*//div[contains(@class, 'dropdown-login')]//a[contains(text(), 'Sign Up')]"))).click();
        String URLRegis = driver.getCurrentUrl();
        //when not register page
        //System.out.println(URLRegis);
        Assert.assertEquals(URLRegis, "https://phptravels.net/register");
        driver.findElement((By.xpath("*//input[@name = 'firstname']"))).sendKeys(FirstName);
        driver.findElement((By.xpath("*//input[@name = 'lastname']"))).sendKeys(LastName);
        driver.findElement((By.xpath("*//input[@name = 'phone']"))).sendKeys(phoneNumber);
        driver.findElement((By.xpath("*//input[@name = 'email']"))).sendKeys(email);
        driver.findElement((By.xpath("*//input[@name = 'password']"))).sendKeys(password);
        driver.findElement((By.xpath("*//input[@name = 'confirmpassword']"))).sendKeys(confirmpassword);
        driver.findElement((By.xpath("*//button[contains(@class, 'btn-block')]"))).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".dropdown-login")).click();
        driver.findElement((By.xpath("*//div[contains(@class, 'dropdown-login')]//a[contains(text(),'Logout')]"))).click();
        Thread.sleep(1000);
        String URLLogOut = driver.getCurrentUrl();
        //System.out.println(URLLogOut);
        Assert.assertEquals(URLLogOut, "https://phptravels.net/login");
        driver.findElement((By.xpath("*//input[@name = 'username']"))).sendKeys(email);
        driver.findElement((By.xpath("*//input[@name = 'password']"))).sendKeys(password);
        driver.findElement((By.xpath("*//button[contains(@class, 'btn-block')]"))).click();
        Thread.sleep(1000);
        String URLAfterLogIn = driver.getCurrentUrl();
        //System.out.println(URLAfterLogIn);
        Assert.assertEquals(URLAfterLogIn, "https://phptravels.net/account/");
    }


    @AfterMethod
    public void AfterMed()
    {
        driver.quit();
    }

}
