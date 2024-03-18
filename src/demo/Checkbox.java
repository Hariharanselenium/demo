package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Checkbox {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("WebDriver.Chrome.Driver", "D://");
	       WebDriver driver =new ChromeDriver();
	       ChromeDriver driver1= new ChromeDriver();
	       driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
	       System.out.println( driver.findElement(By.id("ctl00_mainContent_chk_friendsandfamily")).isSelected());
	       driver.findElement(By.id("ctl00_mainContent_chk_friendsandfamily")).click();
	       System.out.println( driver.findElement(By.id("ctl00_mainContent_chk_friendsandfamily")).isSelected());
	      
	}

}
