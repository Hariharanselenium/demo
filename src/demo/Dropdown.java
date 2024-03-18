package demo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Dropdown {

	public static void main(String[] args) throws InterruptedException {
//		System set property
		System.setProperty("WebDriver.Chrome.Driver", "D://");
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
// Static Dropdown box
		WebElement staticdropdown=driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));
        Select dropdown =new Select(staticdropdown);
        dropdown.selectByVisibleText("USD");
        dropdown.selectByIndex(2);
        dropdown.selectByValue("INR");
        System.out.println(dropdown.getFirstSelectedOption().getText());
//  looping concept
        driver.findElement(By.id("divpaxinfo")).click();
       Thread.sleep(3000) ;
        for(int i=1;i<5;i++) {
        	driver.findElement(By.id("hrefIncAdt")).click();
        }
       driver.findElement(By.id("btnclosepaxoption")).click();
       System.out.println(driver.findElement(By.id("divpaxinfo")).getText());
//   Dynamic dropdown
       Thread.sleep(3000);
       driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
       driver.findElement(By.cssSelector("a[text='Chennai (MAA)']")).click();
       Thread.sleep(3000);
       driver.findElement(By.xpath("//div[@id='ctl00_mainContent_ddl_destinationStation1_CTNR']//a[@text='Goa (GOI)']")).click();
//   Auto suggestive
       Thread.sleep(2000);
       driver.findElement(By.id("autosuggest")).sendKeys("ind");
       Thread.sleep(2000);
       List<WebElement> options =driver.findElements(By.cssSelector("li[class='ui-menu-item'] a"));
       for(WebElement option :options)
       {
    	   if(option.getText().equalsIgnoreCase("India"))
    	   {
    		   option.click();
    		   break;
    	   }
       }
	}

}
