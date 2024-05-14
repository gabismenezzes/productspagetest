package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RemoveFromCartTest {
    private WebDriver driver;
    private final String username = "visual_user";
    private final String password = "secret_sauce";

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.edge.driver", "C:/Users/maria/Documents/drivers/msedgedriver.exe");
        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }

    @DataProvider(name = "users")
    public Object[][] users() {
        return new Object[][]{
                {"standard_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"visual_user", "secret_sauce"}
        };
    }

    @Test(dataProvider = "users")
    public void testAddAndRemoveFromCart(String username, String password) {
        driver.get("https://www.saucedemo.com/inventory.html");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username);

        WebElement addToCartButton = driver.findElement(By.xpath("//button[@data-test='add-to-cart-sauce-labs-backpack']"));
        addToCartButton.click();


        driver.get("https://www.saucedemo.com/cart.html");
        WebElement removeFromCartButton = driver.findElement(By.xpath("//button[@data-test='remove-sauce-labs-backpack']"));
        removeFromCartButton.click();

    }

    @Test
    public void testRemoveSauceLabsBackpackFromCart() {
        driver.get("https://www.saucedemo.com/inventory.html");
        WebElement addToCartButton = driver.findElement(By.xpath("//button[@data-test='add-to-cart-sauce-labs-backpack']"));
        addToCartButton.click();

        driver.get("https://www.saucedemo.com/cart.html");

        WebElement removeFromCartButton = driver.findElement(By.xpath("//button[@data-test='remove-sauce-labs-backpack']"));


        removeFromCartButton.click();

        boolean isSauceLabsBackpackPresent = driver.findElements(By.xpath("//div[text()='Sauce Labs Backpack']")).isEmpty();
    }

    @Test
    public void testRemoveItemFromCartOnHomePage() {
        driver.get("https://www.saucedemo.com/inventory.html");
        WebElement addToCartButton = driver.findElement(By.xpath("(//button[contains(text(), 'Add to cart')])[1]"));
        addToCartButton.click();

        WebElement removeFromCartButton = driver.findElement(By.xpath("(//button[contains(text(), 'Remove')])[1]"));

        removeFromCartButton.click();

        boolean isItemRemoved = driver.findElements(By.xpath("(//button[contains(text(), 'Remove')])[1]")).isEmpty();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
