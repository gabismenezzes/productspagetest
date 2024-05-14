package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductPageTest {
    private WebDriver driver;
    private String username;
    private String password;


    @FindBy(xpath = "//button[@value='az']")
    private WebElement sortByNameAZButton;

    @FindBy(className = "product_sort_container")
    private WebElement sortByPriceHighLowButton;

    @FindBy(xpath = "//button[@data-test='add-to-cart-sauce-labs-fleece-jacket']")
    private WebElement addToCartButton;

    @FindBy(xpath = "//button[@data-test='remove-sauce-labs-backpack']")
    private WebElement removeFromCartButton;

    public ProductPageTest(WebDriver driver, String username, String password) {
        this.driver = driver;
        this.username = username;
        this.password = password;
        PageFactory.initElements(driver, this);
        loginUser();
    }

    private void loginUser() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username);
    }


    public void clickSortByPriceHighLow() {
        sortByPriceHighLowButton.click();
    }

    public void clickAddToCart() {
        addToCartButton.click();
    }

    public void clickSortByNameAZ() {
        sortByNameAZButton.click();
    }

    public void clickRemoveCart(){
        removeFromCartButton.click();
    }

    @Test
    public void testSortByNameAZ() {
        ProductPageTest productPage = new ProductPageTest(driver, "standard_user", "secret_sauce");
        productPage.clickSortByNameAZ();
    }

    @Test
    public void testSortByPriceHighLow() {
        ProductPageTest productPage = new ProductPageTest(driver, "standard_user", "secret_sauce");
        productPage.clickSortByPriceHighLow();
    }

    @Test
    public void testAddToCart() {
        ProductPageTest productPage = new ProductPageTest(driver, "problem_user", "secret_sauce");
        productPage.clickAddToCart();
    }

    @Test
    public void testRemoveFromCart() {
        ProductPageTest productPage = new ProductPageTest(driver, "visual_user", "secret_sauce");
        productPage.clickRemoveCart();
    }

    @Test
    public void testIncorrectValueForProduct() {
        driver.get("https://www.saucedemo.com/inventory.html");
        WebElement productLink = driver.findElement(By.xpath("//a[contains(text(), 'Sauce Labs Fleece Backpack')]"));
        productLink.click();

        WebElement productPriceElement = driver.findElement(By.className("inventory_details_price"));
        String productPrice = productPriceElement.getText();

        System.out.println("Valor do produto: " + productPrice);
    }

    @Test
    public void testIncorrectProductDetails_SauceLabsBoltTShirt() {
        driver.get("https://www.saucedemo.com/inventory.html");
        WebElement productLink = driver.findElement(By.xpath("//a[contains(text(), 'Sauce Labs Bolt T-Shirt')]"));
        productLink.click();

        WebElement productDescription = driver.findElement(By.className("inventory_item_desc"));
        WebElement productImage = driver.findElement(By.className("inventory_item_img"));

        Assert.assertNotEquals(productDescription.getText(), "Sauce Labs Onesie", "A descrição do produto Sauce Labs Bolt T-Shirt está incorreta.");
        Assert.assertFalse(productImage.getAttribute("src").contains("onesie"), "A imagem do produto Sauce Labs Bolt T-Shirt está incorreta.");
    }

    @Test
    public void testIncorrectProductDetails_SauceLabsOnesie() {
        driver.get("https://www.saucedemo.com/inventory.html");
        WebElement productLink = driver.findElement(By.xpath("//a[contains(text(), 'Sauce Labs Onesie')]"));
        productLink.click();

        WebElement productDescription = driver.findElement(By.className("inventory_item_desc"));
        WebElement productImage = driver.findElement(By.className("inventory_item_img"));

        Assert.assertNotEquals(productDescription.getText(), "Test.allTheThings() T-Shirt (Red)", "A descrição do produto Sauce Labs Onesie está incorreta.");
        Assert.assertFalse(productImage.getAttribute("src").contains("red-shirt"), "A imagem do produto Sauce Labs Onesie está incorreta.");
    }

    @Test
    public void testIncorrectProductDetails_TestAllTheThingsTShirt() {
        driver.get("https://www.saucedemo.com/inventory.html");
        WebElement productLink = driver.findElement(By.xpath("//a[contains(text(), 'Test.allTheThings() T-Shirt (Red)')]"));
        productLink.click();

        WebElement productDescription = driver.findElement(By.className("inventory_item_desc"));
        WebElement productImage = driver.findElement(By.className("inventory_item_img"));

        Assert.assertNotEquals(productDescription.getText(), "Sauce Labs Backpack", "A descrição do produto Test.allTheThings() T-Shirt (Red) está incorreta.");
        Assert.assertFalse(productImage.getAttribute("src").contains("backpack"), "A imagem do produto Test.allTheThings() T-Shirt (Red) está incorreta.");
    }

    @Test
    public void testIncorrectProductDetails_SauceLabsFleeceJacket() {
        driver.get("https://www.saucedemo.com/inventory.html");
        WebElement productLink = driver.findElement(By.xpath("//a[contains(text(), 'Sauce Labs Fleece Jacket')]"));
        productLink.click();

        WebElement productDescription = driver.findElement(By.className("inventory_item_desc"));
        WebElement productImage = driver.findElement(By.className("inventory_item_img"));

        Assert.assertNotEquals(productDescription.getText(), "ITEM NOT FOUND", "A descrição do produto Sauce Labs Fleece Jacket está incorreta.");
        Assert.assertFalse(productImage.getAttribute("src").contains("not-found"), "A imagem do produto Sauce Labs Fleece Jacket está incorreta.");
    }
}
