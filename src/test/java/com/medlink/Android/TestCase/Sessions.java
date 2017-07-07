package com.medlink.Android.TestCase;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.experitest.appium.SeeTestAndroidDriver;
import com.experitest.appium.SeeTestAndroidElement;
import com.tavieHIV.Android.Util.Util;

public class Sessions {
	
	static SeeTestAndroidDriver<SeeTestAndroidElement> driver;
	
	@BeforeClass
	public void hpDriverInit() throws MalformedURLException
	{
		Util utilSessions=new Util();
		driver=utilSessions.setUp();
		driver.findElement(By.id("verification_code")).sendKeys("1234");
	}
	
	@Test
	public void sessionGeneralActivities()
	{
		driver.findElement(By.id("tab_dashboard")).click();
		driver.findElement(By.id("textView14")).click();
		if(driver.findElement(By.id("playvidpause")).isDisplayed())
		{
			System.out.println("Session Video is displayed");
		}
		for (int i=0; i<2; i++)
		{
		driver.findElement(By.id("button9")).click();  //Video speech to text button
		}
		
		driver.findElement(By.id("resource_button")).click();
		
		for (int i=0; i<2; i++)
		{
		driver.findElement(By.id("backbtn")).click();
		}
		
	}

	@Test
	public void session1Completion() throws InterruptedException
	{
		driver.findElement(By.id("tab_sessions")).click();
	    if(driver.findElements(By.xpath("//*[@text='RESET']")).size()>0)
	    {
	    driver.findElements(By.xpath("//*[@text='RESET']")).get(0).click();	
		for (int i=0; i<2; i++)
		{
		driver.findElement(By.id("confirm_button")).click();
		}
	    }
		driver.findElement(By.xpath("//*[@text='Session 1']")).click();
		Thread.sleep(1000L);
		driver.swipe(547, 960, 547, 60, 300);
		driver.findElement(By.xpath("//*[@text='Yes']")).click();
		Thread.sleep(1000L);
		driver.findElement(By.xpath("//*[@text='Continue']")).click();
		driver.findElement(By.xpath("//*[@text='No']")).click();
		driver.swipe(108, 724, 350, 724, 300);
		Thread.sleep(1000L);
		driver.findElement(By.xpath("//*[@text='Continue']")).click();
		driver.findElement(By.xpath("//*[@text='No']")).click();
		driver.swipe(547, 960, 547, 160, 300);
		Thread.sleep(1000L);
		driver.findElement(By.xpath("//*[@text='See you in 2 weeks']")).click();
		Assert.assertEquals(driver.findElements(By.xpath("//*[@text='100%']")).get(0).isDisplayed(), true);
		
			
	}

	@Test(dependsOnMethods={"session1Completion"})
	public void session1Reset()
	{
		driver.findElement(By.id("tab_sessions")).click();
		driver.findElements(By.xpath("//*[@text='RESET']")).get(0).click();
		
		for (int i=0; i<2; i++)
		{
		driver.findElement(By.id("confirm_button")).click();
		}
		
		Assert.assertEquals(driver.findElements(By.xpath("//*[@text='0%']")).get(0).isDisplayed(), true);
		
	} 
	
	@AfterMethod
		public void tearDown(ITestResult result) throws MalformedURLException
		{
			if (result.getStatus() == ITestResult.FAILURE){
				driver.close();
				Util util=new Util();
				driver=util.setUp();
				driver.findElement(By.id("verification_code")).sendKeys("1234");
				
			}
		}
	
	
	@AfterClass
	public void driverQuit()
	{
		driver.close();
	}

}
