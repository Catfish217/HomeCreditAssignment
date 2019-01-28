package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import models.Customer;

public class DepositPage {

	WebDriver driver;
	
	By pageTitleLabel = By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[1]/td/p");
	By accountNoInput = By.name("accountno");
	By amountInput = By.name("ammount");
	By descInput = By.name("desc");
	By submitButton = By.name("AccSubmit");
	
	By newBalance = By.xpath("//*[@id=\"deposit\"]/tbody/tr[23]/td[2]");
	
	public DepositPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getPageTitleText() {
		return driver.findElement(pageTitleLabel).getText().trim();
	}
	
	public void deposit(Customer customer, int amount, String desc) {
		driver.findElement(accountNoInput).sendKeys(customer.getAccountId());
		driver.findElement(amountInput).sendKeys(amount + "");
		driver.findElement(descInput).sendKeys(desc);
		driver.findElement(submitButton).click();
	}
	
	public int getNewBalance() {
		return Integer.parseInt(driver.findElement(newBalance).getText());
	}
	
	
}
