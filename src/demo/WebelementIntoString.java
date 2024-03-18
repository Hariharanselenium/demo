package demo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class WebelementIntoString {

	public static void main(String[] args) {
		System.setProperty("WebDriver.Chrome.Driver","D://");
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
		driver.findElement(By.xpath("//tr//th[1]")).click();
		List<WebElement> elements=driver.findElements(By.xpath("//tr//td[1]"));
		List<String> originalList=elements.stream().map(s->s.getText()).collect(Collectors.toList());
		List<String> sortedList=originalList.stream().sorted().collect(Collectors.toList());
		Assert.assertTrue(originalList.equals(sortedList));
		 List<String> money;
	     money=elements.stream().filter(s->s.getText().contains("Beans"))
			   .map(s->price(s)).collect(Collectors.toList());
	   money.forEach(a->System.out.println(a));
	}

	private static  String price(WebElement s) {
		
		return s.findElement(By.xpath("following-sibling::td[1]")).getText();
	}

}
