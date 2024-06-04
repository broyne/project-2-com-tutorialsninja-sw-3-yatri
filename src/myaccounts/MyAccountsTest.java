package myaccounts;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;

public class MyAccountsTest extends Utility {
    String baseUrl = "https://tutorialsninja.com/demo/index.php";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    //1.1 create method with name "selectMyAccountOptions" it has one parameter name "option" of type string
    public void selectMyAccountOptions (String option){

        //1.2 This method should click on the options whatever name is passed as parameter
        List<WebElement> topMenuNames = driver.findElements(By.xpath("//ul[@class = 'list-inline']//a"));
        for (WebElement names : topMenuNames){
            System.out.println(names.getText());
            if (names.getText().equalsIgnoreCase(option)){
                names.click();
                break;
            }
        }
    }
    @Test
    public void verifyUserShouldNavigateToRegisterPageSuccessfully(){
        //1.1 Click on My Account Link.
        clickOnElement(By.xpath("//span[normalize-space()='My Account']"));

        //1.2 Call the method “selectMyAccountOptions” method and pass the parameter “Register”
        selectMyAccountOptions("Register");

        //1.3 Verify the text “Register Account"
        assertionMethod("Incorrect Option","Register Account",By.xpath("//h1[normalize-space()='Register Account']"));
    }
    @Test
    public void verifyUserShouldNavigateToLoginPageSuccessfully() {
        //2.1 Click on My Account Link.
        clickOnElement(By.xpath("//span[normalize-space()='My Account']"));

        //2.2 Call the method “selectMyAccountOptions” method and pass the parameter “Login”
        selectMyAccountOptions("Login");

        //2.3 Verify the text “Returning Customer"
        assertionMethod("Incorrect Text", "Returning Customer", By.xpath("//h2[normalize-space()='Returning Customer']"));
    }
    @Test
    public void verifyThatUserRegisterAccountSuccessfully(){
        // 3.1 Click on My Account Link.
        clickOnElement(By.xpath("//span[normalize-space()='My Account']"));

        // 3.2 Call the method “selectMyAccountOptions” method and pass the parameter“Register”
        selectMyAccountOptions("Register");

        //3.3 Enter First Name
        sendTextToElement(By.id("input-firstname"),"Beena");

        //3.4 Enter Last Name
        sendTextToElement(By.id("input-lastname"),"Rani");

        //3.5 Enter Email
        final String randomEmail = randomEmail();
        sendTextToElement(By.id("input-email"), randomEmail);

        //3.6 Enter Telephone
        sendTextToElement(By.id("input-telephone"),"9184561234");

        //3.7 Enter Password
        sendTextToElement(By.id("input-password"),"12345678");

        //3.8 Enter Password Confirm
        sendTextToElement(By.xpath("//input[@id='input-confirm']"),"12345678");

        //3.9 Select Subscribe Yes radio button
        clickOnElement(By.xpath("//label[normalize-space()='Yes']"));

        //3.10 Click on Privacy Policy check box
        clickOnElement(By.cssSelector("input[value='1'][name='agree']"));

        //3.11 Click on Continue button
        clickOnElement(By.xpath("//input[@value='Continue']"));

        //3.12 Verify the message “Your Account Has Been Created!”
        assertionMethod("Incorrect message", "Your Account Has Been Created!", By.xpath("//h1[normalize-space()='Your Account Has Been Created!']"));

        //3.13 Click on Continue button
        clickOnElement(By.xpath("//a[normalize-space()='Continue']"));

        //3.14 Click on My Account Link.
        clickOnElement(By.xpath("//span[normalize-space()='My Account']"));

        //3.15 Call the method “selectMyAccountOptions” method and pass the parameter “Logout”
        selectMyAccountOptions("Logout");

        //3.16 Verify the text “Account Logout”
        assertionMethod("Incorrect Text","Logout",By.xpath("//a[normalize-space()='Logout']"));

        //3.17 Click on Continue button
        clickOnElement(By.xpath("//a[normalize-space()='Continue']"));
    }
    @Test
    public void verifyThatUserShouldLoginAndLogoutSuccessfully() {
        //4.1 Click on My Account Link.
        clickOnElement(By.xpath("//span[normalize-space()='My Account']"));

        //4.2 Call the method “selectMyAccountOptions” method and pass the parameter “Login”
        selectMyAccountOptions("Login");

        //4.3 Enter Email address
        sendTextToElement(By.id("input-email"),"yhirani@gmail.com");

        //4.5 Enter Password
        sendTextToElement(By.id("input-password"), "12345678");

        //4.6 Click on Login button
        clickOnElement(By.xpath("//input[@value='Login']"));

        //4.7 Verify text “My Account”
        assertionMethod("Incorrect Text", "My Account", By.xpath("//h2[normalize-space()='My Account']"));

        //4.8 Click on My Account Link.
        clickOnElement(By.xpath("//span[normalize-space()='My Account']"));

        //4.9 Call the method “selectMyAccountOptions” method and pass the parameter Logout”
        selectMyAccountOptions("Logout");

        //4.10 Verify the text “Account Logout”
        assertionMethod("Incorrect Text", "Logout", By.xpath("//a[normalize-space()='Logout']"));

        //4.11 Click on Continue button
        clickOnElement(By.xpath("//a[normalize-space()='Continue']"));
    }




    @After
    public void tearDown() {
        //closeBrowser();
    }
}
