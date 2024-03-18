package demo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CalenderDemo {

	public static void main(String[] args) {
		System.setProperty("WebDriver.Chrome.Driver","D://");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://www.path2usa.com/travel-companions");







		driver.manage().window().maximize();
		driver.findElement(By.id("form-field-travel_from")).sendKeys("Bengaluru International Airport   (BLR) Bangalore");
		driver.findElement(By.id("form-field-travel_to")).sendKeys("Chennai International Airport (MAA) Chennai");
		driver.findElement(By.id("form-field-travel_comp_date")).click();
//	elementor-field-type-date elementor-field-group elementor-column elementor-field-group-travel_comp_date elementor-col-25	
	}

}
