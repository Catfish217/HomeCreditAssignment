package test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import org.testng.Assert;

import pages.HomePage;
import pages.LoginPage;
import pages.NewAccountPage;
import pages.NewCustomerPage;
import pages.NewUserPage;
import pages.DepositPage;

import models.User;
import models.Customer;

public class GuruBankTest {
	
	public static void main(String[] args) {
		
		WebDriver driver =  null;
		int browserType = 0; // 0: Chrome; 1: Firefox; 2: Safari

		if (browserType == 1)
			driver = new FirefoxDriver(); // Special data format for input=date
		else if (browserType == 2)
			driver = new SafariDriver(); // Develop > Allow remote control
		else
			driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		int randomId = (int) (Math.random() * 1000);
		
		User user = new User("testuser" + randomId + "@gmail.com");
		System.out.println("User email = " + user.getEmail());
		
		Customer customer = new Customer();
		customer.setName("Phuong Dang");
		customer.setGender("female");
		customer.setAddress("Somewhere in Quang Trung str");
		customer.setCity("HCM");
		customer.setDob("01/01/2010"); // mm/dd/yyyy
		customer.setDobForFirefox("2010-01-01"); // yyyy-mm-dd
		customer.setEmail("cusomeremail" + randomId +"@email.com");
		customer.setMobile("123456789");
		customer.setPin("123456");
		customer.setState("ABC");
		customer.setPassword("123123");
		customer.setBalance(1000);
		
		System.out.println("Customer email = " + customer.getEmail());
		
		// 1. Create user
		NewUserPage newUserPage = new NewUserPage(driver);
		newUserPage.openNewUserPage();
		Assert.assertTrue(newUserPage.getPageTitleText().toLowerCase().contains("enter your email address"), "Page opened is not New User Page");
		
		newUserPage.createUser(user.getEmail());
		newUserPage.getUserAccount(user);
		
		Assert.assertNotNull(user.getUserId(), "Failed to create new user");
		System.out.println("New User has been created with userId=" + user.getUserId() + " password=" + user.getPassword());
		
		// 2. Login using created user
		LoginPage loginPage = new LoginPage(driver);
		loginPage.openLoginPage();
		
		Assert.assertTrue(loginPage.getPageTitleText().toLowerCase().contains("guru99 bank"), "Page opened is not LoginPage Page");
		
		loginPage.login(user);
		HomePage homepage = new HomePage(driver);
		
		Assert.assertTrue(homepage.getCurrentUserText().toLowerCase().contains(user.getUserId()), "Page opened is not Home Page");
		
		// 3. Create new customer
		homepage.clickNewCustomerLink();
		
		NewCustomerPage newCustomerPage = new NewCustomerPage(driver);
		Assert.assertTrue(newCustomerPage.getPageTitleText().toLowerCase().contains("add new customer"), "Page opened is not Create New Customer Page, PageTitle is " + newCustomerPage.getPageTitleText());
		
		newCustomerPage.createCustomer(customer);
		newCustomerPage.getCustomerId(customer);
		
		Assert.assertNotNull(customer.getId(), "Failed to create new customer");
		System.out.println("New customer has been created with id = " + customer.getId());
		
		// 4. Create customer account
		homepage.clickNewAccountLink();
		
		NewAccountPage newAccountPage = new NewAccountPage(driver);
		
		Assert.assertTrue(newAccountPage.getPageTitleText().toLowerCase().contains("add new account form"), "Page opened is not NewAccount page");
		
		newAccountPage.createAccount(customer);
		newAccountPage.getAccountId(customer);
		
		Assert.assertNotNull(customer.getId(), "Failed to create new Account");
		System.out.println("New Account Id = " + customer.getAccountId());
		
		// 5. Deposit
		homepage.clickDepositLinkButton();
		
		DepositPage depositPage = new DepositPage(driver);
		
		Assert.assertTrue(depositPage.getPageTitleText().toLowerCase().contains("amount deposit form"), "Page opened is not Deposit page");
		
		int oldBalance = customer.getBalance();
		int amount = 500;
		System.out.println("Balance was = " + customer.getBalance() + "; deposit amount = " + amount);
		
		depositPage.deposit(customer, amount, "Deposit money into account");
		customer.setBalance(depositPage.getNewBalance());
		
		Assert.assertTrue((oldBalance + amount) == customer.getBalance(), "Balance was not updated correctly after deposited, old balance=" + oldBalance + " amount=" + amount + " != new balance=" + customer.getBalance());	
		System.out.println("New balance = " + customer.getBalance());
		System.out.println("DONE");
	}

}
