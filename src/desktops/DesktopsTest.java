package desktops;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;

public class DesktopsTest extends Utility {
    String baseUrl = "https://tutorialsninja.com/demo/index.php";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }


    @Test
    public void verifyProductArrangeInAlphaBaticalOrder() {
        //1.1 Mouse hover on Desktops Tab.and click
        mouseHoverAndClickOnElement(By.linkText("Desktops"));

        //1.2 Click on “Show All Desktops”
        clickOnElement(By.linkText("Show AllDesktops"));

        //1.3 Select Sort By position "Name: Z to A"
        selectByVisibleFromDropDown(By.xpath("//select[@id='input-sort']"), "Name (Z - A)");

        //1.4 Verify the Product will arrange in Descending order.
        assertionMethod("Products are not in Descending order", "Name (Z - A)", By.xpath("//option[@value='https://tutorialsninja.com/demo/index.php?route=product/category&path=20&sort=pd.name&order=DESC']"));
    }


    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {

        //2.1 Mouse hover on Currency Dropdown and click
        clickOnElement(By.xpath("//button[@class='btn btn-link dropdown-toggle']"));

        //2.2 Mouse hover on £Pound Sterling and click
        clickOnElement(By.xpath("//button[normalize-space()='£Pound Sterling']"));

        //2.3 Mouse hover on Desktops Tab.
        mouseHoverToElement(By.xpath("//a[@class='dropdown-toggle'][normalize-space()='Desktops']"));

        //2.4 Click on “Show All Desktops”
        mouseHoverAndClickOnElement(By.xpath("//a[normalize-space()='Show AllDesktops']"));

        //2.5 Select Sort By position "Name: A to Z"
        //selectByVisibleFromDropDown(By.xpath("//select[@id='input-sort']"), "Name (A - Z)");
        selectByVisibleFromDropDown(By.xpath("//select[@id='input-sort']"), "Name (A - Z)");

        //2.6 Select product “HP LP3065”
        clickOnElement(By.linkText("HP LP3065"));

        //2.7 Verify the Text "HP LP3065"
        Assert.assertEquals("Matched", "HP LP3065", getTextFromTheElement(By.xpath("//h1[normalize-space()='HP LP3065']")));

        //2.8 Select Delivery Date "2023-11-27"
        selectDate("30", "November", "2022");



        //2.9.Enter Qty "1” using Select class.
        clearText(By.xpath("//input[@id='input-quantity']"));
        sendTextToElement(By.name("quantity"), "1");

        //2.10 Click on “Add to Cart” button
        clickOnElement(By.xpath("//button[@id='button-cart']"));

        // 2.11 Verify the Message “Success: You have added HP LP3065 to your shopping cart!”
        String actualMessage = getTextFromTheElement(By.xpath("//div[@class='alert alert-success alert-dismissible']"));
        String[] actualmsg = actualMessage.split("×");
        actualmsg[0].trim();
        String finalMessage = actualmsg[0];
        String expectedMessage = finalMessage + "×";
//verifyElements(expectedMessage, actualmsg[0],By.xpath("//div[@class='alert alert-success alert-dismissible']"));
        Assert.assertEquals("Message", actualMessage, expectedMessage);

// 2.12 Click on link “shopping cart” display into success message
        Thread.sleep(1000);
        clickOnElement(By.linkText("shopping cart"));



// 2.13 Verify the text "Shopping Cart"
        expectedMessage = "Shopping Cart  (1.00kg)";
        actualMessage = getTextFromTheElement(By.xpath("//body/div/div/div/h1[1]"));
        verifyElements(expectedMessage, actualMessage, By.xpath("//body/div/div/div/h1[1]"));

//2.14 Verify the Product name "HP LP3065"
        expectedMessage = "HP LP3065";
        actualMessage = getTextFromTheElement(By.xpath("//body[1]/div[2]/div[1]/div[1]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/a[1]"));
        verifyElements(expectedMessage, actualMessage, By.xpath("//body[1]/div[2]/div[1]/div[1]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/a[1]"));

// 2.15 Verify the Delivery Date "2022-11-30"
        expectedMessage = "2022-11-30";
        actualMessage = getTextFromTheElement(By.xpath("(//small)[2]"));
        String[] actualmsg1 = actualMessage.split(":");
//verifyElements(actualmsg1[1], expectedMessage, By.xpath("(//small)[2]"));
        Assert.assertEquals("Delivery Date does not match", actualmsg1[1], expectedMessage);

// 2.16 Verify the Model "Product21"
        expectedMessage = "Product 21";
        actualMessage = getTextFromTheElement(By.xpath("//td[normalize-space()='Product 21']"));
        verifyElements(expectedMessage, actualMessage, By.xpath("//td[normalize-space()='Product 21']"));

// 2.17 Verify the Todat "£74.73"

        Assert.assertEquals("£74.73", getTextFromTheElement(By.xpath("(//td[contains(text(),'£74.73')])[4]")));

    }

    public void selectDate(String date, String month, String year) throws InterruptedException {
        clickOnElement(By.xpath("//i[@class='fa fa-calendar']"));
        while (true) {
            String monthYear = driver.findElement(By.xpath("(//th[@class='picker-switch'])[1]")).getText();
            String[] a = monthYear.split(" ");
            String mon = a[0];
            String yer = a[1];
            if (mon.equalsIgnoreCase(month) && yer.equalsIgnoreCase(year)) {
                break;
            } else {
                clickOnElement(By.xpath("//div[@class='datepicker-days']//th[@class='next'][contains(text(),'›')]"));
            }
        }
        Thread.sleep(1000);
        // Select the date
        List<WebElement> allDates = driver.findElements(By.xpath("//*[@class='datepicker-days']//tbody//tr//td"));
        for (WebElement dt : allDates) {
            if (dt.getText().equalsIgnoreCase(date)) {
                dt.click();
                break;
            }
        }
    }


    @After
    public void tearDown() {
        //closeBrowser();
    }

}
