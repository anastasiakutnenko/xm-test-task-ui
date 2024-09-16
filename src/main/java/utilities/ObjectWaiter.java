package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ObjectWaiter {
    WebDriver driver;

    public static boolean isPresent(final Integer timeOutInSeconds, final WebDriver driver, final WebElement element) {
        try {
            untilElementVisible(timeOutInSeconds, driver, element);
            return true;
        } catch (final NotFoundException | TimeoutException exc) {
            return false;
        }
    }

    public static boolean isVisible(final Integer timeOutInSeconds, final WebDriver driver, final By element) {
        try {
            return doWebDriverWait(driver, timeOutInSeconds, ExpectedConditions.visibilityOfElementLocated(element)).isDisplayed();
        }
        catch (final NotFoundException | TimeoutException exc) {
            return false;
        }
    }

    public static void clickWhenClickable(final WebDriver driver, final WebElement element) {
        clickWhenClickable(5, driver, element);
    }

    public static void clickWhenClickable(final Integer timeOutInSeconds, final WebDriver driver, final WebElement element) {
        try {
            doWebDriverWait(driver, timeOutInSeconds, ExpectedConditions.elementToBeClickable(element)).click();
        }
        catch (final StaleElementReferenceException ex) {
            doWebDriverWait(driver, 1, ExpectedConditions.elementToBeClickable(element)).click();
        }
    }
    public static WebElement untilElementVisible(final Integer timeOutInSeconds, final WebDriver driver, final WebElement element) {
        return doWebDriverWait(driver, timeOutInSeconds, ExpectedConditions.visibilityOf(element));
    }

    private static WebElement doWebDriverWait(final WebDriver driver, final Integer timeOutInSeconds, final ExpectedCondition<WebElement> condition) {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds)).until(condition);
        } catch (final StaleElementReferenceException ex) {
            return new WebDriverWait(driver, Duration.ofSeconds(1)).until(condition);
        }
    }

    private static Boolean doWebDriverWaitBoolean(final WebDriver driver, final Integer timeOutInSeconds, final ExpectedCondition<Boolean> condition) {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds)).until(condition);
        }
        catch (final StaleElementReferenceException ex) {
            return new WebDriverWait(driver, Duration.ofSeconds(1)).until(condition);
        }
    }

    public static Boolean untilElementDisappear(final Integer timeOutInSeconds, final WebDriver driver, final WebElement element) {
        return doWebDriverWaitBoolean(driver, timeOutInSeconds, ExpectedConditions.invisibilityOf(element));
    }
}
