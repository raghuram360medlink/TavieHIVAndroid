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

public class SymptomsDiary {

static SeeTestAndroidDriver<SeeTestAndroidElement> driver;
	
	@BeforeClass
	public void hpDriverInit() throws MalformedURLException
	{
		Util utilSessions=new Util();
		driver=utilSessions.setUp();
		driver.findElement(By.id("verification_code")).sendKeys("1234");
	}
	
	@Test
	public void symptomsCreateOrderByDate()
	{
		driver.findElement(By.id("tab_usertools")).click();
		driver.findElement(By.id("textViewdiary")).click();
		driver.findElement(By.id("add_fab")).click();
		driver.findElement(By.xpath("//*[@text='Skin problems']")).click();
		driver.findElement(By.xpath("//*[@text='Sleeping problems']")).click();
		driver.findElement(By.id("validate_fab")).click();
		
		Assert.assertEquals(driver.findElement(By.id("symptom_name")).getText(),"Skin problems");
		
		driver.findElements(By.id("intense_img")).get(0).click();
		driver.swipe(372,685, 372, 600, 300);
		driver.swipe(206,1108,212,1008, 300);
		
		driver.findElement(By.id("finish_fab")).click();
		
		//Assert.assertEquals(driver.findElement(By.id("symtoms_name")).getText(),"Sleeping problems");
		
		driver.findElements(By.id("distressing_img")).get(1).click();
		driver.findElement(By.id("finish_fab")).click();
		
		driver.findElement(By.xpath("//*[@text='DONE']")).click();
		
		Assert.assertEquals(driver.findElements(By.id("symp_desc")).get(0).getText(), "SKIN PROBLEMS");
		Assert.assertEquals(driver.findElements(By.id("symp_desc")).get(1).getText(), "SLEEPING PROBLEMS");
		
		Assert.assertEquals(driver.findElement(By.xpath("//*[@text='JUNE (2)']")).isDisplayed(), true);
		
		System.out.println("Symptoms is created and categorized in date section");
		
		driver.findElement(By.id("backbtn")).click();
		
	}
	
	
	@Test
	public void symptomsDiaryDelete()
	{
		driver.findElement(By.id("tab_usertools")).click();
		driver.findElement(By.id("textViewdiary")).click();
		int existing_symptoms_count=driver.findElements(By.xpath("//*[@text='SKIN PROBLEMS']")).size();
		
		driver.swipe(200, 475, 60, 514, 300);
		driver.findElement(By.id("deletemed")).click();
		
		Assert.assertEquals(driver.findElements(By.xpath("//*[@text='SKIN PROBLEMS']")).size(), existing_symptoms_count-2);
		
		System.out.println("Medication is successfully deleted");
		driver.findElement(By.id("backbtn")).click();
		
	}
	
	@Test
	public void symptomsCreateOrderBySymptoms()
	{
		driver.findElement(By.id("tab_usertools")).click();
		driver.findElement(By.id("textViewdiary")).click();
		driver.findElement(By.id("add_fab")).click();
		driver.findElement(By.xpath("//*[@text='Skin problems']")).click();
		driver.findElement(By.id("validate_fab")).click();
		
		Assert.assertEquals(driver.findElements(By.id("symptom_name")).get(0).getText(),"Skin problems");
		
		driver.findElements(By.id("intense_img")).get(0).click();
		driver.swipe(372,685, 372, 600, 300);
		driver.swipe(206,1108,212,1008, 300);
		
		driver.findElement(By.id("finish_fab")).click();
		
		driver.findElement(By.xpath("//*[@text='DONE']")).click();
		
		driver.findElement(By.xpath("//*[@text='By Symptom']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//*[@text='SKIN PROBLEMS (2)']")).isDisplayed());
		
		System.out.println("Symptoms diary is Successfully categorized");
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
