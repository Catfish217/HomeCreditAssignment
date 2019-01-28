package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import models.Customer;

public class NewAccountPage {

	WebDriver driver;
	By pageTitleLabel = By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[1]/td/p");
	
	By customerIdInput = By.name("cusid");
	By initDepositInput = By.name("inideposit");
	By submitButton = By.name("button2");
	
	By accountIdLabel = By.xpath("//*[@id=\"account\"]/tbody/tr[4]/td[2]");
						
	
	public NewAccountPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getPageTitleText() {
		return driver.findElement(pageTitleLabel).getText().trim();
	}
	
	public void createAccount(Customer customer) {
		driver.findElement(customerIdInput).sendKeys(customer.getId());
		driver.findElement(initDepositInput).sendKeys(customer.getBalance() + "");
		driver.findElement(submitButton).click();
	}
	
	public void getAccountId(Customer customer) {
		String accountId = driver.findElement(accountIdLabel).getText().trim();
		customer.setAccountId(accountId);
	}
}
