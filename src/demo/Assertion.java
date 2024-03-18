package demo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assertion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
	       WebDriver driver =new ChromeDriver();
	       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	       driver.manage().window().fullscreen();	      
	       driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
	       Assert.assertFalse( driver.findElement(By.id("ctl00_mainContent_chk_friendsandfamily")).isSelected());
	      Assert.assertFalse(false);
	       driver.findElement(By.id("ctl00_mainContent_chk_friendsandfamily")).click();
	       Assert.assertTrue(driver.findElement(By.id("ctl00_mainContent_chk_friendsandfamily")).isSelected());
	}

}
