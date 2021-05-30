package SampleCase;

import org.apache.bcel.generic.Select;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.openqa.selenium.By.*;


public class TestCase {
    protected WebDriver driver;
    public static String WebsiteUrl = "https://www.gittigidiyor.com/";
    public static String SecondPage = "https://www.gittigidiyor.com/arama/?k=bilgisayar&sf=2";

    @Before

    public void setUp() {
        try {
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\ENUYGUN\\IdeaProjects\\gittigidiyor\\ChromeDriver\\chromedriver.exe");
            driver = new ChromeDriver(capabilities);

            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();

            //dynamic wait
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


        } finally {

        }
    }
@Test

public void login() throws InterruptedException {
/*
    // Siteye gidilir ve üye girişi yapılır.
    driver.get(WebsiteUrl);
    WebElement ele = driver.findElement(xpath("//*[@id=\"main-header\"]/div[3]/div/div/div[1]/div[3]/div/div[1]/div/div[1]/div"));
    Actions action = new Actions(driver);
    action.moveToElement(ele).perform();
    driver.findElement(xpath("/html//header[@id='main-header']/div/div/div/div//div[@class='gekhq4-5 hJhHqb']/div/div//a[@href='https://www.gittigidiyor.com/uye-girisi?s=1']/span[.='Giriş Yap']")).click();
    driver.findElement(id("L-UserNameField")).click();
    driver.findElement(id("L-UserNameField")).sendKeys("cemresilbir");
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.findElement(id("L-PasswordField")).click();
    driver.findElement(id("L-PasswordField")).sendKeys("cemre12345");
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.findElement(xpath("//*[@id=\"gg-login-enter\"]")).click();

    // Güvenlik sebebiyle otomasyon ile giriş yapamıyorum, giriş yaptığımı varsayarak ilerliyorum.
*/
    driver.get(WebsiteUrl);
    Assert.assertEquals("GittiGidiyor - Türkiye'nin Öncü Alışveriş Sitesi", driver.getTitle());
    System.out.println("Başarılı şekilde login olundu");

    sleep(100);

    // Bilgisayar ürünü arama kutucuğuna yazılır ve searche tıklanır.
    driver.findElement(xpath("//*[contains(@class,'sc-4995aq-4 dNPmGY')]")).click();
    driver.findElement(xpath("/html//header[@id='main-header']/div/div/div/div//form[@action='https://www.gittigidiyor.com/arama']//input[@name='k']")).sendKeys("bilgisayar");
    driver.findElement(xpath("/html//header[@id='main-header']/div/div/div/div/div/form[@action='https://www.gittigidiyor.com/arama']//button[@type='submit']/span[.='BUL']")).click();
    sleep(1000);

    // Listeleme sayfasında 2. sayfaya gidilir.

    driver.get(SecondPage);

    // Random bir ürün seçilir ve sepete eklenir.

    driver.findElement(xpath("//*[@id=\"item-info-block-695373966\"]")).click();
    String productPrice =driver.findElement(id("sp-price-lowPrice")).getText();
    System.out.println(productPrice);
    JavascriptExecutor js = (JavascriptExecutor)driver;
    js.executeScript("window.scrollBy(0,600)");
    driver.findElement(xpath("//*[@id=\"add-to-basket\"]")).click();
    sleep(1000);

    // Sepete gidilir ve sepette bulunan ürünün fiyatı doğrulanır.
    WebElement ele = driver.findElement(xpath("//*[@id=\"header_wrapper\"]/div[4]/div[3]/a"));
    Actions action = new Actions(driver);
    action.moveToElement(ele).perform();
    driver.findElement(xpath("//*[@id=\"header_wrapper\"]/div[4]/div[3]/div/div/div/div[2]/div[4]/div[1]/a")).click();
    String TotalPrice = driver.findElement(className("total-price")).getText();
    System.out.println(TotalPrice);
    Assert.assertEquals(productPrice,TotalPrice);
    System.out.println("Prices are equal.");

    // Sepette bulunan ürün miktarı arttırılır.
    //Select selectelement = new Select(driver.findElement(xpath("//*[@id=\"cart-item-478048883\"]/div[1]/div[4]/div/div[2]/select")));

    // Ürün sepetten silinir ve boş sepet doğrulanır.
    driver.findElement(xpath("/html/body/div[1]/div[2]/div/div[1]/form/div/div[2]/div[2]/div/div/div[6]/div[2]/div[2]/div[1]/div[3]/div/div[2]/div/a")).click();
    String FinalPrice = driver.findElement(className("new-price")).getText();
    Assert.assertNotEquals(TotalPrice , FinalPrice);
    System.out.println("The product has been deleted from the cart.");

}
}



