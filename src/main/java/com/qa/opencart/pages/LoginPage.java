package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	//Page Objects - By loctors - OR
	private By username = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.xpath("//input[@value='Login']");
	private By forgotPwd= By.xpath("//div[@class='form-group']/a[text()='Forgotten Password']");
	private By registerLink = By.linkText("Register");
	
	//constructor - why we need this constructor
	//when someone is creating the object of login page class constructor it is compulsory to give the driver same driver will be used page actions
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}
	
	//page actions/methods
	public String getLoginPageTitle() {
		return elementUtil.waitForPageTitleIs(Constants.LOGIN_PAGE_TITLE, 5);
	}
	
	public boolean isForgotPwdLinkExists() {
		return elementUtil.doIsDisplayed(forgotPwd);
				
	}
	
	public AccountsPage doLogin(String un, String pwd) {
		System.out.println("login with: " + un);
		elementUtil.doSendKeys(username, un);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginButton);
		
		return new AccountsPage(driver);
	}

	public RegisterPage navigateToRegisterPage() {
		elementUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}
}
