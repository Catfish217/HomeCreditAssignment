package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
	
	WebDriver driver;
	
	By currentUserLabel = By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[3]/td");
	By newCustomerLinkButton = By.xpath("/html/body/div[3]/div/ul/li[2]/a"); 
	By newAccountLinkButton = By.xpath("/html/body/div[3]/div/ul/li[5]/a");
	By depositLinkButton = By.xpath("/html/body/div[3]/div/ul/li[8]/a");
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getCurrentUserText() {
		return driver.findElement(currentUserLabel).getText().trim();
	}
	
	public void clickNewCustomerLink() {
		driver.findElement(newCustomerLinkButton).click();
	}
	
	public void clickNewAccountLink() {
		driver.findElement(newAccountLinkButton).click();
	}
	
	public void clickDepositLinkButton() {
		driver.findElement(depositLinkButton).click();
	}

}
