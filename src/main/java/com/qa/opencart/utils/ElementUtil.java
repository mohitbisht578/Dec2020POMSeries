package com.qa.opencart.utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {

	// private WebDriver driver;
	public WebDriver driver;
	public Actions action; 
	public JavaScriptUtil jsUtil;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		action = new Actions(this.driver);
		jsUtil = new JavaScriptUtil(this.driver);
	}

	public WebElement getElement(By locator) {
		WebElement ele = driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlight)) {
			jsUtil.flash(ele);
		}
		return ele;
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public void doSendKeys(By locator, String value) {
		getElement(locator).clear();
		getElement(locator).sendKeys(value);
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public void doClear(By locator) {
		getElement(locator).clear();
	}
	
	public String doGetElementText(By locator) {
		return getElement(locator).getText();
	}

	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	public void doLinkClick(By locator, String value) {

		// List<WebElement> langList =
		// driver.findElements(By.xpath("//div[@id='SIvCob']/a"));
		List<WebElement> linksList = getElements(locator);

		System.out.println("total language links in the page are : " + linksList.size());

		for (WebElement e : linksList) {
			String text = e.getText();
			System.out.println(text);
			if (text.equals(value)) {
				e.click();
				break;

			}
		}
	}

	// **************************Drop down Utils*************************//

	public void doSelectByVisibleText(By locator, String text) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);
	}

	public void doSelectByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}

	public void doSelectByVisibleText(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	public void doSelectByTextOption(By locator, String text) {

		Select select = new Select(getElement(locator));

		List<WebElement> optionsList = select.getOptions();

		for (WebElement e : optionsList) {
			if (e.getText().equals(text)) {
				e.click();
				break;
			}
		}
	}

	/**
	 * this method used to select dropdown without select class
	 * 
	 * @param locator
	 * @param value
	 */

	public void doSelectDropDownWithoutSelectClass(By locator, String value) {

		List<WebElement> optionsList = getElements(locator);

		for (WebElement e : optionsList) {
			if (e.getText().equals(value)) {
				e.click();
				break;
			}
		}
	}

	// **************************Actions Class Utils*************************//

	/**
	 * 
	 * action class util
	 */
	// 2 level menu and sub menu
	public void doMoveToElement(By locator) {
		action.moveToElement(getElement(locator)).perform();
		;
	}

	// 3 level
	public void doMoveToElement(By locator1, By locator2) {
		action.moveToElement(getElement(locator1)).perform();
		action.moveToElement(getElement(locator2)).perform();
	}

	// 3 level with click
	public void doMoveToElement(By locator1, By locator2, By locator3) {
		action.moveToElement(getElement(locator1)).perform();
		action.moveToElement(getElement(locator2)).perform();
		// locator 3 for click
		// doClick();
	}

	public void doActionsClick(By locator) {
		action.click(getElement(locator)).perform();
	}

	public void doActionsSendKeys(By locator, String value) {
		action.sendKeys(getElement(locator), value).perform();
	}

	
	///calendar//
	
	
//	public static void selectDate(String day, String tagName) {
//		String xpath = "//" + tagName + "[text()='"+day+"']"));
//		//driver.findElement(By.xpath("//"+tagName+"[text()='"+day+"']")).click();
//	}
//	
	
	//collect spanb feb
	//clicking american airlines
	
	
	// **************************** Wait Utils ***************************************//
	
	public Alert waitForAlertToBePresent(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public String getAlertText(int timeOut) {
		String text = waitForAlertToBePresent(timeOut).getText();
		acceptAlert(timeOut);
		return text;
	}
	
	public void acceptAlert(int timeOut) {
		waitForAlertToBePresent(timeOut).accept();
	}
	
	public void dismissAlert(int timeOut) {
		waitForAlertToBePresent(timeOut).dismiss();
	}
	
	public String waitForPageTitlePresent(String titleValue, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.titleContains(titleValue));
		return driver.getTitle();
	}
	
	public String waitForPageTitleIs(String titleValue, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.titleIs(titleValue));
		return driver.getTitle();
	}
	
	public Boolean waitForTitle(String titleValue, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.titleIs(titleValue));
	}
	
	public WebElement waitForPresenceOfElement(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	/*
	 * An expectation for checking that an element
	 * known to be present on the DOM of a page, is visible. 
	 * Visibility means that the element is not only displayed 
	 * but also has a height and width that is greater than 0.
	 */
	
	public WebElement waitForVisibleOfElement(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.visibilityOf(getElement(locator)));
	}
	
	public List<WebElement> waitForvisibilityOfAllElements(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public void printElementLinksTexts(By locator, int timeOut) {
		waitForvisibilityOfAllElements(locator, timeOut).stream().forEach(ele -> System.out.println(ele.getText()));
	}
	
	public void getPageLinksText(By locator, int timeOut){
		waitForvisibilityOfAllElements(locator, timeOut).stream().forEach(ele -> System.out.println(ele.getText()));
	}
	
	public WebElement waitForElementToBeClickable(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
	    return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public void clickWhenReady(By locator, int timeOut) {
		waitForElementToBeClickable(locator, timeOut).click();
	}
	
	public String getElementAttribute(By locator, int timeOut, String name) {
		return waitForElementToBeClickable(locator, timeOut).getAttribute(name);
	}

	public Boolean waitForElementTextToBe(By locator, int timeOut, String value) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
	    return wait.until(ExpectedConditions.textToBe(locator, value));
	}
	
	public WebElement waitForElementToBeLocated(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement waitForElementToBeVisible(By locator, int timeOut) {
		WebElement element = getElement(locator);
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public boolean waitForUrl(String url, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.urlContains(url));
	}
	
	public boolean waitForUrlToBe(String url, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.urlToBe(url));
	}
	
	//frame
	
	public void waitForFrameToBeAvailable(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}
	
	public void waitForFrameToBeAvailable(String nameorId, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameorId));
	}
	
	public void waitForFrameToBeAvailable(int frameIndex, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}

	public WebElement waitForElementWithFluentWait(By locator, int timeOut, int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchElementException.class);

		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
}
