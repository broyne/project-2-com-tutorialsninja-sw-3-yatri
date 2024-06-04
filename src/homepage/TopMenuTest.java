package homepage;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

public class TopMenuTest extends Utility {

    String baseUrl = "https://tutorialsninja.com/demo/index.php";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    /**
     * 1.1 create method with name "selectMenu" it has one parameter name "menu" of type
     * string
     * 1.2 This method should click on the menu whatever name is passed as parameter.
     * Write the following Test:
     */

    public void selectMenu(String menu) {
        mouseHoverToElement(By.xpath("//a[normalize-space()='" + menu + "']"));
        clickOnElement(By.xpath("//a[normalize-space()='Show All" + menu + "']"));
        String actualText = driver.getTitle();
        String expectedText = menu;
        Assert.assertEquals(expectedText, actualText);
    }

    @Test
    /**
     * 1.1 Mouse hover on “Desktops” Tab and click
     * 1.2 call selectMenu method and pass the menu = “Show All Desktops”
     * 1.3 Verify the text ‘Desktops’
     */
    public void verifyUserShouldNavigateToDesktopsPageSuccessfully() {
        selectMenu("Desktops");
    }

    @Test
    /**
     * 2.1 Mouse hover on “Laptops & Notebooks” Tab and click
     * 2.2 call selectMenu method and pass the menu = “Show All Laptops & Notebooks”
     * 2.3 Verify the text ‘Laptops & Notebooks’
     */
    public void verifyUserShouldNavigateToLaptopsAndNotebooksPageSuccessfully() {
        selectMenu("Laptops & Notebooks");
    }

    @Test
    /**
     * 3.1 Mouse hover on “Components” Tab and click
     * 3.2 call selectMenu method and pass the menu = “Show All Components”
     * 3.3 Verify the text ‘Components’
     */
    public void verifyUserShouldNavigateToComponentsPageSuccessfully() {
        selectMenu("Components");
    }


    @After
    public void tearDown() {
        //closeBrowser();
    }
}
