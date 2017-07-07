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

public class Notification {

	static SeeTestAndroidDriver<SeeTestAndroidElement> driver;
	@BeforeClass
	public void driverInitiation() throws MalformedURLException
	{
		Util utilTS=new Util();
		driver=utilTS.setUp();
		driver.findElement(By.id("verification_code")).sendKeys("1234");
	}
	
	@Test 
	public void sessionCompletion()
	{
		driver.findElement(By.id("tab_notifications")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//*[@text='Congrats!']")).isDisplayed(), true);		
	}
	
	@Test
	public void selfAssessmentUnlock()
	{
		driver.findElement(By.id("tab_notifications")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//*[@text='How are you doing?']")).isDisplayed());
	}
	
	@Test
	public void sessionUnlock()
	{
		driver.findElement(By.id("tab_notifications")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//*[@text='Hi There!']")).isDisplayed());
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
