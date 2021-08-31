package week4.day2;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Project1Myntra {

	public static void main(String[] args) throws InterruptedException, IOException {

		//Driver manager launch and setup
		WebDriverManager.chromedriver().setup();
		//ChromeDriver driver = new ChromeDriver();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		
		driver.get("https://www.myntra.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		Thread.sleep(5000);
		
		
		//Choose Jacket from Men section
		WebElement wbMen = driver.findElement(By.xpath("//a[text()='Men']"));
		Actions builder = new Actions(driver);
		builder.moveToElement(wbMen).perform();
		System.out.println("Mouse hovered on Men");
		Thread.sleep(3000);
		
		WebElement wbJacket = driver.findElement(By.xpath("//div[@class='desktop-paneContent']//a[text()='Jackets']"));
		wbJacket.click();
		System.out.println("Clicked Jackets");
		
		
		//Checking total count and category count
		WebElement titleCount = driver.findElement(By.xpath("//span[@class='title-count']"));
		
		WebElement jCount = driver.findElement(By.xpath("//span[@class='categories-num']"));
		
		WebElement rjCount = driver.findElement(By.xpath("(//span[@class='categories-num'])[2]"));
		
		StringToInt sIntConv = new StringToInt();
		int totalCount = sIntConv.ToGetCount(titleCount);
		System.out.println(totalCount+ " is the total count.");
		
		int intJCount = sIntConv.ToGetCount(jCount);
		System.out.println(intJCount+ " is the count of Jacket");
		int intRJCount = sIntConv.ToGetCount(rjCount);
		System.out.println(intRJCount+ " is the count of Rain Jacket");
		
		int sum = intJCount + intRJCount;
		System.out.println(sum + " is the sum of Jacket and Rain Jacket count.");
		
		if(totalCount==sum) {
			System.out.println("Jackets and Rain jackets total count is matched");
		}

		else
			System.out.println("There is a count mismatch");
		
		
		
		driver.findElement(By.xpath("//div[@class='common-checkboxIndicator']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@class='brand-more']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Search brand']")).sendKeys("Duke");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//label[text()='Duke']/div")).click();
		driver.findElement(By.xpath("//span[@class='myntraweb-sprite FilterDirectory-close sprites-remove']")).click();
		
		List<WebElement> items = driver.findElements(By.xpath("//h3[text()='Duke']"));
		int total = items.size();
		System.out.println("There are "+total+" items under the selected criteria");
		
		
		for(WebElement eachItem:items) {
			boolean res;
			String brandName = eachItem.getText();
			
			if(brandName.equalsIgnoreCase("Duke")) {
				res=true;
			}
			else
				res=false;
			
			if(!res)
				System.out.println("Brand name is not Duke");
		}
		
		
		WebElement wbSort = driver.findElement(By.xpath("//div[@class='sort-sortBy']"));
		builder.moveToElement(wbSort).perform();
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//label[text()='Better Discount']")).click();
		Thread.sleep(3000);
		WebElement wb1stItem = driver.findElement(By.xpath("//span[@class='product-discountedPrice']"));
		String price = wb1stItem.getText();
		System.out.println("Price of the 1st item is:\t"+price);
		
		driver.findElement(By.xpath("//li[@class='product-base']")).click();
		
		Set<String> windowsSet = driver.getWindowHandles();
		List<String> windowsList = new ArrayList<String>(windowsSet);
		
		driver.switchTo().window(windowsList.get(1));
		
		File src = driver.getScreenshotAs(OutputType.FILE);
		File dst = new File("./snaps/myntra.png");
		FileUtils.copyFile(src, dst);
		
		driver.findElement(By.xpath("//span[text()='WISHLIST']")).click();
		
		driver.close();
		//driver.quit();
		
	}

}
