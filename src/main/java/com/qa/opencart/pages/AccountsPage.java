package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	private WebDriver driver;
	private ElementUtil elementUtil;

	By logo = By.cssSelector("div#logo a");
	By accountSectionsHeaders = By.cssSelector("div#content h2");
	By searchText = By.cssSelector("div#search input[name='search']");
	By searchButton = By.cssSelector("div#search button[type='button']");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}

	public String getAccountPageTitle() {
		return elementUtil.waitForPageTitleIs(Constants.ACCOUNTS_PAGE_TITLE, 5);
	}

	public boolean isLogoExists() {
		return elementUtil.doIsDisplayed(logo);
	}
	
	public int getAccountPageHeadersCount() {
		return elementUtil.getElements(accountSectionsHeaders).size();
	}

	public List<String> getAccountsSectionsList() {

		List<String> accSectionList = new ArrayList<String>();
		List<WebElement> accList = elementUtil.getElements(accountSectionsHeaders);
		 
		for(WebElement e : accList) {
			String section = e.getText();
			System.out.println(section);
			accSectionList.add(section);
		}
		
		return accSectionList;
}
	
	public SearchResultPage doSearch(String productName) {
		elementUtil.doClear(searchText);
		System.out.println("searching for the product : " + productName);
		elementUtil.doSendKeys(searchText, productName);
		elementUtil.doClick(searchButton);
		
		return new SearchResultPage(driver);
		
	}
	


}
