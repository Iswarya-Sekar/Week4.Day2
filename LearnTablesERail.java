package week4.day2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LearnTablesERail {

	public static void main(String[] args) {
		
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://erail.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
		WebElement from = driver.findElement(By.id("txtStationFrom"));
		from.clear();
		from.sendKeys("MS");
		from.sendKeys(Keys.ENTER);
		
		WebElement to = driver.findElement(By.id("txtStationTo"));
		to.clear();
		to.sendKeys("MDU");
		to.sendKeys(Keys.ENTER);
		
		driver.findElement(By.id("chkSelectDateOnly")).click();
		
		
		WebElement table = driver.findElement(By.xpath("//table[@class='DataTable TrainList TrainListHeader']"));
		List<WebElement> trains = table.findElements(By.tagName("tr"));
		int rCount = trains.size();
		System.out.println("No of trains listed:\t" +rCount);
		
		List<String> tNames = new ArrayList<String>();
		
		System.out.println("***********All Train Names*************");
		System.out.println("  S. No\t\tTrain Name");
		
		
		for(int i=0; i<rCount; i++) {
			WebElement eachRow = trains.get(i);
			List<WebElement> allValues = eachRow.findElements(By.tagName("td"));
			String name = allValues.get(1).getText();
			tNames.add(name);
			int c = i+1;
			System.out.println("  " + c +"\t\t   " +name);
			
		}
		
		Collections.sort(tNames);
		System.out.println("");
		
		System.out.println("***********Sorted Train Names*************");
		System.out.println("  S. No\t\tTrain Name");
		
		for (String string : tNames) {
			int i = tNames.indexOf(string);
			i++;
			System.out.println("  " +i+ "\t\t" +string);
		}
		
		
		driver.quit();

	}

}
