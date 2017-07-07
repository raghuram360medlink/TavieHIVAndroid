package com.tavieHIV.Android.Util;
	//package <set your test package>;
	import com.experitest.appium.*;
	import io.appium.java_client.remote.AndroidMobileCapabilityType;
	import io.appium.java_client.remote.MobileCapabilityType;
	import org.openqa.selenium.remote.DesiredCapabilities;
	import java.net.URL;
	import java.net.MalformedURLException;

	public class Util {
	    private String host = "localhost";
	    private int port = 8889;
	   /* private String reportDirectory = "reports";
	    private String reportFormat = "xml";*/
	    private String testName = "Tavie HIV";
	    public SeeTestAndroidDriver<SeeTestAndroidElement> driver; 
	    
	    public SeeTestAndroidDriver<SeeTestAndroidElement> setUp() throws MalformedURLException {
	    	DesiredCapabilities dc = new DesiredCapabilities();
	        /*dc.setCapability(SeeTestCapabilityType.REPORT_DIRECTORY, reportDirectory);
	        dc.setCapability(SeeTestCapabilityType.REPORT_FORMAT, reportFormat);*/
	        dc.setCapability(SeeTestCapabilityType.TEST_NAME, testName);
	        dc.setCapability(MobileCapabilityType.UDID, "42008bccfa6e13e7");
	        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "tavie.patient.hiv.ohhs.production.v3");
	        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.medlink.mehdi.tavie.SplashScreen");
	        dc.setCapability(MobileCapabilityType.NO_RESET, true);
	        driver = new SeeTestAndroidDriver<SeeTestAndroidElement>(new URL("http://"+host+":"+port), dc);
	        return driver;
	    
	    }
	    
	   
	}


