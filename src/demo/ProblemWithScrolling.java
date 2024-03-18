package demo;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ProblemWithScrolling {

	public static void main(String[] args) {
		System.setProperty("WebDriver.Chrome.Driver","D://");
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		JavascriptExecutor je =(JavascriptExecutor)driver;
		je.executeScript("window.scrollBy(0,660)");
		List <WebElement> val=driver.findElements(By.cssSelector(".table-display td:nth-child(3)"));
		int sum =0;
		for(int i=0;i<val.size();i++)
			sum=sum+Integer.parseInt(val.get(i).getText());
		System.out.println(sum);	
		

	}

}
