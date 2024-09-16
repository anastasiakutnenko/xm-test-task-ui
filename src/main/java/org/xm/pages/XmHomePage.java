package org.xm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ObjectWaiter;

public class XmHomePage {
    private WebDriver driver;
    @FindBy(css = ".container .center-logo")
    private WebElement centerLogo;
    @FindBy(css = ".cookie-modal")
    private WebElement modalDialog;
    @FindBy(css = ".gtm-acceptDefaultCookieFirstVisit")
    private WebElement acceptDefaultCookies;
    @FindBy(css = ".main_nav_research > a")
    private WebElement researchAndEducation;
    @FindBy(css = ".menu-research > a[href = \"https://www.xm.com/au/research/economicCalendar\"]")
    private WebElement economicCalendarButton;

    public XmHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }
    public XmHomePage waitForModalDialog(){
        ObjectWaiter.isPresent(5, this.driver, modalDialog);
        return this;
    }
    public XmHomePage acceptDefaultCookies(){
        ObjectWaiter.clickWhenClickable(this.driver, acceptDefaultCookies);
        this.waitUntilAcceptCookiesModalIsClosed();
        return this;
    }

    public XmHomePage waitUntilAcceptCookiesModalIsClosed(){
        ObjectWaiter.untilElementDisappear(5, this.driver, modalDialog);
        return this;
    }
    public XmHomePage clickResearchAndEducation(){
        ObjectWaiter.clickWhenClickable(this.driver, researchAndEducation);
        waitUntilEconomicCalendarButtonAppears();
        return this;
    }
    public XmHomePage waitUntilEconomicCalendarButtonAppears(){
        ObjectWaiter.untilElementVisible(5, this.driver, economicCalendarButton);
        return this;
    }

    public EconomicCalendarPage clickEconomicCalendarButton(){
        ObjectWaiter.clickWhenClickable(this.driver, economicCalendarButton);
        return new EconomicCalendarPage(this.driver);
    }
}
