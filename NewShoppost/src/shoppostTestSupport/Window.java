package shoppostTestSupport;


import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import shoppostPages.ShareModal;




public class Window {
	
	private WebDriver driver;
	private ShareModal shareModal;
	
	
	
	public Window (WebDriver _driver){
		
		driver = _driver;
		shareModal = PageFactory.initElements(driver, ShareModal.class);  //instantiate the pageObject
		
	}
	
	
public String changeWindowForShare(Set beforePop) throws Exception {
		
		//Set beforePopup = driver.getWindowHandles();   //collect windows before the popup
		  
		//driver.findElement(by).click();   //click the button to open a new window
		
		Thread.sleep(2000);
		  
		Set afterPopupList = driver.getWindowHandles(); //collect windows after popup
		
		//afterPopupList.removeAll(beforePopup);  //this removes all windows from the list except the latest popup
		System.out.println(beforePop);
		System.out.println(afterPopupList);

		//if(afterPopupList.size()==1) { driver.switchTo().window((String) afterPopupList.toArray()[0]); }
		//if(afterPopupList.size()==1) {  return (String) afterPopupList.toArray()[0]; }
		//else {
		//	return "error";
		//}
		//System.out.println(beforePopup);
		//System.out.println(afterPopupList);
		//afterPopupList.removeAll(beforePopup);  //remove the handles from before popup
		//System.out.println(afterPopupList);
		//System.out.println(afterPopupList.size());
		if (afterPopupList.size() == 2) {
			  //System.out.println("window: "+(String)afterPopupList.toArray()[1]);
			  return (String)afterPopupList.toArray()[1];  //return the new window for the switch
		  }else {
			  //System.out.println("window: "+(String)afterPopupList.toArray()[0]);
			  return (String)afterPopupList.toArray()[0];  //return the initial window so no change
		  }

		
		
			  
	}
	
	
	
	public String changeWindow(By by) throws Exception {
		
		Set beforePopup = driver.getWindowHandles();   //collect windows before the popup
		  
		driver.findElement(by).click();   //click the button to open a new window
		
		Thread.sleep(5000);
		  
		Set afterPopupList = driver.getWindowHandles(); //collect windows after popup
		
		//afterPopupList.removeAll(beforePopup);  //this removes all windows from the list except the latest popup
		System.out.println(beforePopup);
		System.out.println(afterPopupList);

		//if(afterPopupList.size()==1) { driver.switchTo().window((String) afterPopupList.toArray()[0]); }
		//if(afterPopupList.size()==1) {  return (String) afterPopupList.toArray()[0]; }
		//else {
		//	return "error";
		//}

		
		
		
		//System.out.println(beforePopup);
		//System.out.println(afterPopupList);
		//afterPopupList.removeAll(beforePopup);  //remove the handles from before popup
		//System.out.println(afterPopupList);
		//System.out.println(afterPopupList.size());
		if (afterPopupList.size() == 2) {
			  //System.out.println("window: "+(String)afterPopupList.toArray()[1]);
			  return (String)afterPopupList.toArray()[1];  //return the new window for the switch
		  }else {
			  //System.out.println("window: "+(String)afterPopupList.toArray()[0]);
			  return (String)afterPopupList.toArray()[0];  //return the initial window so no change
		  }

		
		
			  
	}
	
	public String changeRemoveWindow(By by) throws Exception {
		
		Set beforePopup = driver.getWindowHandles();   //collect windows before the popup
		  
		driver.findElement(by).click();   //click the button to open a new window
		
		Thread.sleep(2000);
		  
		Set afterPopupList = driver.getWindowHandles(); //collect windows after popup
		
		afterPopupList.removeAll(beforePopup);  //this removes all windows from the list except the latest popup
		System.out.println(beforePopup);
		System.out.println(afterPopupList);

		//if(afterPopupList.size()==1) { driver.switchTo().window((String) afterPopupList.toArray()[0]); }
		if(afterPopupList.size()==1) {  return (String) afterPopupList.toArray()[0]; }
		else {
			return "error";
		}

		
		
		
		
			  
	}
	
}
