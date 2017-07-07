package com.medlink.Android.TestCase;
import java.net.MalformedURLException;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.experitest.appium.SeeTestAndroidDriver;
import com.experitest.appium.SeeTestAndroidElement;
import com.tavieHIV.Android.Util.Util;

public class TreatmentSummary {

static SeeTestAndroidDriver<SeeTestAndroidElement> driver; 	


@BeforeClass
	public void driverInitiation() throws MalformedURLException
	{
		Util utilTS=new Util();
		driver=utilTS.setUp();
		driver.findElement(By.id("verification_code")).sendKeys("1234");
	}


@Test
public void dailyDosageCreate() throws InterruptedException
{
	
	driver.findElement(By.id("tab_usertools")).click();
	driver.findElement(By.xpath("//*[@text='Treatment Summary']")).click();
	
	//Validating existing daily dosages in ALL section
	driver.findElement(By.xpath("//*[@text='ALL']")).click();
	driver.swipe(358, 783, 337, -72, 200);
	
	int existing_dosage=driver.findElements(By.xpath("//*[@id='dailyitems_holder']//*[@id='textView63']")).size();
	
	System.out.println("Number of daily dosages already present: " +existing_dosage);
	
	
	//Switching to Today section
	driver.findElement(By.xpath("//*[@text='TODAY']")).click();
	driver.swipe(358, 783, 337, -72, 200);
	int medication_feedback_before_adding=driver.findElements(By.id("medicationfeedback")).size();
	
	//Adding medication
	Thread.sleep(2000L);
	driver.findElement(By.id("add_medication")).click();
	driver.findElement(By.id("searchbtn")).click();
	driver.findElement(By.id("searchtext")).sendKeys("3T");
	driver.hideKeyboard();
	driver.findElement(By.xpath("//*[@text='3TC150']")).click();
	
	Assert.assertEquals(driver.findElement(By.xpath("//*[@text='3TC150']")).isDisplayed(), true);
	
	
	driver.findElement(By.id("show_recommendation")).click();
	driver.findElement(By.id("recommendation_text")).isDisplayed();
	driver.findElement(By.xpath("//*[@text='Hide recommendations']")).click();
	driver.findElement(By.id("frequency_layout")).click();
	driver.findElement(By.id("dailytext")).click();
	driver.findElement(By.id("how_many_times")).click();
	driver.findElement(By.xpath("//*[@text='2']")).click();
	for(int i=0; i<2; i++)
	{
	driver.findElement(By.id("switchrminder")).click();
	}
	
	//Checking if dose 1 and dose 2 are added 
	driver.swipe(358, 783, 337, -72, 200);
	driver.findElement(By.xpath("//*[@text='Dose 1']")).isDisplayed();
	Assert.assertEquals(driver.findElement(By.xpath("//*[@text='Dose 2']")).isDisplayed(), true);
	
	int num_of_doses=driver.findElements(By.id("number_tablets_layout")).size();
	
	//Adding number of doses to a medication 
	for (int i=0;i<num_of_doses;i++)
	{
		driver.findElements(By.id("number_tablets_layout")).get(i).click();
		driver.swipe(624, 1008, 624, 700, 200);
		driver.findElement(By.id("selected_item")).click();
	}
	
	Assert.assertEquals(driver.findElement(By.xpath("//*[@text='2.0']")).isDisplayed(), true);
	
	Assert.assertEquals(driver.findElement(By.xpath("//*[@text='3.0']")).isDisplayed(), true);
	
	
	//Adding time of dose
	
	int time_of_dose=driver.findElements(By.id("timedosa")).size();
	for (int i=0;i<time_of_dose; i++)
	{
		driver.findElements(By.id("timedosa")).get(i).click();
		driver.findElement(By.id("am_label")).click();
		driver.findElement(By.id("pm_label")).click();
		driver.findElement(By.xpath("//*[@contentDescription='12']")).click();
		driver.findElement(By.xpath("//*[@contentDescription='5']")).click();
		driver.findElement(By.xpath("//*[@text='OK']")).click();
		driver.findElement(By.xpath("//*[@text='12:05PM']")).isDisplayed();
	}
	
	driver.findElement(By.id("addbutton")).click();
	Thread.sleep(1000L);
	driver.findElement(By.id("continueButton")).click();
	Thread.sleep(1000L);
	driver.findElement(By.xpath("//*[@text='3TC150']")).isDisplayed();
	
	//Medication count after adding the dosage in ALL section
	
	int medication_count=driver.findElements(By.xpath("//*[@id='dailyitems_holder']//*[@id='textView63']")).size();
	
	Assert.assertEquals(medication_count, 2+existing_dosage);
	
	
	
	//Switching to Today section

driver.findElement(By.xpath("//*[@text='TODAY']")).click();	
	
int medication_feedback_after_adding=driver.findElements(By.id("medicationfeedback")).size();

if(medication_feedback_after_adding==2+medication_feedback_before_adding)
{
	System.out.println("Medication status is displayed for all the medication");
}

for (int i=0; i<2; i++)
{
driver.findElement(By.id("backbtn")).click();
}
	
}


@Test(dependsOnMethods={"dailyDosageCreate"})
public void updateDailyDosage() throws InterruptedException
{
	//Clicking Treatment Summary
	Thread.sleep(2000L);
	driver.findElement(By.id("tab_usertools")).click();
	driver.findElement(By.xpath("//*[@text='Treatment Summary']")).click();
	
	
	//Counting the number of medication 
	
	Thread.sleep(2000L);
	int daily_dosage_count_existing=driver.findElements(By.xpath("//*[@id='dailyitems_holder']//*[@id='textView63']")).size();
	System.out.println("Total number of same medication in today section is " +daily_dosage_count_existing);
	
	//Switching back to All section
	
	
	driver.findElements(By.xpath("//*[@id='dailyitems_holder']//*[@id='textView63']")).get(0).click(); //Clicked on first dosage
	
	//Clicking in frequency
	driver.findElement(By.id("frequency_layout")).click();
	
	//Changing dosage from daily to weekly
	Thread.sleep(1000L);
	driver.findElement(By.xpath("//*[@text='Weekly']")).click();
	Thread.sleep(2000L);
	
	
	driver.findElement(By.id("sattext")).click();

	
	Thread.sleep(1000L);
	
	
	driver.findElement(By.id("addbutton")).click();
	driver.findElement(By.id("continueButton")).click();
	
	
	Assert.assertEquals(true,driver.findElement(By.id("weekly_text")).isDisplayed());
	
	Assert.assertEquals(true, driver.findElement(By.xpath("//*[@text='Saturday : 12:05PM']")).isDisplayed());
    
    System.out.println("Daily dosage is updated to weekly dosage");
    
    
    int daily_dosage_count_updating=driver.findElements(By.xpath("//*[@id='dailyitems_holder']//*[@id='textView63']")).size();
    
    System.out.println("Dose size after updating" + daily_dosage_count_updating);
    
    Thread.sleep(2000L);
    
    Assert.assertEquals(daily_dosage_count_existing-1, daily_dosage_count_updating);
    
    driver.findElement(By.id("backbtn")).click();
        
}


@Test(dependsOnMethods={"updateDailyDosage"}, priority=0)
public void deleteDailyDosage() throws InterruptedException
{
    
	driver.findElement(By.id("tab_usertools")).click();
	driver.findElement(By.xpath("//*[@text='Treatment Summary']")).click();
	
	driver.swipe(358, 783, 337, -72, 200);
	
    driver.swipe(306,520,130,520,300);
   
    Thread.sleep(1000L);
   driver.findElement(By.xpath("//*[@id='deletemed' and ./preceding-sibling::*[@id='editmed']]")).click();
   Thread.sleep(1000L);
   
   int number_of_dosages_afterDeleting_actual=driver.findElements(By.xpath("//*[@id='dailyitems_holder']//*[@id='textView63']")).size();

   int number_of_dosages_afterDeleting_expected=0;
   
   Assert.assertEquals(number_of_dosages_afterDeleting_actual, number_of_dosages_afterDeleting_expected);
   
   driver.findElement(By.id("backbtn")).click();
   
}


//WeeklyDosagecreate

@Test
public void weeklyDosageCreate() throws InterruptedException
{
	driver.findElement(By.id("tab_usertools")).click();
	driver.findElement(By.xpath("//*[@text='Treatment Summary']")).click();
	
	//Validating existing weekly dosages in ALL section
	driver.findElement(By.xpath("//*[@text='ALL']")).click();
	driver.swipe(358, 783, 337, -72, 200);
	
	int existing_weekly_dosage=driver.findElements(By.xpath("//*[@id='weeklyitems_holder']//*[@id='textView63']")).size();
	
	System.out.println("Number of weeky dosages present in ALL section: " +existing_weekly_dosage);
	
	//Adding a weekly medication
	driver.findElement(By.id("add_medication")).click();
	driver.findElement(By.id("searchbtn")).click();
	driver.findElement(By.id("searchtext")).sendKeys("A");
	driver.hideKeyboard();
	driver.findElement(By.xpath("//*[@text='APTIVUS']")).click();
	String medication_name=driver.findElement(By.xpath("//*[@text='APTIVUS']")).getText();
	
	Assert.assertEquals("APTIVUS", medication_name);
	
	
	driver.findElement(By.id("show_recommendation")).click();
	driver.findElement(By.id("recommendation_text")).isDisplayed();
	driver.findElement(By.xpath("//*[@text='Hide recommendations']")).click();
	
	
	driver.findElement(By.id("frequency_layout")).click();
	
	driver.findElement(By.xpath("//*[@text='Weekly']")).click();
	driver.findElement(By.id("sattext")).click();
	driver.findElement(By.xpath("//*[@id='time_of_dose_layout']")).click();
	
	driver.findElement(By.id("am_label")).click();
	
	driver.findElement(By.xpath("//*[@content-desc='8']")).click();
	driver.findElement(By.xpath("//*[@contentDescription='5']")).click();
	driver.findElement(By.xpath("//*[@text='OK']")).click();
	
	driver.findElement(By.id("addbutton")).click();
	Thread.sleep(1000L);
	
	
	//Adding New Dose
	driver.findElement(By.xpath("//*[@text='New Dose']")).click();
    driver.findElement(By.id("frequency_layout")).click();
	
	driver.findElement(By.xpath("//*[@text='Weekly']")).click();
	Thread.sleep(1000L);
	driver.findElement(By.id("wednestext")).click();
	driver.findElement(By.xpath("//*[@id='time_of_dose_layout']")).click();
	
	driver.findElement(By.id("am_label")).click();
	driver.findElement(By.xpath("//*[@content-desc='8']")).click();
	driver.findElement(By.xpath("//*[@contentDescription='5']")).click();
	driver.findElement(By.xpath("//*[@text='OK']")).click();
	
	driver.findElement(By.xpath("//*[@text='DONE']")).click();
	Thread.sleep(1000L);
	
	driver.findElement(By.id("continueButton")).click();
	Thread.sleep(1000L);
	
	int weekly_count_actual=driver.findElements(By.xpath("//*[@id='weeklyitems_holder']//*[@id='textView63']")).size();
	
	Assert.assertEquals(existing_weekly_dosage+2, weekly_count_actual);
	
	System.out.println("Same medication is added for both the time are added");
	
	driver.findElement(By.id("backbtn")).click();
	
	
}


@Test(dependsOnMethods={"weeklyDosageCreate"})
public void weeklyDosageUpdate() throws InterruptedException
{
	driver.findElement(By.id("tab_usertools")).click();
	driver.findElement(By.xpath("//*[@text='Treatment Summary']")).click();
	
	//Validating existing weekly dosages in ALL section
	driver.findElement(By.xpath("//*[@text='ALL']")).click();
	driver.swipe(358, 783, 337, -72, 200);
	
    int existing_weekly_dosage=driver.findElements(By.xpath("//*[@id='weeklyitems_holder']//*[@id='textView63']")).size();
	
	System.out.println("Number of weekly dosages already present in ALL section: " +existing_weekly_dosage);
	
	driver.findElements(By.xpath("//*[@id='weeklyitems_holder']//*[@id='textView63']")).get(0).click();
    driver.findElement(By.id("frequency_layout")).click();
	
	driver.findElement(By.xpath("//*[@text='Bi-Weekly']")).click();
	driver.findElement(By.id("sattextw2")).click();
	
    driver.findElement(By.xpath("//*[@id='time_of_dose_layout']")).click();
    driver.findElement(By.id("am_label")).click();
	driver.findElement(By.xpath("//*[@content-desc='8']")).click();
	driver.findElement(By.xpath("//*[@contentDescription='5']")).click();
	driver.findElement(By.xpath("//*[@text='OK']")).click();
	
	driver.findElement(By.id("addbutton")).click();
	Thread.sleep(1000L);
	driver.findElement(By.id("continueButton")).click();
	Thread.sleep(1000L);
	
	int weekly_dosage_after_update=driver.findElements(By.xpath("//*[@id='weeklyitems_holder']//*[@id='textView63']")).size();
	
	Assert.assertEquals(existing_weekly_dosage, weekly_dosage_after_update);
	
	String actual_bi_weekly=driver.findElement(By.xpath("//*[@id='biweeklyitems_holder']//*[@id='textView63']")).getText();
	
	Assert.assertEquals("Saturday : 08:05AM", actual_bi_weekly);
	
	System.out.println("Weekly dosage is changed to Bi-weekly dosage successfully");
	
	driver.findElement(By.id("backbtn")).click();
	
	
}



@Test(dependsOnMethods={"weeklyDosageUpdate"}, priority=1)
public void deleteWeeklyDosage() throws InterruptedException
{
    
	driver.findElement(By.id("tab_usertools")).click();

	driver.findElement(By.xpath("//*[@text='Treatment Summary']")).click();
	Thread.sleep(1000L);
	
	driver.swipe(358, 783, 337, -72, 200);
	
	//driver.findElement(By.xpath("//*[@id='weeklyitems_holder']//*[@id='textView63']")).swipe(SwipeElementDirection.LEFT, 2000);
	
	driver.swipe(306,520,130,520,300);
	
		  
    driver.findElement(By.xpath("//*[@id='deletemed' and ./preceding-sibling::*[@id='editmed']]")).click();
    
    Thread.sleep(1000L);
   
   int weekly_dosage_after_detele=driver.findElements(By.xpath("//*[@id='weeklyitems_holder']//*[@id='textView63']")).size();
  
   Assert.assertEquals(0,weekly_dosage_after_detele);
   
   driver.findElement(By.id("backbtn")).click();

}

@Test
public void biWeeklyDosageCreate() throws InterruptedException
{
	driver.findElement(By.id("tab_usertools")).click();
	driver.findElement(By.xpath("//*[@text='Treatment Summary']")).click();
	
	//Validating existing bi-weekly dosages in ALL section
	driver.findElement(By.xpath("//*[@text='ALL']")).click();
	driver.swipe(358, 783, 358, -72, 200);
    int existing_bi_weekly_dosage=driver.findElements(By.xpath("//*[@id='biweeklyitems_holder']//*[@id='textView63']")).size();
	
    
	System.out.println("Number of bi-weeky dosages present in ALL section: " +existing_bi_weekly_dosage);
	
	driver.findElement(By.id("add_medication")).click();
	
	driver.findElement(By.xpath("//*[@text='ATRIPLA']")).click();
	
    String medication_name=driver.findElement(By.xpath("//*[@text='ATRIPLA']")).getText();
	
	Assert.assertEquals("ATRIPLA", medication_name);
	
	
	driver.findElement(By.id("show_recommendation")).click();
	driver.findElement(By.id("recommendation_text")).isDisplayed();
	driver.findElement(By.xpath("//*[@text='Hide recommendations']")).click();
	driver.findElement(By.id("frequency_layout")).click();
	
	driver.findElement(By.xpath("//*[@text='Bi-Weekly']")).click();
	driver.findElement(By.id("sundtextw2")).click();
	
    driver.findElement(By.xpath("//*[@id='time_of_dose_layout']")).click();
	
	driver.findElement(By.id("am_label")).click();
	
	driver.findElement(By.xpath("//*[@content-desc='8']")).click();
	driver.findElement(By.xpath("//*[@contentDescription='5']")).click();
	driver.findElement(By.xpath("//*[@text='OK']")).click();
	
	driver.findElement(By.id("addbutton")).click();
	Thread.sleep(1000L);
	
	//Add a new Dose
	
	driver.findElement(By.xpath("//*[@text='New Dose']")).click();
    driver.findElement(By.id("frequency_layout")).click();
    
    driver.findElement(By.xpath("//*[@text='Bi-Weekly']")).click();
	Thread.sleep(1000L);
	driver.findElement(By.id("sattextw2")).click();
	driver.findElement(By.xpath("//*[@id='time_of_dose_layout']")).click();
	
	driver.findElement(By.id("am_label")).click();
	driver.findElement(By.xpath("//*[@content-desc='8']")).click();
	driver.findElement(By.xpath("//*[@contentDescription='5']")).click();
	driver.findElement(By.xpath("//*[@text='OK']")).click();
	
	driver.findElement(By.xpath("//*[@text='DONE']")).click();
	Thread.sleep(1000L);
	
	driver.findElement(By.id("continueButton")).click();
	Thread.sleep(1000L);
	
    int Bi_weekly_count_actual=driver.findElements(By.xpath("//*[@id='biweeklyitems_holder']//*[@id='textView63']")).size();
	
	Assert.assertEquals(existing_bi_weekly_dosage+2, Bi_weekly_count_actual);
	
	System.out.println("Same medication is added for both the time are added");
	
	for (int i=0; i<2; i++)
	{
	driver.findElement(By.id("backbtn")).click();
	}
	
} 


@Test(dependsOnMethods={"biWeeklyDosageCreate"})
public void biWeeklyUpdate() throws InterruptedException
{
	Thread.sleep(1000L);
	if(driver.findElements(By.id("backbtn")).size()>0)
	{
		driver.findElement(By.id("backbtn")).click();
	}
	driver.findElement(By.id("tab_usertools")).click();
	driver.findElement(By.xpath("//*[@text='Treatment Summary']")).click();
	
	//Validating existing bi-weekly dosages in ALL section
	driver.findElement(By.xpath("//*[@text='ALL']")).click();
	driver.swipe(358, 783, 358, -72, 200);
    int existing_bi_weekly_dosage=driver.findElements(By.xpath("//*[@id='biweeklyitems_holder']//*[@id='textView63']")).size();
	System.out.println("Number of bi-weeky dosages present in ALL section: " +existing_bi_weekly_dosage);
    
	driver.findElements(By.xpath("//*[@id='biweeklyitems_holder']//*[@id='textView63']")).get(0).click();
    driver.findElement(By.id("frequency_layout")).click();
	
	driver.findElement(By.xpath("//*[@text='Monthly']")).click();
	
	driver.findElement(By.xpath("//*[@text='Day of the month']")).click();
	driver.swipe(583,872, 583, 500, 300);
	driver.findElement(By.xpath("//*[@id='number_tablets_layout']//*[@id='editText12']")).click();
	
    driver.findElement(By.xpath("//*[@id='time_of_dose_layout']")).click();
    driver.findElement(By.id("am_label")).click();
	driver.findElement(By.xpath("//*[@content-desc='7']")).click();
	driver.findElement(By.xpath("//*[@contentDescription='10']")).click();
	driver.findElement(By.xpath("//*[@text='OK']")).click();
	
	driver.findElement(By.id("addbutton")).click();
	Thread.sleep(1000L);
	driver.findElement(By.id("continueButton")).click();
	Thread.sleep(1000L);
	
    int bi_weekly_dosage_after_update=driver.findElements(By.xpath("//*[@id='biweeklyitems_holder']//*[@id='textView63']")).size();
	
	Assert.assertEquals(existing_bi_weekly_dosage-1, bi_weekly_dosage_after_update);
	
	System.out.println("The bi-weekly medication is successfully changed to Monthly");
	
	driver.findElement(By.id("backbtn")).click();
	}

@Test(dependsOnMethods={"biWeeklyUpdate"}, priority=2)
public void biweeklyDosageDelete() throws InterruptedException
{
	driver.findElement(By.id("tab_usertools")).click();
	driver.findElement(By.xpath("//*[@text='Treatment Summary']")).click();
	
	driver.swipe(358, 783, 337, -72, 200);
	
    driver.swipe(306,520,130,520,300);
   
    Thread.sleep(1000L);
   driver.findElement(By.xpath("//*[@id='deletemed' and ./preceding-sibling::*[@id='editmed']]")).click();
   Thread.sleep(1000L);
   
   int bi_weekly_dosage_after_deleting=driver.findElements(By.xpath("//*[@id='biweeklyitems_holder']//*[@id='textView63']")).size();
   
   Assert.assertEquals(0, bi_weekly_dosage_after_deleting);
   
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





	

