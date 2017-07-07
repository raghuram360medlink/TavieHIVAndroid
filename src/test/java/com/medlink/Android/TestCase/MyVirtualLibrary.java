package com.medlink.Android.TestCase;

import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.experitest.appium.SeeTestAndroidDriver;
import com.experitest.appium.SeeTestAndroidElement;
import com.tavieHIV.Android.Util.Util;

public class MyVirtualLibrary {

static SeeTestAndroidDriver<SeeTestAndroidElement> driver;
	
	@BeforeClass
	public void hpDriverInit() throws MalformedURLException
	{
		Util utilSessions=new Util();
		driver=utilSessions.setUp();
		driver.findElement(By.id("verification_code")).sendKeys("1234");
	}
	
	@Test
	public void AddtoFavorites()
	{
		driver.findElement(By.id("tab_usertools")).click();
		driver.findElement(By.id("ressources_dash")).click();
		driver.findElement(By.id("generalinfobtn")).click();
		driver.findElement(By.xpath("//*[@id='imageView65' and ./parent::*[./following-sibling::*[@text='DECIDE']]]")).click();
		driver.findElement(By.xpath("//*[@text='Favorites']")).click();
		driver.findElement(By.xpath("//*[@id='generalinfobtn']")).click();
		Assert.assertEquals(2, driver.findElements(By.xpath("//*[@text='DECIDE']")).size());
		driver.findElement(By.id("backbtn")).click();
	}
	
	@Test
	public void RemoveFromFavorites()
	{
		driver.findElement(By.id("tab_usertools")).click();
		driver.findElement(By.id("ressources_dash")).click();
		driver.findElement(By.xpath("//*[@text='Favorites']")).click();
		driver.findElement(By.id("generalinfobtn")).click();
		driver.findElement(By.id("imageView65")).click();
		Assert.assertEquals(driver.findElements(By.id("general_number")).get(1).getText(), " 0 items");
		driver.findElement(By.id("backbtn")).click();
		
	}
	
	@Test
	public void searchOperation()
	{
		driver.findElement(By.id("tab_usertools")).click();
		driver.findElement(By.id("ressources_dash")).click();
		driver.findElement(By.xpath("//*[@id='searchbtn']")).sendKeys("POS");
		driver.hideKeyboard();
		driver.findElement(By.id("generalinfobtn")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//*[@text='Positive association']")).isDisplayed(), true);
		driver.findElement(By.id("backbtn")).click();
		
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
