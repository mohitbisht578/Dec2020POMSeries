package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	By productNameHeader = By.cssSelector("div#content h1");
	By productMetdData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	By productPrice = By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
    By quantity = By.id("input-quantity");
    By addToCartBtn = By.id("button-cart");
    By productImages = By.cssSelector(".thumbnails img");
    By successMessage = By.cssSelector("div.alert.alert-success.alert-dismissible");
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}
	
	public String getProductHeaderText() {
		return elementUtil.doGetElementText(productNameHeader);
	}
	
	public Map<String, String> getProductInformation() {
		
		Map<String, String> productInfoMap = new HashMap<String, String>();
		
		productInfoMap.put("name", elementUtil.getElement(productNameHeader).getText().trim());
		
		List<WebElement> productMetaDataList = elementUtil.getElements(productMetdData);
		
		for(WebElement e : productMetaDataList) {
			
			String[] metaData = e.getText().split(":");
			String metaKey = metaData[0].trim();
			String metaValue = metaData[1].trim();
			
			productInfoMap.put(metaKey, metaValue);
		}
		
		//prices
		List<WebElement> productPriceList = elementUtil.getElements(productPrice);
		
		productInfoMap.put("price", productPriceList.get(0).getText().trim());
		productInfoMap.put("exTaxPrice", productPriceList.get(1).getText().split(":")[1].trim());
		
		return productInfoMap;
	}
	
	public int getProductImagesCount() {
		int imageCount = elementUtil.getElements(productImages).size();
		System.out.println("total product images: " + imageCount);
		return imageCount;
	}
	
	public void getQuantity(String qty) {
		System.out.println("quantityb : " + qty) ;
		elementUtil.doClear(quantity);
		elementUtil.doSendKeys(quantity, qty);
	}
	
	public void addToCart() {
		System.out.println("add to cart....");
		elementUtil.doClick(addToCartBtn);
	}
	
	public String getSucessMessage() throws InterruptedException {
		
		// elementUtil.waitForElementToBeVisible(successMessage, 10);
		Thread.sleep(2000);
		return elementUtil.doGetElementText(successMessage);
	}

}
