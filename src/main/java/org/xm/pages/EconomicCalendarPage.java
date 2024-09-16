package org.xm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ObjectWaiter;

public class EconomicCalendarPage {
    private WebDriver driver;
    @FindBy(css = "mat-calendar .mat-calendar-content")
    private WebElement economicCalendar;

    public EconomicCalendarPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        waitUntilEconomicCalendarAppears();
    }
    public EconomicCalendarPage waitUntilEconomicCalendarAppears(){
        ObjectWaiter.isPresent(5, this.driver, economicCalendar);
        return this;
    }
}
