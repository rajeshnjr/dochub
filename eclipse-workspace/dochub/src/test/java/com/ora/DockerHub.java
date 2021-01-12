package com.ora;

import org.testng.annotations.Test;

import com.ora.ContainersPage;
import com.ora.Base.UITest;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class DockerHub extends UITest{
	private static Logger log = Logger.getLogger(DockerHub.class.getName());
	String sURL = "https://hub.docker.com/search";
	WebDriver driver;
	ContainersPage containerspage;
	@Parameters({ "browser" })
	@BeforeClass
	public void setUp(String browser) throws Exception {
		driver = CreateDriver(browser,sURL);
	}

	@Test
	public void testContainersPage() throws InterruptedException {
		containerspage = new ContainersPage(driver);

		// Verify that the user lands in the �Containers� tab by default
		log.info("Verify that the user lands in the �Containers� tab by default");
		boolean landingpage = containerspage.IsLandingPage("Containers");
		Assert.assertTrue(landingpage, "Containers is not the landing page");
		log.info("PASSED : Landed on Containers page");

		// Verify there are 2 checkboxes under Images with labels �Verified Publisher�
		// and �Official Images�
		log.info(
				"Verify there are 2 checkboxes under Images with labels \"Verified Publisher\" and \"Official Images\"");
		List<String> availableFilters = containerspage.getCheckBoxLabelFor("Images");
		Assert.assertTrue(availableFilters.size() == 2, "Images filter check boxes are not equal to 2");
		log.info("PASSED : Images filter check boxes are equal to 2");

		log.info(
				"Under Categories, verify the following checkboxes are present\n1.Analytics\n2.Base Images\n3.Databases\n"
						+ "4.Storage");
		List<String> expectedFilters = new ArrayList<String>();
		expectedFilters.add("Verified Publisher");
		expectedFilters.add("Official Images");
		Assert.assertTrue(availableFilters.equals(expectedFilters), "Expected Checkboxes are not available");
		log.info("PASSED : Expected Checkboxes are available");

		// Verify the filter �Publisher Content� is shown at the top of the content
		log.info("Click the \"Verified Publisher\" checkbox");
		containerspage.ClickImgFilterCheckbox("Verified Publisher");
		Thread.sleep(5000);
		Assert.assertTrue(containerspage.IsFilterContentDisplayed("Publisher Content"),
				"Publisher Content is not shown at the top of the content");
		log.info("PASSED : Publisher Content is shown at the top of the content");

		// Verify the additional filters are shown at the top of the content
		log.info("Click Base Images and Database Checkboxes");
		availableFilters.clear();
		expectedFilters.clear();
		expectedFilters.add("Base Images");
		expectedFilters.add("Databases");
		containerspage.ClickFilterCheckboxes(expectedFilters);
		log.info("Verify the additional filters are shown at the top of the content");
		expectedFilters.add("Publisher Content");
		availableFilters = containerspage.getFilterContentsDisplayed();
		Assert.assertTrue(availableFilters.equals(expectedFilters),
				"Additional filters are not shown at the top of the content");
		log.info("PASSED : Additional filters are shown at the top of the content");

		// Verify the corresponding checkbox in the left filter pane is also unchecked
		log.info("Click the close icon (X) on the database filter");
		containerspage.CloseFilterContentDisplayed("Databases");
		Assert.assertTrue(containerspage.IsCheckBoxUnChecked("Databases"),
				"Databases checkbox in the left filter pane is not unchecked");
		log.info("PASSED : Databases checkbox in the left filter pane is unchecked");
	}

	@AfterClass
	public void terminateBrowser() {
		driver.close();
	}

}
