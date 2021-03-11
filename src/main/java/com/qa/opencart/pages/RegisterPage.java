package com.qa.opencart.pages;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	private By firstname = By.id("input-firstname");
	private By lastname = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");
	
    private By subscribeYes = By.cssSelector("label.radio-inline:nth-of-type(1) input");
    private By subscribeNo = By.cssSelector("label.radio-inline:nth-of-type(2) input");
	
	private By agreeCheckbox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value = 'Continue']");
	
	private By accountSuccessMessg = By.cssSelector("#content h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}
	
	public String getRandomNumber() {
		Random randomGenerator = new Random();
		String email = "testautomation" + randomGenerator.nextInt(1000) + "@gmail.com";
		return email;
	}
	
	public boolean accountRegistration(String firstname, String lastname, 
			String email, String telephone, String password,
			String subscribe) {

	   // email = "automation" + getRandomNumber() + "@gmail.com";
		
		elementUtil.doSendKeys(this.firstname, firstname);
		elementUtil.doSendKeys(this.lastname, lastname);
	    elementUtil.doSendKeys(this.email, email);          //tom9898@gmail.com
		elementUtil.doSendKeys(this.telephone, telephone);
		elementUtil.doSendKeys(this.password, password);
		elementUtil.doSendKeys(this.confirmpassword, password);

		if (subscribe.equals("yes")) {
			elementUtil.doClick(subscribeYes);
		} else {
			elementUtil.doClick(subscribeNo);
		}

		elementUtil.doClick(agreeCheckbox);
		elementUtil.doClick(continueButton);
		
		elementUtil.waitForPresenceOfElement(this.accountSuccessMessg, 5);
		
		String mesg = elementUtil.doGetElementText(accountSuccessMessg);
		
		if(mesg.contains(Constants.ACCOUNT_SUCCESS_MESSG)) {
			elementUtil.doClick(logoutLink);
			elementUtil.doClick(registerLink);

			return true;
		}
		return false;
	}
	
	
}
