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

public class HealthParameter {

	static SeeTestAndroidDriver<SeeTestAndroidElement> driver;
	
	
	
	@BeforeClass
	public void hpDriverInit() throws MalformedURLException
	{
		Util utilHP=new Util();
		driver=utilHP.setUp();
		
		driver.findElement(By.id("verification_code")).sendKeys("1234");
	}
	
	@Test
	public void adherenceRateCreate() throws InterruptedException
	{
		//Calculating the total number of pills
		
		HealthParameter.dailyDosageCreate();
		driver.findElement(By.id("tab_dashboard")).click();
		driver.findElement(By.xpath("//*[@text='Adherence Rate']")).click();
		Assert.assertEquals(driver.findElement(By.id("adherence_chart")).isDisplayed(), true);
		
		driver.findElement(By.id("adherence_improtant")).click();
		
		//Assert.assertEquals(driver.findElement(By.id("videoView")).isDisplayed(), true);
		
		driver.findElement(By.xpath("//*[@text='Learn more']")).click();
		
		
		Assert.assertEquals(true,driver.findElements(By.id("pdfView")).size()>0);
		
		Assert.assertEquals(true, driver.findElements(By.id("share")).size()>0);
		
		Assert.assertEquals(true,driver.findElements(By.id("imageView28")).size()>0);
		
		driver.findElement(By.id("backbtn")).click();
		driver.findElement(By.id("adherence_back")).click();
		
		//Adding adherence rate
		
		driver.findElement(By.id("adherence_add_measure")).click();
		
		driver.swipe(185, 766, 352, 766, 300);
		driver.findElement(By.xpath("//*[@text='ACCEPT']")).click();
		
		driver.findElement(By.id("adherence_back")).click();
		
		
	}

	@Test(dependsOnMethods={"adherenceRateCreate"}, priority=1)
	public void editAdherenceRate()
	{
		driver.findElement(By.xpath("//*[@text='Adherence Rate']")).click();
		driver.findElement(By.id("adherence_list")).click();
		driver.findElement(By.id("adherence_add_measure")).click();
		driver.swipe(352, 766, 200, 766, 300);
		driver.findElement(By.xpath("//*[@text='ACCEPT']")).click();
		driver.findElement(By.id("adherence_back")).click();
	}

	
	@Test(dependsOnMethods={"adherenceRateCreate"}, priority=2)
	public void deleteAdherenceRate()
	{
		driver.findElement(By.xpath("//*[@text='Adherence Rate']")).click();
		driver.findElement(By.id("adherence_list")).click();
		driver.swipe(580, 266, 35, 266, 200);
		driver.findElement(By.id("deletemed")).click();
		driver.findElement(By.id("adherence_back")).click();
	}

	                                       //Viral Load
	
	//Viral Load Create

	@Test
	public void viralLoadCreate()
	{
		driver.findElement(By.id("tab_dashboard")).click();
		driver.findElement(By.xpath("//*[@text='Viral Load / CD4']")).click();
		
		
		
		driver.findElements(By.id("care_continuum")).get(0).click();
		
		Assert.assertEquals(driver.findElement(By.id("share")).isDisplayed(), true);
		Assert.assertEquals(driver.findElement(By.id("imageView28")).isDisplayed(), true);
		
		
		driver.findElement(By.id("backbtn")).click();
		driver.findElement(By.id("cd4_list")).click();
		
		int viral_load_Before_addition=driver.findElements(By.id("cd4_list_txt")).size();
		
		System.out.println("Number of viral loads measurements entered " + viral_load_Before_addition);
		
		driver.findElement(By.id("cd4_list")).click();
		
		Assert.assertEquals(driver.findElement(By.id("pulse_chart")).isDisplayed(), true);
		
		driver.findElement(By.id("cd_mesure")).click();
		
		driver.findElement(By.id("pulse_value")).sendKeys("500");
		driver.findElement(By.id("bloodP_dialog_add")).click();
		
		driver.findElement(By.id("cd4_list")).click();
		
		int viral_load_After_addition=driver.findElements(By.id("cd4_list_txt")).size();
		System.out.println("Number of viral loads measurements after adiition " + viral_load_After_addition);
		
		Assert.assertEquals(viral_load_After_addition, viral_load_Before_addition+1);
		
		driver.findElement(By.id("viral_back")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//*[starts-with(., '500')]")).isDisplayed(), true);
		
		
	}




	//Viral load Delete


	@Test(dependsOnMethods={"viralLoadCreate"})
	public void viralLoadDelete() throws InterruptedException
	{
		driver.findElement(By.id("tab_dashboard")).click();
		driver.findElement(By.xpath("//*[@text='Viral Load / CD4']")).click();
		driver.findElement(By.id("cd4_list")).click();
		
		int viral_count_before_delete=driver.findElements(By.id("cd4_list_txt")).size();
		
		System.out.println(viral_count_before_delete);
		
		driver.swipe(524, 414, 8, 414, 200);
		
		driver.findElement(By.id("deletemed")).click();
		
		Thread.sleep(2000L);
		
		int viral_count_after_delete=driver.findElements(By.id("cd4_list_txt")).size();
		
		System.out.println(viral_count_after_delete);
		
		Assert.assertEquals(viral_count_after_delete, viral_count_before_delete-1);
		
		driver.findElement(By.id("viral_back")).click();
		
	}



	                                      //CD4 

	//CD4 Create
	@Test
	public void cd4Create()
	{
		
		driver.findElement(By.id("tab_dashboard")).click();
		driver.findElement(By.xpath("//*[@text='Viral Load / CD4']")).click();
		driver.findElement(By.xpath("//*[@text='CD4 count']")).click();
		
		driver.findElement(By.id("cd4_list")).click();
		
		int cd4_count_before_addition=driver.findElements(By.id("cd4_list_txt")).size();
		System.out.println("CD4 count before addition " + cd4_count_before_addition);
		
		driver.findElement(By.id("cd4_list")).click();
		
		Assert.assertEquals(driver.findElements(By.id("pulse_chart")).size()>0, true);
		
		driver.findElements(By.id("care_continuum")).get(1).click();
		
		Assert.assertEquals(driver.findElement(By.id("share")).isDisplayed(), true);
		
		Assert.assertEquals(true,driver.findElements(By.id("imageView28")).size()>0);
		
		driver.findElement(By.id("backbtn")).click();
		
		System.out.println(driver.findElements(By.id("cd_mesure")).size());
		
		driver.findElements(By.id("cd_mesure")).get(1).click();
		
		driver.findElement(By.id("pulse_value")).sendKeys("1000");
		
		driver.findElement(By.id("bloodP_dialog_add")).click();
		
		driver.findElement(By.id("cd4_list")).click();
		
		int cd4_count_after_addition=driver.findElements(By.id("cd4_list_txt")).size();
		System.out.println("CD4 count after addition " + cd4_count_after_addition);
		
		Assert.assertEquals(cd4_count_after_addition, cd4_count_before_addition+1);
		
		driver.findElement(By.id("viral_back")).click();
		
		
	}

	                                     //CD4 Delete

	@Test
	public void cd4Delete() throws InterruptedException
	{
		driver.findElement(By.id("tab_dashboard")).click();
		driver.findElement(By.xpath("//*[@text='Viral Load / CD4']")).click();
		driver.findElement(By.xpath("//*[@text='CD4 count']")).click();
		driver.findElement(By.id("cd4_list")).click();
		
	int CD4_count_before_delete=driver.findElements(By.id("cd4_list_txt")).size();
		
		System.out.println(CD4_count_before_delete);
		
		driver.swipe(524, 414, 8, 414, 200);
		
		driver.findElement(By.id("deletemed")).click();
		
		Thread.sleep(2000L);
		
		int CD_count_after_delete=driver.findElements(By.id("cd4_list_txt")).size();
		
		System.out.println(CD_count_after_delete);
		
		Assert.assertEquals(CD_count_after_delete, CD4_count_before_delete-1);
	
		driver.findElement(By.id("viral_back")).click();
		
	}
	                                      

										//Blood Pressure Create

	@Test
	public void bloodPressureCreate() throws InterruptedException
	{
		driver.findElement(By.id("tab_dashboard")).click();
		driver.findElement(By.xpath("//*[@text='Blood Pressure']")).click();
		
		Assert.assertEquals(driver.findElements(By.id("line_view")).size()>0, true);
		
		driver.findElement(By.id("bloodp_list_bloodp2")).click();
		
		int bp_before_addition=driver.findElements(By.xpath("//*[@text='SYSTOLIC']")).size();
		
		System.out.println("Number of Blood Pressure values already present " +bp_before_addition);
		
		driver.findElement(By.id("bloodp_add_mesure2")).click();
		
		driver.findElement(By.id("bloodp_dialog_systolic_value")).sendKeys("60");
		
		driver.findElement(By.id("bloodp_dialog_diastolic_value")).sendKeys("40");
		
		driver.findElement(By.id("bloodP_dialog_add")).click();
		
		int bp_after_addition=driver.findElements(By.xpath("//*[@text='SYSTOLIC']")).size();
		
		System.out.println("Number of Blood Pressure values after addition " +bp_after_addition);
		Assert.assertEquals(bp_after_addition, bp_before_addition + 1);
		
		driver.findElement(By.id("bloodp_back")).click();
	}


	//Blood Pressure Delete

	@Test
	public void bloodPressureDelete() throws InterruptedException
	{
		driver.findElement(By.id("tab_dashboard")).click();
		driver.findElement(By.xpath("//*[@text='Blood Pressure']")).click();
		driver.findElement(By.id("bloodp_list_bloodp2")).click();
		
		int bp_count_before_deletion=driver.findElements(By.xpath("//*[@text='SYSTOLIC']")).size();
		
		System.out.println("Bp Count Before Deletion " + bp_count_before_deletion);
		
	    driver.swipe(524, 250, 8, 250, 400);
		
		driver.findElement(By.id("deletemed")).click();
		
		Thread.sleep(2000L);
		
	    int bp_count_after_deletion=driver.findElements(By.xpath("//*[@text='SYSTOLIC']")).size();
		
		System.out.println("Bp Count After Deletion" + bp_count_after_deletion);
		
		Assert.assertEquals(bp_count_after_deletion, bp_count_before_deletion-1);
		
			
		driver.findElement(By.id("bloodp_back")).click();
		
	}



	//Pulse
	@Test
	public void pulseCreate()
	{
		driver.findElement(By.id("tab_dashboard")).click();
		driver.findElement(By.xpath("//*[@text='Pulse']")).click();
		
		Assert.assertEquals(driver.findElements(By.id("pulse_chart")).size()>0, true);
		
		
		driver.findElement(By.id("pulse_list")).click();
		
		int pulse_list_before_addition=driver.findElements(By.id("pulse_list_value")).size();
		
		System.out.println("The number of pulse values before addition " + pulse_list_before_addition);
		
		driver.findElement(By.id("pulse_add_measure")).click();
		
		driver.findElement(By.id("pulse_value")).sendKeys("90");
		
		driver.findElement(By.id("bloodP_dialog_add")).click();
		
		int pulse_list_after_addition=driver.findElements(By.id("pulse_list_value")).size();
		
	    Assert.assertEquals(pulse_list_after_addition, pulse_list_before_addition+1); 	
	
		driver.findElement(By.id("pulse_back")).click();
		
		Assert.assertEquals(driver.findElement(By.id("textView33")).getText(), "90");
		
	}
		
		

		//Pulse Delete
		
	@Test
	public void pulseDelete() throws InterruptedException
	{
		
		driver.findElement(By.id("tab_dashboard")).click();
		driver.findElement(By.xpath("//*[@text='Pulse']")).click();
		
		driver.findElement(By.id("pulse_list")).click();
		
		int pulse_list_before_deletion=driver.findElements(By.id("pulse_list_value")).size();
		
		System.out.println("The number of pulse values dispalyed before deleting: " +pulse_list_before_deletion);
		
		driver.swipe(300, 250, 8, 250, 400);
		
		driver.findElement(By.id("deletemed")).click();
		
		Thread.sleep(2000L);
			
		int pulse_list_after_deletion=driver.findElements(By.id("pulse_list_value")).size();
		
		System.out.println("The number of pulse values dispalyed before deleting: " +pulse_list_after_deletion);
		
		Assert.assertEquals(pulse_list_after_deletion, pulse_list_before_deletion-1);
		
		driver.findElement(By.id("pulse_back")).click();
		
		Assert.assertNotEquals(driver.findElement(By.id("textView33")).getText(), "90");
				}
		
		
	                               //*****MINUTES AND CALORIES*****

	@Test
	public void minutesCaloriesCreate()
	{
		driver.findElement(By.id("tab_dashboard")).click();
		driver.findElement(By.xpath("//*[@text='Minutes/Calories']")).click();
		driver.findElement(By.id("steps_list")).click();
		
		if(driver.findElements(By.id("one_list_steps_steps")).size()>0)
		{
			driver.swipe(300, 250, 8, 250, 400);
			
			driver.findElement(By.id("deletemed")).click();
		}
		
		int count_before_adding=driver.findElements(By.id("one_list_steps_steps")).size();
		
		driver.findElement(By.id("steps_mesure")).click();
		
		driver.findElement(By.id("spiner_txt")).click();
		
		driver.findElement(By.xpath("//*[@text='Bicycling']")).click();
		
		driver.findElement(By.id("dialog_time")).click();
		
		driver.findElement(By.xpath("//*[@content-desc='12']")).click();
		
		driver.findElement(By.xpath("//*[@content-desc='5']")).click();
		
		driver.findElement(By.id("button1")).click();
		
		if(driver.findElement(By.id("dialog_weight"))==null)
		{
		driver.findElement(By.id("dialog_weight")).sendKeys("200");
		}
		else
		{
			driver.findElement(By.id("dialog_weight")).clear();
			driver.findElement(By.id("dialog_weight")).sendKeys("200");
		}
		
		driver.findElement(By.id("duration_hours")).sendKeys("1");
		driver.findElement(By.id("duration_minutes")).sendKeys("00");
		driver.findElement(By.id("bloodP_dialog_add")).click();
		
		
		
		//Checking the minutes/calories count list after adding 
		int count_after_adding=driver.findElements(By.id("one_list_steps_steps")).size();
		
		Assert.assertEquals(count_after_adding, count_before_adding+1);
		
	    
	    
	    
	    //Navigating to minutes chart
		driver.findElement(By.id("steps_list")).click();
		
		Assert.assertEquals(driver.findElements(By.id("steps_round_steps_value")).get(0).getText(), "60 minutes today");
		
        Assert.assertEquals(driver.findElement(By.id("bike_txt")).getText(), "60\nmin"); 
		
		
		//Validating calories chart
		driver.findElement(By.xpath("//*[@text='Calories']")).click();
		
		Assert.assertEquals(driver.findElements(By.id("steps_round_steps_value")).get(1).getText(), "562 calories burned today");
		
		Assert.assertEquals(driver.findElements(By.id("bike_txt")).get(1).getText(), "562\ncal");
		
		driver.findElement(By.id("steps_back")).click();
		
		Assert.assertEquals(driver.findElement(By.id("textView116")).getText(), "60/562");
		
		
	}



	                                //****Minutes/Calories Delete****
	@Test
	public void minutesCaloriesDelete()
	{
		driver.findElement(By.id("tab_dashboard")).click();
		
		driver.findElement(By.xpath("//*[@text='Minutes/Calories']")).click();
		driver.findElement(By.id("steps_list")).click();
		
		int count_before_deleting=driver.findElements(By.id("one_list_steps_steps")).size();
		
		driver.swipe(300, 250, 8, 250, 400);
		
		driver.findElement(By.id("deletemed")).click();
		
		int count_after_deleting=driver.findElements(By.id("one_list_steps_steps")).size();
		
		Assert.assertEquals(count_before_deleting-1, count_after_deleting);
		
		driver.findElement(By.id("steps_back")).click();
		
		Assert.assertNotEquals(driver.findElement(By.id("textView116")).getText(), "60/562");
		
	}



	//***BMI***

	@Test
	public void bmiCreate()
	{
		driver.findElement(By.id("tab_dashboard")).click();
		
		driver.findElement(By.xpath("//*[@text='Body Mass Index']")).click();
		
		Assert.assertEquals(driver.findElements(By.id("line_view")).size()>0, true);
		
		driver.findElement(By.id("bloodp_list_bloodp2")).click();
		
		//Add
		
	int BMI_before_addition=driver.findElements(By.id("swipe_layout")).size();
		
		
	driver.findElement(By.id("bloodp_add_mesure2")).click();
	driver.findElement(By.id("bmi_feet")).sendKeys("6");
	driver.findElement(By.id("bmi_inch")).sendKeys("3");
	driver.findElement(By.id("bloodp_dialog_systolic_value")).sendKeys("150");
	driver.findElement(By.id("bloodP_dialog_add")).click();

	int BMI_after_addition=driver.findElements(By.id("swipe_layout")).size();
    
	Assert.assertEquals(BMI_after_addition, BMI_before_addition+1);

	String BMI_value=driver.findElement(By.id("list_bmi")).getText();

	driver.findElement(By.id("bloodp_back")).click();

	Assert.assertEquals(driver.findElement(By.id("BMIdash")).getText(), BMI_value);
	}


	@Test
	public void bmiDelete() throws InterruptedException
	{
	    driver.findElement(By.id("tab_dashboard")).click();
		
		driver.findElement(By.xpath("//*[@text='Body Mass Index']")).click();
		
		driver.findElement(By.id("bloodp_list_bloodp2")).click();
		
		int BMI_Count_before_deleting=driver.findElements(By.id("list_bmi")).size();
		System.out.println(BMI_Count_before_deleting);
		
	    driver.swipe(300, 250, 8, 250, 400);
		
		driver.findElement(By.id("deletemed")).click();
		
		Thread.sleep(2000L);
		
		int BMI_count_after_deleting=driver.findElements(By.id("list_bmi")).size();
		
		System.out.println(BMI_count_after_deleting);
		
		driver.findElement(By.id("bloodp_back")).click();
		
	    driver.findElement(By.xpath("//*[@text='Body Mass Index']")).click();
		
		driver.findElement(By.id("bloodp_list_bloodp2")).click();
	
		Assert.assertEquals(BMI_count_after_deleting, BMI_Count_before_deleting-1);
		driver.findElement(By.id("bloodp_back")).click();
		
	}

	//Daily dosage create for Adherence rate calculation
	
	public static void dailyDosageCreate() throws InterruptedException
	{
		System.out.println("Inside");
		driver.findElement(By.id("tab_usertools")).click();
		driver.findElement(By.xpath("//*[@text='Treatment Summary']")).click();
		driver.findElement(By.id("add_medication")).click();
		driver.findElement(By.xpath("//*[@text='3TC150']")).click();
		driver.findElement(By.id("frequency_layout")).click();
		driver.findElement(By.id("dailytext")).click();
		driver.findElement(By.id("how_many_times")).click();
		driver.findElement(By.xpath("//*[@text='1']")).click();
		driver.findElement(By.id("timedosa")).click();
		driver.findElement(By.id("am_label")).click();
		driver.findElement(By.xpath("//*[@contentDescription='12']")).click();
		driver.findElement(By.xpath("//*[@contentDescription='5']")).click();
		driver.findElement(By.xpath("//*[@text='OK']")).click();
		
		driver.findElement(By.id("addbutton")).click();
		Thread.sleep(1000L);
		driver.findElement(By.id("continueButton")).click();
		for (int i=0; i<2; i++)
		{
		driver.findElement(By.id("backbtn")).click();
		}
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
public void hpDriverQuit()
{
	driver.close();
}

	
	
}
