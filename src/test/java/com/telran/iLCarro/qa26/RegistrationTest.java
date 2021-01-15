package com.telran.iLCarro.qa26;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTest {
    WebDriver wd;

    @BeforeMethod
    public void setUp() {
        wd = new FirefoxDriver();
        wd.get("https://ilcarro-dev-v1.firebaseapp.com");
    }

    @Test


    public void testRegistration() throws InterruptedException {

        openRegForm();
        String email = "hi" + System.currentTimeMillis() % 3600 + "@gmail.com";
        fillRegistrationForm("Al", "Br", email, "Ab1234567");
        selectCheckBox();
        pause();
        clickOnYallaButton();
        Assert.assertTrue(isRegistrationFormPresent());
    }
    @Test
    public void testRegistrationNegative() throws InterruptedException { //empty fild fName

        openRegForm();
        String email = "hi" + System.currentTimeMillis() % 3600 + "@gmail.com";
        fillRegistrationForm("", "Br", email, "Ab1234567");
        selectCheckBox();
        pause();
        clickOnYallaButton();
        Assert.assertTrue(isRegistrationFormPresent());
    }

    public void clickOnYallaButton() {
        click(By.cssSelector(".yalla_yalla__1Jxk6[type=\"submit\"]"));
    }

    public boolean isRegistrationFormPresent() {
        return wd.findElements(By.cssSelector("[href$='/login']")).size() >= 0;
    }

    public void pause() throws InterruptedException {
        Thread.sleep(2000);
    }

    public void selectCheckBox() {
        click(By.id("check_policy"));
    }

    public void fillRegistrationForm(String fName, String lName, String email, String password) {

        type(By.id("first_name"), fName);
        type(By.id("second_name"), lName);
        type(By.id("email"), email);


        System.out.println("email : " + email);
        type(By.id("password"), password);
    }

    public void openRegForm() {
        click(By.cssSelector("[href='/signup']"));
    }

    public void type(By locator, String text) {
        click(locator);
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
    }

    public void click(By locator) {
        wd.findElement(locator).click();
    }

    @AfterMethod
    public void tearDown() {

    }
}
