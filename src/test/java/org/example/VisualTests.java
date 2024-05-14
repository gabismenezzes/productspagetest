package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class VisualTests {
    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.edge.driver", "C:/Users/maria/Documents/drivers/msedgedriver.exe");
        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testSauceLabsBackpackImage() {
        driver.get("https://www.saucedemo.com/inventory.html");

        WebElement sauceLabsBackpack = driver.findElement(By.xpath("//a[@id='item_4_title_link']"));
        sauceLabsBackpack.click();

        WebElement productImage = driver.findElement(By.xpath("//div[@class='inventory_details_img_container']/img"));
        String imageURL = productImage.getAttribute("src");
        Assert.assertTrue(imageURL.contains("sauce-backpack.jpg"), "A imagem exibida não corresponde à do Sauce Labs Backpack");
    }

    @Test
    public void testProductLayout() {
        driver.get("https://www.saucedemo.com/inventory.html");

        WebElement boltTShirt = driver.findElement(By.xpath("//div[@id='inventory_container']//a[@id='item_1_title_link']"));
        WebElement fleeceJacket = driver.findElement(By.xpath("//div[@id='inventory_container']//a[@id='item_5_title_link']"));

        String boltTShirtAlign = boltTShirt.getCssValue("text-align");
        String fleeceJacketAlign = fleeceJacket.getCssValue("text-align");

        Assert.assertEquals(boltTShirtAlign, "left", "A orientação do produto Sauce Labs Bolt T-shirt está incorreta");
        Assert.assertEquals(fleeceJacketAlign, "left", "A orientação do produto Sauce Labs Fleece Jacket está incorreta");

        driver.get("https://www.saucedemo.com/inventory-item.html?id=0");
        WebElement addToCartButton = driver.findElement(By.xpath("//button[@data-test='add-to-cart-sauce-labs-bolt-t-shirt']"));
        String addToCartButtonAlign = addToCartButton.getCssValue("text-align");
        Assert.assertEquals(addToCartButtonAlign, "left", "O botão 'Add to Cart' do produto Test.allTheThings() T-Shirt está desalinhado");

        WebElement cartIcon = driver.findElement(By.xpath("//div[@class='shopping_cart_container']"));
        String cartIconPosition = cartIcon.getCssValue("position");
        Assert.assertEquals(cartIconPosition, "fixed", "O ícone do carrinho não está fixado na página");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
