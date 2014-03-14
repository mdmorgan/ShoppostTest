package shoppostPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.WebElement;

public class ShopifyLogin {
	private int DRIVER_WAIT = 15;
	
	@FindBy (id = "subdomain")
	private WebElement storeNameField;

	@FindBy (id = "login")
	private WebElement emailField;
	
	@FindBy (id = "password")
	private WebElement passwordField;
	
	@FindBy (id = "remember")
	private WebElement rememberCheck;
	
	@FindBy (xpath = "//form[@id='shopify-login-form']/input[contains(@class,'button-primary')]")
	private WebElement loginButton;
	

	public ShopifyLogin (WebDriver driver) {
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, DRIVER_WAIT);
		PageFactory.initElements(finder, this);
	}

	
	
	public void login(String storeName, String email, String password) {
		storeNameField.sendKeys(storeName);
		emailField.sendKeys(email);
		passwordField.sendKeys(password);
		loginButton.click();
	}

}
