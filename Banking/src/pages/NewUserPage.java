package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.function.Function;

import models.User;

public class NewUserPage {
	
	WebDriver driver;
	
	By pageTitleLabel = By.xpath("/html/body/form/table/tbody/tr[1]/td/h2");
	By emailInput = By.name("emailid");
	By submitButton = By.name("btnLogin");
	
	By userIdLabel = By.xpath("/html/body/table/tbody/tr[4]/td[2]");
	By passwordLabel = By.xpath("/html/body/table/tbody/tr[5]/td[2]");
	
	/**
	 * Constructor
	 * @param driver
	 */
	public NewUserPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void openNewUserPage() {
		driver.get("http://demo.guru99.com");
	}
	
	public String getPageTitleText() {
		return driver.findElement(pageTitleLabel).getText().trim();
	}
	
	public void setEmail(String email) {
		driver.findElement(emailInput).sendKeys(email);
	}
	
	public void clickSubmit() {
		driver.findElement(submitButton).click();
	}
	
	public void createUser(String email) {
		this.setEmail(email);
		this.clickSubmit();
	}
	
	public void getUserAccount(User user) {
		WebElement userId = driver.findElement(userIdLabel);
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(userIdLabel))); 
		
		user.setUserId(userId.getText().trim());
		user.setPassword(driver.findElement(passwordLabel).getText().trim());
	}

}
