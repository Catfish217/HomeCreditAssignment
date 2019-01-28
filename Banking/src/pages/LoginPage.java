package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import models.User;

public class LoginPage {
	
	WebDriver driver;
	
	By pageTitleLabel = By.className("barone");
	By userIdInput = By.name("uid");
	By passwordInput = By.name("password");
	By loginButton = By.name("btnLogin");
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void setUserId(String userId) {
		driver.findElement(userIdInput).sendKeys(userId);
	}
	
	public void setPassword(String password) {
		driver.findElement(passwordInput).sendKeys(password);
	}
	
	public void clickLoginButton() {
		driver.findElement(loginButton).click();
	}
	
	public void openLoginPage() {
		driver.get("http://demo.guru99.com/v4/");
	}
	
	public String getPageTitleText() {
		return driver.findElement(pageTitleLabel).getText().trim();
	}
	
	public void login(User user) {
		this.setUserId(user.getUserId());
		this.setPassword(user.getPassword());
		this.clickLoginButton();
	}

}
