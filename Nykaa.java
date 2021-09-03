package week4.day2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	public static void main(String[] args) throws InterruptedException {

		//Step1: Go to https://www.nykaa.com/
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Step2: Mouseover on Brands and Mouseover on Popular
		//driver.findElement(By.className("close")).click();
		Actions builder = new Actions(driver);
		WebElement brand = driver.findElement(By.xpath("(//a[@href='/'])[2]"));
		builder.moveToElement(brand).perform();
		Thread.sleep(1000);
		WebElement popular = driver.findElement(By.xpath("//div[@class='BrandsCategoryHeading']/a"));
		builder.moveToElement(popular).perform();
		
		//Step3: Click L'Oreal Paris
		driver.findElement(By.xpath("//div[@id='brandCont_Popular']//li[5]")).click();
		
		//Step4: Go to the newly opened window and check the title contains L'Oreal Paris
		Set<String> windows = driver.getWindowHandles();
		List<String> windowsList = new ArrayList<String>(windows);
		
		String child1 = windowsList.get(1);    //String parentWin = windowsList.get(0);
		
		driver.switchTo().window(child1);
		
		String title = driver.getTitle();
		if(title.contains("L'Oreal Paris")) {
			System.out.println("Title contains L'Oreal Paris");
		}
		else
			System.out.println("Title does not contain L'Oreal Paris");
		
		//Step5: Click sort By and select customer top rated
		WebElement sortPoint = driver.findElement(By.xpath("//p[@class='block-heading']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", sortPoint);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='sort-btn clearfix']")).click();
		driver.findElement(By.xpath("//span[contains(text(),'rated')]")).click();
		Thread.sleep(2000);
		
		WebElement topt = driver.findElement(By.xpath("//div[@class='block-detail']"));
		js.executeScript("arguments[0].scrollIntoView();", topt);
		
		driver.findElement(By.xpath("//div[text()='Category']")).click();
		driver.findElement(By.xpath("//span[text()='Hair']")).click();
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		driver.findElement(By.xpath("//span[text()='Shampoo']")).click();
		
		
		//Step7: check whether the Filter is applied with Shampoo
		String filterValue = driver.findElement(By.xpath("//li[text()='Shampoo']")).getText();
		//System.out.println("Applied filter is: " +filterValue);
		if(filterValue.contains("Shampoo"))
			System.out.println("Applied filter is Shampoo");
		else
			System.out.println("Incorrect filter is shown");
		
		//Step8: Click on L'Oreal Paris Colour Protect Shampoo
		driver.findElement(By.xpath("//span[contains(text(),'Colour Protect')]/..")).click();
		Thread.sleep(5000);
		
		//Step9: GO to the new window and select size as 175ml
		Set<String> windows1 = driver.getWindowHandles();
		List<String> windowsList1 = new ArrayList<String>(windows1);
		
		int len = windowsList1.size();
		int curWin = len-1;
		String child2 = windowsList1.get(curWin);
		driver.switchTo().window(child2);
		
		WebElement mlValue = driver.findElement(By.xpath("//span[text()='175ml']"));
		boolean selected = mlValue.isSelected();
		if(!selected)
			mlValue.click();
		
		
		//Step10: Print the MRP of the product
		String price = driver.findElement(By.xpath("//span[@class='post-card__content-price-offer']")).getText();
		String actprice = price.replaceAll("[^0-9]", "");
		System.out.println("MRP of the shampoo is:\t" +actprice);
		
		//Step11: Click on ADD to BAG
		driver.findElement(By.xpath("//button[contains(@class,'combo-add-to-btn')]")).click();
		Thread.sleep(2000);
		
		//Step12: Go to Shopping Bag 
		driver.findElement(By.xpath("//div[@class='AddBagIcon']")).click();
		
		//Step13: Print the Grand Total amount
		String gTotal1 = driver.findElement(By.xpath("//div[@class='value medium-strong']")).getText();
		String actTotal = gTotal1.replaceAll("[^0-9]", "");
		int tt1 = Integer.parseInt(actTotal);
		System.out.println("Grand total in cart:\t" +tt1);
		
		//Step14: Click Proceed
		WebElement proceed = driver.findElement(By.xpath("//i[@class='proceed-arrow']"));
		js.executeScript("arguments[0].click();", proceed);
		Thread.sleep(2000);
		
		//Step15: Click on Continue as Guest
		WebElement btnGuest = driver.findElement(By.xpath("//button[@class='btn full big']"));
		js.executeScript("arguments[0].click();", btnGuest);
		Thread.sleep(2000);
		
		//Step16: Check if this grand total is the same in step 13
		String gTotal2 = driver.findElement(By.xpath("(//div[@class='value']/span)[2]")).getText();
		String actTotal1 = gTotal2.replaceAll("[^0-9]", "");
		int tt2 = Integer.parseInt(actTotal1);
		System.out.println("Grand total in checkout:\t" +tt2);
		
		if(tt1==tt2) {
			System.out.println("Grand total is same");
		}
		
		else
			System.out.println("Grand total is different");
		
		
		//Step17: Close all windows
		Thread.sleep(5000);
		driver.quit();
	}

}
