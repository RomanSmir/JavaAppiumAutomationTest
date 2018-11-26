import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;



public class MainTest {

    private AppiumDriver driver;
    @Before
    public void setUp() throws Exception

    {
       DesiredCapabilities capabilities = new DesiredCapabilities();

       capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","Android");
        capabilities.setCapability("platformVersion","8.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","E:\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");


        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }


    @After
    public void tearDown()
    {
        driver.quit();
    }



//@Test
//    public void firstTest()
//{
//    waitForElementAndClik(
//            By.xpath("//*[contains(@text,'Search Wikipedia')]"),
//            "Cannot findSearch Wikipedia input",
//            5
//
//    );
//
//    waitForElementAndSendKeys(
//            By.xpath("//*[contains(@text,'Search…')]"),
//            "Java",
//            "Cannot find search input",
//            10
//
//    );
//
//    waitForElementPresent(
//          By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Java (programming language)']"),
//           "Cannot find 'Java (programming language)'Java'",
//           15
//   );
//
//}
//
//@Test
//public void testCancelSearch()
//{
//    waitForElementAndClik(
//By.id("org.wikipedia:id/search_container"),
//    "Cannot find 'Search Wikipedia' input",
//            5
//    );
//
//waitForElementAndSendKeys(
//        By.xpath("//*[contains(@text,'Search…')]"),
//        "Java",
//        "Cannot find'Java'",
//        5
//);
//
//waitForElementAndClear(
//        By.id("org.wikipedia:id/search_src_text"),
//        "cannot find search failed",
//        5
//);
//
//    waitForElementAndClik(
//            By.id("org.wikipedia:id/search_close_btn"),
//            "X is still present on the page",
//            5
//
//    );
//    waitForElementNotPresent(
//            By.id("org.wikipedia:id/search_close_btn"),
//            "Cannot find x to cancel search",
//            5
//    );
//}


   @Test
   public void testCompareArticleTitle()
   {
           waitForElementAndClik(
           By.xpath("//*[contains(@text,'Search Wikipedia')]"),
    "Cannot find 'Search Wikipedia' input",
            5
            );

    waitForElementAndSendKeys(
            By.xpath("//*[contains(@text,'Search…')]"),
        "Java",
                "Cannot find'Java'",
                5
                );

       waitForElementAndClik(
               By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Java (programming language)']"),
               "Cannot find 'Search Wikipedia' input",
               15
       );
       WebElement title_element = waitForElementPresent(
             By.id("org.wikipedia:id/view_page_title_text"),
               "Cannot find article title",
               15

               );


       String article_title = title_element.getAttribute("text");

       Assert.assertEquals(
               "We see unexpected title",
               "Java (programming language)",
               article_title
       );
   }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)

{
    WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
    wait.withMessage(error_message+"\n");
    return wait.until(
            ExpectedConditions.presenceOfElementLocated(by)
            
    );

}

private WebElement waitForElementPresent(By by, String error_message)
{
    return waitForElementPresent(by,error_message,5);
}

private WebElement waitForElementAndClik(By by, String error_message, long timeoutInseconds)
{
    WebElement element = waitForElementPresent(by,error_message,timeoutInseconds);
    element.click();
    return element;
}

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInseconds)
    {
        WebElement element = waitForElementPresent(by,error_message,timeoutInseconds);
        element.sendKeys(value);
        return element;
    }



        private boolean waitForElementNotPresent(By by, String error_message, long timeOutInSeconds)
        {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.withMessage(error_message+"\n");
            return wait.until(
                    ExpectedConditions.invisibilityOfElementLocated(by));
        }

        private WebElement waitForElementAndClear(By by, String error_message, long timeotInSeconds)
        {
            WebElement element = waitForElementPresent(by,error_message,timeotInSeconds);
            element.clear();
            return element;
        }
}
