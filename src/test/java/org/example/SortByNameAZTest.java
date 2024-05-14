package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortByNameAZTest {
    private WebDriver driver;

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
    public void testAddToCart(String username, String password) {
        driver.get("https://www.saucedemo.com/");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username);

        WebElement sortSelect = driver.findElement(By.className("product_sort_container"));

        Select select = new Select(sortSelect);
        select.selectByValue("az");

    }

    @Test
    public void testSortingFilters() {
        driver.get("https://www.saucedemo.com/inventory.html");

        WebElement sortAZButton = driver.findElement(By.xpath("//select[@class='product_sort_container']/option[text()='Name (A to Z)']"));
        sortAZButton.click();

        List<WebElement> productNames = driver.findElements(By.className("inventory_item_name"));
        List<String> sortedNamesAZ = new ArrayList<>();
        for (WebElement name : productNames) {
            sortedNamesAZ.add(name.getText());
        }

        List<String> expectedNamesAZ = new ArrayList<>(sortedNamesAZ);
        Collections.sort(expectedNamesAZ);
        Assert.assertEquals(sortedNamesAZ, expectedNamesAZ, "A ordenação A a Z não está funcionando corretamente.");

        WebElement sortZAButton = driver.findElement(By.xpath("//select[@class='product_sort_container']/option[text()='Name (Z to A)']"));
        sortZAButton.click();

        productNames = driver.findElements(By.className("inventory_item_name"));
        List<String> sortedNamesZA = new ArrayList<>();
        for (WebElement name : productNames) {
            sortedNamesZA.add(name.getText());
        }

        List<String> expectedNamesZA = new ArrayList<>(sortedNamesZA);
        Collections.sort(expectedNamesZA, Collections.reverseOrder());
        Assert.assertEquals(sortedNamesZA, expectedNamesZA, "A ordenação Z a A não está funcionando corretamente.");

        WebElement sortPriceHighLowButton = driver.findElement(By.xpath("//select[@class='product_sort_container']/option[text()='Price (high to low)']"));
        sortPriceHighLowButton.click();

        List<WebElement> productPrices = driver.findElements(By.className("inventory_item_price"));
        List<Double> sortedPricesHighLow = new ArrayList<>();
        for (WebElement price : productPrices) {
            sortedPricesHighLow.add(Double.parseDouble(price.getText().substring(1))); // Remove o "$" do preço
        }

        List<Double> expectedPricesHighLow = new ArrayList<>(sortedPricesHighLow);
        Collections.sort(expectedPricesHighLow, Collections.reverseOrder());
        Assert.assertEquals(sortedPricesHighLow, expectedPricesHighLow, "A ordenação de Maior para Menor Preço não está funcionando corretamente.");

        WebElement sortPriceLowHighButton = driver.findElement(By.xpath("//select[@class='product_sort_container']/option[text()='Price (low to high)']"));
        sortPriceLowHighButton.click();

        productPrices = driver.findElements(By.className("inventory_item_price"));
        List<Double> sortedPricesLowHigh = new ArrayList<>();
        for (WebElement price : productPrices) {
            sortedPricesLowHigh.add(Double.parseDouble(price.getText().substring(1))); // Remove o "$" do preço
        }

        List<Double> expectedPricesLowHigh = new ArrayList<>(sortedPricesLowHigh);
        Collections.sort(expectedPricesLowHigh);
        Assert.assertEquals(sortedPricesLowHigh, expectedPricesLowHigh, "A ordenação de Menor para Maior Preço não está funcionando corretamente.");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}
