package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

public class ProductInfoPageTest extends BaseTest {
	SoftAssert softAssert = new SoftAssert();
	
	@BeforeClass
	public void accSetUp() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void productInfoTest() {
		searchResultPage = accountsPage.doSearch("MacBook");
		productInfoPage = searchResultPage.selectProductFromResults("MacBook Pro");
		Map<String, String> actProductInfoMap = productInfoPage.getProductInformation();
		softAssert.assertTrue(actProductInfoMap.get("name").equals("MacBook Pro"));
		softAssert.assertTrue(actProductInfoMap.get("Brand").equals("Apple"));
		softAssert.assertTrue(actProductInfoMap.get("price").equals("$2,000.00"));
				
	}
	
	@Test
	public void addToCartTest() throws InterruptedException {
		searchResultPage = accountsPage.doSearch("MacBook");
		productInfoPage = searchResultPage.selectProductFromResults("MacBook Pro");
		productInfoPage.getQuantity("2");
		productInfoPage.addToCart();
		String actualMessage = productInfoPage.getSucessMessage();
		System.out.println(actualMessage);
		Assert.assertTrue(actualMessage.contains(Constants.SUCCESS_MESSAGE));
	}
	
	
}
