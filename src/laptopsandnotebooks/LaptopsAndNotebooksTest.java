package laptopsandnotebooks;

import com.google.common.base.Verify;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import utilities.Utility;

public class LaptopsAndNotebooksTest extends Utility {
    String baseUrl = "https://tutorialsninja.com/demo/index.php";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyProductsPriceDisplayHighToLowSuccessfully() {
        //1.1 Mouse hover on Laptops & Notebooks Tab.and click
        mouseHoverToElement(By.xpath("//a[text()='Laptops & Notebooks']"));

        //1.2 Click on “Show All Laptops & Notebooks”
        clickOnElement(By.xpath("//a[text()='Laptops & Notebooks']"));

        //1.3 Select Sort By "Price (High > Low)"
        clickOnElement(By.xpath("//a[text()='Show AllLaptops & Notebooks']"));

        //1.4 Verify the Product price will arrange in High to Low order.
        assertionMethod("Displayed are not arranged by High to Low", "Price (High > Low)", By.xpath("//option[@value='https://tutorialsninja.com/demo/index.php?route=product/category&path=18&sort=p.price&order=DESC']"));

    }

    @Test
    public void verifyThatUserPlaceOrderSuccessfully() throws InterruptedException {
        //2.1 Mouse hover on Laptops & Notebooks Tab and click
        mouseHoverToElement(By.xpath("//a[normalize-space()='Laptops & Notebooks']"));

        //2.2 Click on “Show All Laptops & Notebooks”
        clickOnElement(By.xpath("//a[normalize-space()='Show AllLaptops & Notebooks']"));

        //2.3 Select Sort By "Price (High > Low)"
        selectByValueFromDropDown(By.id("input-sort"), "https://tutorialsninja.com/demo/index.php?route=product/category&path=18&sort=p.price&order=DESC");

        //2.4 Select Product “MacBook”
        clickOnElement(By.linkText("Sony VAIO"));

        //2.5 Verify the text “MacBook”
        Assert.assertEquals("Sony VAIO", getTextFromTheElement(By.xpath("//h1[contains(text(),'Sony VAIO')]")));

        //2.6 Click on ‘Add To Cart’ button
        clickOnElement(By.id("button-cart"));

        //2.7 Verify the message “Success: You have added MacBook to your shopping cart!”
        Assert.assertEquals("Success: You have added Sony VAIO to your shopping cart!", getTextFromTheElement(By.xpath("//div[contains(text(),'Success')]")).substring(0, 56));

        //2.8 Click on link “shopping cart” display into success message
        clickOnElement(By.linkText("shopping cart"));

        //2.9 Verify the text "Shopping Cart"
        Assert.assertEquals("Shopping Cart", getTextFromTheElement(By.xpath("//h1[contains(text(),'Shopping Cart')]")).substring(0, 13));

        //2.10 Verify the Product name "MacBook"
        Assert.assertEquals("Sony VAIO", getTextFromTheElement(By.linkText("Sony VAIO")));

        //2.11 Change Quantity "2"
        driver.findElement(By.xpath("//input[@class='form-control']")).clear();
        sendTextToElement(By.xpath("//input[@class='form-control']"), "2");

        //2.12 Click on “Update” Tab
        clickOnElement(By.xpath("//button[@type='submit']"));

        //2.13 Verify the message “Success: You have modified your shopping cart!”
        Assert.assertEquals("Success: You have modified your shopping cart!", getTextFromTheElement(By.xpath("//div[contains(text(),'Success')]")).substring(0, 46));

        //2.14 Verify the Total £737.45
        //Assert.assertEquals("£1,472.45", getTextFromTheElement(By.xpath("//tbody//tr//td[6]")));

        String expTotal = "$2,404.00";
        WebElement toRightOf = driver.findElement(RelativeLocator.with(By.xpath("//td[@class='text-right']")).toRightOf(By.xpath("//strong[normalize-space()='Total:']")));
        String actTotal = toRightOf.getText();
        Assert.assertEquals("Invalid total", expTotal, actTotal);

        //2.15 Click on “Checkout” button
        clickOnElement(By.xpath("//a[@class='btn btn-primary']"));

        //2.16 Verify the text “Checkout”
        Assert.assertEquals("Checkout", getTextFromTheElement(By.xpath("//h1[normalize-space()='Checkout']")));


        //2.17 Verify the Text “New Customer”
        String expectedNewCustomer = "New Customer";
        String actualNewCustomer = getTextFromTheElement(By.xpath("//div[@class='col-sm-6']/h2[text()='New Customer']"));
        Assert.assertEquals("New customer not found",expectedNewCustomer,actualNewCustomer);

        //2.18 Click on “Guest Checkout” radio button
        clickOnElement(By.xpath("//input[@value='guest']"));

        //2.19 Click on “Continue” tab
        clickOnElement(By.id("button-account"));

        //2.20 Fill the mandatory fields
        sendTextToElement(By.id("input-payment-firstname"), "Yatri");
        sendTextToElement(By.id("input-payment-lastname"), "Hirani");
        sendTextToElement(By.id("input-payment-email"), "yhirani82202@gmail.com");
        sendTextToElement(By.id("input-payment-telephone"), "1234567890");
        sendTextToElement(By.id("input-payment-address-1"), "88 - Marsden street");
        sendTextToElement(By.id("input-payment-city"), "Sydney");
        sendTextToElement(By.id("input-payment-postcode"), "2150");
        selectByValueFromDropDown(By.id("input-payment-country"), "3");
        selectByValueFromDropDown(By.id("input-payment-zone"), "3515");

        //2.21 Click on “Continue” Button
        clickOnElement(By.id("button-guest"));

        //2.22 Add Comments About your order into text area
        sendTextToElement(By.xpath("//textarea[@name='comment']"), "I want a macbook");

        //2.23 Check the Terms & Conditions check box
        clickOnElement(By.id("button-shipping-method"));

        //2.24 Click on “Continue” button
        Thread.sleep(1000);
        clickOnElement(By.xpath("//input[@name='agree']"));
        clickOnElement(By.id("button-payment-method"));

        //2.25 Verify the message “Warning: Payment method required!”
        clickOnElement(By.id("button-confirm"));
    }


    @After
    public void tearDown() {
        //closeBrowser();
    }
}
