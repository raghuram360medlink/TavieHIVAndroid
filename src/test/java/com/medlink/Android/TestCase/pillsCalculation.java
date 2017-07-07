package com.medlink.Android.TestCase;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.experitest.appium.SeeTestAndroidDriver;
import com.experitest.appium.SeeTestAndroidElement;
import com.tavieHIV.Android.Util.Util;

public class pillsCalculation {
	
	static SeeTestAndroidDriver<SeeTestAndroidElement> driver;
	@BeforeClass
	public void driverInitiation() throws MalformedURLException
	{
		Util utilpC=new Util();
		driver=utilpC.setUp();
	}
	
	@Test
	public void deviceDayCalculation()
	{
		Calendar calendar = Calendar.getInstance();
		Date date=calendar.getTime();
		System.out.println(new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime()));
	}
	

}
