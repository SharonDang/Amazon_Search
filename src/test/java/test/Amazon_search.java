package test;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Amazon_search {
    WebElement searchBox, count;
    String sString="candle";

    @Test
    public void openPage() {
        String a, text = null;

        System.setProperty("webdriver.chrome.driver", "D:/risk_register_user_roles_issue/AUTOMATION/source/chromedriver_win32/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.amazon.com/");
        driver.manage().window().maximize();//maximize screen
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);//delay 10s

        searchBox = driver.findElement(By.name("field-keywords"));
        searchBox.sendKeys(sString);
        searchBox.submit();

        List<WebElement> elements = driver.findElements(By.xpath("//h2[@class=\"a-size-mini a-spacing-none a-color-base s-line-clamp-2\"]"));
        System.out.println("Number of elements:" +elements.size());

        for (int i=0; i<elements.size();i++){
            text = elements.get(i).getText().toUpperCase();
            System.out.println(i+1 + ". " + text);

            if( text.contains(sString.toUpperCase())==false){
                System.out.println("Failed");
                driver.quit();
            }

            Assert.assertTrue(text.contains(sString.toUpperCase()));//compare ignore case
            System.out.println("Passed");
        }
        driver.quit();//close browser
    }
}

