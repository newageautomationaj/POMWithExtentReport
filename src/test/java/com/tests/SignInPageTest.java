package com.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.vsysq.base.ExtentTestManager;
import com.vsysq.base.TestBase;
import com.vsysq.pages.BasePage;
import com.vsysq.pages.SignInPage;

public class SignInPageTest extends TestBase {

	private WebDriver driver;
	private SignInPage signInPage;
	private BasePage basePage;

	@BeforeClass
	public void setUp() {
		//Get driver
		driver = getDriver();
	}

	@Test
	public void verifySignInFunction() {
		System.out.println("Sign In functionality details...");
		basePage = new BasePage(driver);
		signInPage = basePage.clickSignInBtn();
		Assert.assertTrue(signInPage.verifySignInPageTitle(), "Sign In page title doesn't match");
		ExtentTestManager.getTest().log(Status.INFO, "Sign In page title successfully verified");
		Assert.assertTrue(signInPage.verifySignInPageText(), "Page text not matching");
		ExtentTestManager.getTest().log(Status.INFO, "Page text successfully verified");
		Assert.assertTrue(signInPage.verifySignIn(), "Unable to sign in");
		ExtentTestManager.getTest().log(Status.INFO, "Sign message text successfully verified");
	}

	@Test
	public void SignInPageTestOne() {
		System.out.println("Hey im in example test One");
		ExtentTestManager.getTest().log(Status.INFO, "Hey im in example test One");
	}

	@Test
	public void SignInPageTestTwo() {
		System.out.println("Hey im in example test two");
		ExtentTestManager.getTest().log(Status.INFO, "Hey im in example test two");
	}

	@Test
	public void SignInPageTestThree() {
		System.out.println("Hey im in example test three");
		ExtentTestManager.getTest().log(Status.INFO, "Hey im in example test three");
	}
}