package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.Errors;


public class AccountsPageTest extends BaseTest {
	
	SoftAssert softAssert = new SoftAssert();

	@BeforeClass
	public void accSetUp() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1)
	public void getTitle() {
		String title = accountsPage.getAccountPageTitle();
		System.out.println(title);
		Assert.assertEquals(title, Constants.ACCOUNTS_PAGE_TITLE, Errors.TITLE_NOT_MATCHED_ERROR);
	}
	
	@Test(priority = 2)
	public void logoTest() {
		Assert.assertTrue(accountsPage.isLogoExists());
	}
	
	@Test(priority = 3)
	public void accPageSectionsCountTest() {
		Assert.assertEquals(accountsPage.getAccountPageHeadersCount(), Constants.ACC_PAGE_SECTIONS_COUNT, Errors.HEADER_NOT_MATCHED_ERROR);
	}
	
	@Test(priority = 4)
	public void headerListTest() {
		List<String> accSectionList = accountsPage.getAccountsSectionsList();
		Assert.assertEquals(accSectionList, Constants.expectedAccSectionsList());			
	}
	
	@Test(priority = 5)
	public void searchTest() {
		searchResultPage = accountsPage.doSearch("macbook");
		Assert.assertTrue(searchResultPage.getSearchItemResultCount()>0, Errors.SEARCH_NOT_SUCCESSFULL);
	} 
	
	@Test(priority = 6)
	public void selectProductTest() {
		searchResultPage = accountsPage.doSearch("macbook");
		productInfoPage = searchResultPage.selectProductFromResults("MacBook Pro");
		String actualHeader = productInfoPage.getProductHeaderText();
		softAssert.assertEquals(actualHeader, "MacBook Pro", Errors.PRODUCT_HEADER_NOT_FOUND);
		softAssert.assertEquals(productInfoPage.getProductImagesCount(), Errors.PRODUCT_IMAGES_COUNT_MACBOOK);
		softAssert.assertAll();
	}

}
