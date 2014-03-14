package shoppostPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.WebElement;

public class Shopify {
	private int DRIVER_WAIT = 15;
	private WebDriver _driver;
	
	@FindBy (id = "sidebar")
	private WebElement sidebarMenu;

	@FindBy (xpath = "//div[@id='sidebar']/nav[1]/ol[1]/li/a[contains(@class,'products')]")
	private WebElement sidebarProducts;
	
	@FindBy (xpath = "//div[@id='sidebar']/nav[1]/ol[1]/li/a[contains(@class,'dashboard')]")
	private WebElement sidebarDashboard;
	
	@FindBy (xpath = "//div[@id='sidebar']/nav[1]/ol[2]/li/a[contains(@class,'apps')]")
	private WebElement sidebarApps;
	
	@FindBy (xpath = "//table[@id='all-products']/tbody/tr[1]/td[@class='name']")
	private WebElement firstProductName;
	@FindBy (xpath = "//table[@id='all-products']/tbody/tr[2]/td[@class='name']")
	private WebElement secondProductName;
	@FindBy (xpath = "//table[@id='all-products']/tbody/tr[3]/td[@class='name']")
	private WebElement thirdProductName;
	@FindBy (xpath = "//table[@id='all-products']/tbody/tr[4]/td[@class='name']")
	private WebElement fourthProductName;
	
	@FindBy (xpath = "//li[@class='apps-dropdown']/a[@class='btn']")
	private WebElement ellipsisButton;
	@FindBy (xpath = "//div[@class='app-icon-list']/a")
	private WebElement shoppostApp;
	

	public Shopify (WebDriver driver) {
		_driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, DRIVER_WAIT);
		PageFactory.initElements(finder, this);
	}
	

	
	
	public void toProductList() {
		sidebarProducts.click();
	}
	public void toApps() {
		sidebarApps.click();
	}
	public void toDashboard() {
		sidebarDashboard.click();
	}
	public String openProduct(String n) {
		String _name = "";
		switch(n) {
		case "1":
			_name = firstProductName.getText();
			firstProductName.click();
			break;
		case "2":
			_name = secondProductName.getText();
			secondProductName.click();
			break;
		case "3":
			_name = thirdProductName.getText();
			thirdProductName.click();
			break;
		case "4":
			_name = fourthProductName.getText();
			fourthProductName.click();
			break;
		}
		return _name;
	}
	public void clickEllipsis() {
		ellipsisButton.click();
	}
	public void activateShoppostApp() {
		shoppostApp.click();
	}

}
