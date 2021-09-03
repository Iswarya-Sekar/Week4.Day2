package week4.day2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Snapdeal {

	public static void main(String[] args) throws InterruptedException, IOException {


		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Mouse hover Men's Fashion
		WebElement mens = driver.findElement(By.xpath("(//span[contains(text(),'Men')])[2]"));
		Actions builder = new Actions(driver);
		builder.moveToElement(mens).perform();
		Thread.sleep(1000);
		
		//Click on Sports shoes
		WebElement sportMens = driver.findElement(By.xpath("//span[text()='Sports Shoes']"));
		sportMens.click();
		
		//Total shoes
		WebElement str = driver.findElement(By.xpath("//span[@class='category-count']"));
		StringToInt objStr = new StringToInt();
		int nCount = objStr.ToGetCount(str);
		System.out.println("Total sports shoes:\t"+nCount);
		
		//Click on Training shoes
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		
		//Sort with low to high
		driver.findElement(By.xpath("//i[contains(@class,'expand-arrow sort-arrow')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//ul[@class='sort-value']/li[2]")).click();
        Thread.sleep(5000);
        
		WebElement end = driver.findElement(By.xpath("//span[@class='btn-yes js-yesFeedback']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", end);
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0, -700)");
		
		Thread.sleep(5000);
		
		driver.findElement(By.xpath("//i[@class='back-to-top-icon backTopImg']")).click();
		
		Thread.sleep(5000);
		
		
		//Check whether the items are sorted
		List<WebElement> shoes = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));
		List<Integer> value = new ArrayList<Integer>();
		
		for(int i=0; i<shoes.size(); i++) {
			String text = shoes.get(i).getText();
			String text1 = text.replaceAll("[^0-9]", "");
			int dPrice = Integer.parseInt(text1);
			value.add(dPrice);
		}
		
		System.out.println("No of Training shoes:\t" +value.size());
		System.out.println(value);
		
		Integer[] valArray = new Integer[value.size()];
		 valArray = value.toArray(valArray);
		 
		 ArrayFunctions checkSort = new ArrayFunctions();
		 boolean result = checkSort.isSorted(valArray);
		 if(result) {
				System.out.println("Items are Sorted");
			}
			
			else {
				System.out.println("Items are Not sorted");
				
			}
				
		
		/*
		Integer min = Collections.min(value); //returns min val in list
		Integer comValue = value.get(0);
		if(comValue==min) {
			System.out.println("Sorted");
		}
		
		else
			System.out.println("Not sorted");
		*/
		
		 
		 
		 //8. Mouseover on 'ASIAN Black Training Shoes' (instead of Puma as it is not present)
		 WebElement selectShoe = driver.findElement(By.xpath("//img[@title='ASIAN Black Training Shoes']"));
		 builder.moveToElement(selectShoe).perform();
		 
		 //9. click QuickView button
		 WebElement quickView = driver.findElement(By.xpath("//img[@title='ASIAN Black Training Shoes']/following::div/div"));
		 builder.moveToElement(quickView).click().perform();
		 
		 //10. Print the cost and the discount percentage
		 String price = driver.findElement(By.xpath("//div[contains(@class,'product-price')]/span")).getText();
		 System.out.println("Price of the shoe is: Rs. " +price);
		 String discount = driver.findElement(By.xpath("//div[contains(@class,'product-price')]/span[2]")).getText();
		 System.out.println("Discount for the shoe is: Rs. " +discount);
		 Thread.sleep(2000);
		 
		 //11. Take the snapshot of the shoes.
		 File src = driver.getScreenshotAs(OutputType.FILE);
		 File dst = new File("./snaps/snapdeal.png");
		 FileUtils.copyFile(src, dst);
		 
		 //12. Close the current window
		 driver.findElement(By.xpath("//div[@class='close close1 marR10']/i[contains(@class,'sd-icon-delete-sign')]")).click();
		 
		 //13. Close the main window
		 //No such step in app
		 
		 //14. select the brand Puma
		 WebElement brandMore = driver.findElement(By.xpath("//button[@data-filtername='Brand']"));
		 js.executeScript("arguments[0].scrollIntoView();", brandMore);
		 brandMore.click();
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//label[@for='Brand-Columbus']")).click();
		 Thread.sleep(1000);
		 driver.findElement(By.xpath("//div[contains(@class,'applyFilters')]")).click();
		 
		 Thread.sleep(5000);
		 driver.quit();
		 
		}
		
	}


