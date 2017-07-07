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

public class SelfAssessment {

static SeeTestAndroidDriver<SeeTestAndroidElement> driver;
	
	@BeforeClass
	public void hpDriverInit() throws MalformedURLException
	{
		Util utilSelfAssessment=new Util();
		driver=utilSelfAssessment.setUp();
		driver.findElement(By.id("verification_code")).sendKeys("1234");
	}
	
	@Test
	public void SelfAssessmentCompletion() throws InterruptedException
	{
		driver.findElement(By.id("tab_usertools")).click();
		driver.findElement(By.xpath("//*[@text='Self-Assessment']")).click();
		//Clciking on the questionnaire
		driver.findElements(By.id("textView24")).get(0).click();
		driver.findElement(By.xpath("//*[@text='Strongly agree']")).click();
		Thread.sleep(1000L);
		Assert.assertEquals(driver.findElement(By.id("textView107")).getText(),"20%");
		driver.findElement(By.xpath("//*[@text='Agree']")).click();
		Thread.sleep(1000L);
		Assert.assertEquals(driver.findElement(By.id("textView107")).getText(),"40%");
		driver.findElement(By.id("backbtn")).click();
		driver.findElement(By.xpath("//*[@text='RESUME']")).click();
		for(int i=0 ;i<2;i++)
		{
			driver.findElement(By.xpath("//*[@text='Next']")).click();
		}
		driver.findElement(By.xpath("//*[@text='Neither agree nor disagree']")).click();
		Thread.sleep(1000L);
		Assert.assertEquals(driver.findElement(By.id("textView107")).getText(),"60%");
		driver.findElement(By.xpath("//*[@text='Disagree']")).click();
		Thread.sleep(1000L);
		Assert.assertEquals(driver.findElement(By.id("textView107")).getText(),"80%");
		driver.findElement(By.xpath("//*[@text='Strongly disagree']")).click();
		Thread.sleep(1000L);
		Assert.assertEquals(driver.findElement(By.id("textView107")).getText(),"100%");
		Assert.assertTrue(driver.findElement(By.id("needle")).isDisplayed());
		
		driver.findElement(By.xpath("//*[@text='DONE']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("xpath=//*[@text='100%' and ./parent::*[./preceding-sibling::*[./*[./*[@text='Views about your anti-HIV treatment']]]]]")).isDisplayed());
        driver.findElement(By.id("backbtn")).click();
        
        
	}
	
	@Test(dependsOnMethods={"SelfAssessmentCompletion"})
	public void selfAssessmeentReset()
	{
		driver.findElement(By.id("tab_usertools")).click();
		driver.findElement(By.xpath("//*[@text='Self-Assessment']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//@text='REVIEW'")).isDisplayed(),true);
		driver.findElement(By.id("textView104")).click();
		Assert.assertEquals(driver.findElement(By.id("loading")).isDisplayed(), true);
		
		for(int i=0; i<2 ; i++)
		{
		
		
		driver.findElement(By.id("confirm_button")).click();
		
		}
		
		Assert.assertEquals(driver.findElements(By.id("textView109")).get(1).getText(), "0%");
		
		System.out.println("Session 1 is successfuly reset");
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
