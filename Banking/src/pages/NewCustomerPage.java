package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import models.Customer;

public class NewCustomerPage {

	WebDriver driver;
	By pageTitleLabel = By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[1]/td/p");

	By nameInput = By.name("name");
	By dobInput = By.name("dob");
	By addrInput = By.name("addr");
	By cityInput = By.name("city");
	By stateInput = By.name("state");
	By pinInput = By.name("pinno");
	By mobileInput = By.name("telephoneno");
	By emailInput = By.name("emailid");
	By passwordInput = By.name("password");
	By submitButton = By.name("sub");

	By customerIdLabel = By.xpath("//*[@id=\"customer\"]/tbody/tr[4]/td[2]");

	public NewCustomerPage(WebDriver driver) {
		this.driver = driver;
	}

	public String getPageTitleText() {
		return driver.findElement(pageTitleLabel).getText().trim();
	}

	public void createCustomer(Customer customer) {
		driver.findElement(nameInput).sendKeys(customer.getName());
		if (driver instanceof FirefoxDriver)
			driver.findElement(dobInput).sendKeys(customer.getDobForFirefox());
		else 
			driver.findElement(dobInput).sendKeys(customer.getDob());
		driver.findElement(addrInput).sendKeys(customer.getAddress());
		driver.findElement(cityInput).sendKeys(customer.getCity());
		driver.findElement(stateInput).sendKeys(customer.getState());
		driver.findElement(pinInput).sendKeys(customer.getPin());
		driver.findElement(mobileInput).sendKeys(customer.getMobile());
		driver.findElement(emailInput).sendKeys(customer.getEmail());
		driver.findElement(passwordInput).sendKeys(customer.getPassword());
		driver.findElement(submitButton).click();
	}

	public void getCustomerId(Customer customer) {
		String customerId = driver.findElement(customerIdLabel).getText().trim();
		customer.setId(customerId);
	}
}
