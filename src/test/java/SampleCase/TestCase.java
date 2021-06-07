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
    public static String SecondPage = "https://www.gittigidiyor.com/arama/?k=samsung&sf=2";

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

   // driver.get(WebsiteUrl);
    Assert.assertEquals("GittiGidiyor - Türkiye'nin Öncü Alışveriş Sitesi", driver.getTitle());
    System.out.println("Başarılı şekilde login olundu");

    sleep(100);

    // Samsung ürün arama kutucuğuna yazılır ve searche tıklanır.
    driver.findElement(xpath("//*[contains(@class,'sc-4995aq-4 dNPmGY')]")).click();
    driver.findElement(xpath("/html//header[@id='main-header']/div/div/div/div//form[@action='https://www.gittigidiyor.com/arama']//input[@name='k']")).sendKeys("samsung");
    driver.findElement(xpath("/html//header[@id='main-header']/div/div/div/div/div/form[@action='https://www.gittigidiyor.com/arama']//button[@type='submit']/span[.='BUL']")).click();
    sleep(1000);

    // Samsung sonuçların bulunduğu doğrulanır.
    String searchResult = driver.findElement(className("search-result-keyword")).getText();
    Assert.assertEquals("samsung",searchResult);
    System.out.println("Samsung aramaları bulundu.");

    // Listeleme sayfasında 2. sayfaya gidilir.

    driver.get(SecondPage);

    // Random bir ürün seçilir ve favorilere eklenir.

    driver.findElement(xpath("//*[@id=\"product_id_645276324\"]")).click();
    String ProductTittle = driver.findElement(id("sp-title")).getText();
    JavascriptExecutor js = (JavascriptExecutor)driver;
    js.executeScript("window.scrollBy(0,600)");
    driver.findElement(xpath("//*[@id=\"spp-watch-product\"]/p[1]/span")).click();
    sleep(1000);

    // Favoriler alanına gidilir ve ürün doğrulanır.
    WebElement tag = driver.findElement(xpath("//*[@id=\"header_wrapper\"]/div[4]/div[1]/a"));
    Actions SecondAction = new Actions(driver);
    SecondAction.moveToElement(tag).perform();
    driver.findElement(xpath("//*[@id=\"header_wrapper\"]/div[4]/div[1]/div/div/div/div/ul/li[3]/a")).click();
    String FavProductName = driver.findElement(xpath("/html/body/div[2]/div[1]/div[4]/div[2]/form/div[2]/table/tbody/tr/td[2]/div/div[1]/a/div/div/h2")).getText();
    System.out.println(FavProductName);
    Assert.assertEquals(ProductTittle,FavProductName);
    System.out.println("Ürünler eşleşiyor.");

    // Ürün favorilerden çıkarılır.
    driver.findElement(id("watch-products-item-checkbox-0")).click();
    driver.findElement(xpath("/html/body/div[2]/div[1]/div[4]/div[2]/form/div[1]/button")).click();

   String DeleteFav = driver.findElement(xpath("/html/body/div[2]/div[1]/div[4]/div[1]")).getText();
    Assert.assertEquals("Tebrikler. Ürünler izlediklerim listesinden çıkartıldı.",DeleteFav);
    System.out.println("Ürünler Favorilerim listesinden çıkartıldı.");
}


}



