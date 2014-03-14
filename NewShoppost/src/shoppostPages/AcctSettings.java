package shoppostPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.WebElement;

public class AcctSettings {
	private int DRIVER_WAIT = 15;

	@FindBy(id = "accountMenu")
	private WebElement acctmenu;
	
	@FindBy (id = "accountButton")
	private WebElement acctBtn;
	
	@FindBy (className = "name")
	private WebElement acctName;
	
	@FindBy (id = "accountSettings")
	private WebElement acctSettings;
	
	@FindBy (id = "accountSignOut")
	private WebElement signOut;

	@FindBy (id = "navLogo")
	private WebElement logoBtn;
	
	public AcctSettings (WebDriver driver) {
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, DRIVER_WAIT);
		PageFactory.initElements(finder, this);
	}
	
	public void signOut() {
		signOut.click();
	}
	public void openAcctMenu() {
		acctBtn.click();
	}
	public void viaLogo() {
		logoBtn.click();
	}

}
