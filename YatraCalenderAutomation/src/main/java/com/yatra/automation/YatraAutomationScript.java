package com.yatra.automation;

import java.text.NumberFormat;
import java.time.Duration;
import java.time.YearMonth;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YatraAutomationScript {

	public static void main(String[] args) {
		
		//disabling the notifications in chromeDriver using ChromeOptions
		ChromeOptions chromeOptions=new ChromeOptions();
		chromeOptions.addArguments("--disable-notifications");
		
		//Launch the browser
		WebDriver driver=new ChromeDriver(chromeOptions);
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));//Synchronizing the webDriver
		
		driver.get("https://www.yatra.com");
		driver.manage().window().maximize();
		closeLoginPopupIfPresent(driver);
		
		By depatureDateButtonLocator=By.xpath("//div[@role=\"button\" and @aria-label=\"Departure Date inputbox\"]");
		WebElement depatureDateButton=wait.until(ExpectedConditions.elementToBeClickable(depatureDateButtonLocator));
		//WebElement depatureDateButton=driver.findElement(depatureDateButtonLocator);	
		depatureDateButton.click();
		
		String currentMonth=YearMonth.now().toString();
		String nextMonth=YearMonth.now().plusMonths(1).toString();
		int current_Month_MIN_Price=minFareCurrentMonth(driver,currentMonth);
		int next_Month_MIN_Price=minFareCurrentMonth(driver,nextMonth);
		
		minFareDate(driver,currentMonth,current_Month_MIN_Price);
		minFareDate(driver,nextMonth,next_Month_MIN_Price);
		
		System.out.println("Minimum price Comparing current and next month is:₹" +Math.min(current_Month_MIN_Price, next_Month_MIN_Price));
		
		
	
		
	}
	
	//Method For Closing Pop-Up If It Appears
	public static void closeLoginPopupIfPresent(WebDriver driver)
	{
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    try
	    {
	        // Wait until popup appears
	        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//section//img[@alt=\"cross\" ]")));

	        // If it appears, find and click the close (X) button
	        WebElement closeButton = popup.findElement(By.xpath("//div//section//img[@alt=\"cross\" ]")); // or By.xpath() if needed
	        closeButton.click();

	        System.out.println("Login popup closed successfully.");
	    }
	    catch (NoSuchElementException e)
	    {
	        System.out.println("Popup appeared but close button not found.");
	    }
	}
	
	// Method For Calculating Minimum Fare in Month
	public static int minFareCurrentMonth(WebDriver driver,String yearMonth)
	{
		int minFare=Integer.MAX_VALUE;
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		String pricesElement="//div[@aria-label=\"month  "+yearMonth+"\"]//span[contains(text(),'₹')]";
		List<WebElement> prices = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(pricesElement)));
		for(WebElement price:prices)
		{
			String fareText=price.getText().replaceAll("[^0-9]","");
			int fare=Integer.parseInt(fareText);
			if(fare<minFare)
			{
				minFare=fare;
			}
		}
		return minFare;
	}
	
	// Method For Finding the date on Minimum Fare
	public static void minFareDate(WebDriver driver,String yearMonth,int min_Price)
	{
		String min_Fare=NumberFormat.getNumberInstance().format(min_Price);
		String dateElement="//div[contains(@aria-label,'month  "+yearMonth+"')]//span[contains(text(),'"+min_Fare+"')]/ancestor::span[@aria-label=\"MAHA SHIVARATHIRI\"]";
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		WebElement date=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dateElement)));
		String fullText = date.getText();
		String[] parts = fullText.split("₹");
		String day = parts[0].trim();
		System.out.println("Minimum fare in " + yearMonth + " is ₹" + min_Fare + " on date: " + day);
	}
	


}
