package week4.day2;

import org.openqa.selenium.WebElement;

public class StringToInt {
	
	public int ToGetCount(WebElement wbEmt) {
		
		String strCount = wbEmt.getText();
		String repCount = strCount.replaceAll("[^0-9]", "");
		int intCount = Integer.parseInt(repCount);
		return intCount;
		
	}
	
	
}
