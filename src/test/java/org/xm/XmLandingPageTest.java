package org.xm;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.xm.pages.XmHomePage;

public class XmLandingPageTest {
    WebDriver driver;

    @BeforeTest
    public void setUp() {
        WebDriverManager.safaridriver().setup();
        this.driver = new SafariDriver();
        this.driver.manage().window().maximize();
    }

    @Test
    public void checkXmHomePage() {
        this.driver.get("https://www.xm.com/au/");
        XmHomePage homePage = new XmHomePage(this.driver);
        homePage
                .waitForModalDialog()
                .acceptDefaultCookies()
                .clickResearchAndEducation()
                .clickEconomicCalendarButton();
    }
    @AfterTest
    public void tearDown() {
        this.driver.quit();
    }
}
