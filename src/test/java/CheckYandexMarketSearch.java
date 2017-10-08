import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.*;
import org.testng.Assert;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import java.util.List;


public class CheckYandexMarketSearch {
    WebDriver driver;
    WebElement element;

    @BeforeTest
    void beforeWork( )
    {
        driver=new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("http://yandex.ru");
    }

    @Test
    void checkYMSearch() throws InterruptedException {

        element =driver.findElement(By.xpath("//a[@data-id='market']"));
        element.click();
        element = driver.findElement(By.linkText ("Компьютеры"));
        element.click();
        element =driver.findElement (By.linkText("Планшеты"));
        element.click();
        element =driver.findElement (By.linkText("Перейти ко всем фильтрам"));
        element.click();
        element = driver.findElement(By.cssSelector("input#glf-pricefrom-var"));
        element .sendKeys("20000");
        element =driver.findElement(By.cssSelector("input#glf-priceto-var"));
        element .sendKeys("25000");
        element =driver.findElement(By.xpath("//label[text() = 'Acer']"));
        element.click();
        element =driver.findElement (By.linkText("Ещё"));
        element .click();
        element = driver.findElement (By.linkText("Показать подходящие"));
        element.click();

        List<WebElement> elements =  driver.findElements(By.xpath("//div[@class='n-snippet-card2__title']"));
        Integer count=elements.size();
        String el_first = elements.get(0).getText();
        element=driver.findElement(By.cssSelector("input#header-search"));
        element.sendKeys(el_first);
        element.submit();
        element=driver.findElement(By.xpath("//div[@class='n-snippet-card2__title']"));
        String result=element.getText();

        SoftAssert sa=new SoftAssert();
        sa.assertTrue(count==10);
        sa.assertTrue(result.equalsIgnoreCase(el_first));
       //  sa.assertThat(result.as("Строка поиска").equalToIgnoringCase(el_first));
        sa.assertAll();

    }

    @AfterTest
     void afterWork()
    {
       driver.quit();
    }
}
