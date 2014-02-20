package shoppostTestSupport;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import shoppostPages.Home;
import shoppostPages.ProductCatalog;


public class SignOut {
	
	private WebDriver driver;
	private WebDriverWait wait;
	private ProductCatalog catalog;
	private Home home;
	
	
	
	
	public SignOut (WebDriver _driver){
	
		driver = _driver;
		catalog = PageFactory.initElements(driver, ProductCatalog.class);  //instantiate the pageOject
		home = PageFactory.initElements(driver, Home.class);
		
	}
	
	
	
	
	  public void logoutFromCat() throws Exception {
		wait = new WebDriverWait(driver, 10);
		catalog.openAcctMenu();
	    
	    catalog = PageFactory.initElements(driver, ProductCatalog.class);
	    
	    catalog.signOut();
	    

	    home = PageFactory.initElements(driver, Home.class);
		
		home.viaLogo();
	    
	  }
}

