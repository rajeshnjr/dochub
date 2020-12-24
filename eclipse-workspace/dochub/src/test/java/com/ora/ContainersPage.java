package com.ora;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ContainersPage {

	String sLandingPage = "//div[@class='dtabs']/button[contains(@class,'selected') and text()='@landingsite']";
	String sFilter = "//div[text()='@filtertype']//following-sibling::div//label";
	String filtercheckbox = "[contains(text(),'@filtertype')]/parent::div//*[name()='svg'])[1]";
	String imglbl = "(//div[text()='Filters ']//following-sibling::div/div[@id='imageFilterList'][//label//span[text()='@filtertype']]//*[name()='svg'])[1]";
	String filterContentpath = "//div[@data-testid='currentFilters']/div[text()='@filtercontent']";
	String sFilterCheckBoxSVG = "//label[text()='@checkbox']/parent::div//div/*[name()='svg' and contains(@style,'143, 158, 168') and not(@fill)]";
	WebDriver driver;

	public ContainersPage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean IsElementPresent(String sElement) {
		try {
			return driver.findElement(By.xpath(sElement)).isDisplayed();
		} catch (Exception var1) {
			return false;
		}
	}

	public boolean IsLandingPage(String LandingPageTitle) {
		By objByLocator = By.xpath(sLandingPage.replaceFirst("@landingsite", LandingPageTitle));
		return driver.findElement(objByLocator).isDisplayed();
	}

	public boolean IsCheckBoxUnChecked(String sCheckBox) {
		return IsElementPresent(sFilterCheckBoxSVG.replaceFirst("@checkbox", sCheckBox));
	}

	public boolean IsFilterContentDisplayed(String filterContent) {
		return IsElementPresent(filterContentpath.replaceFirst("@filtercontent", filterContent));
	}

	public String getFilterLbl(String sFilterType) {
		return sFilter.replaceFirst("@filtertype", sFilterType);
	}

	public String getFilterLblcheckbox(String sFilter) {
		return filtercheckbox.replaceFirst("@filtertype", sFilter);
	}

	public int getCheckBoxCountFor(String sFilterType) {
		return driver.findElements(By.xpath(getFilterLbl(sFilterType))).size();
	}

	public List<String> getCheckBoxLabelFor(String sFilterType) {
		List<String> allAvailableCheckBox = new ArrayList<String>();
		String sLabels = getFilterLbl(sFilterType);
		List<WebElement> labels = driver.findElements(By.xpath(sLabels));
		if (sFilterType.contains("Images")) {
			sLabels = sLabels + "/div/span[contains(@class,'label')]";
		}
		for (int i = 1; i <= labels.size(); i++) {
			allAvailableCheckBox.add(driver.findElement(By.xpath("(" + sLabels + ")[" + i + "]")).getText());
		}
		return allAvailableCheckBox;
	}

	public void ClickImgFilterCheckbox(String sFilterLabel) {
		WebElement element = driver.findElement(By.xpath(imglbl.replaceFirst("@filtertype", sFilterLabel)));
		Actions builder = new Actions(driver);
		builder.moveToElement(element).click().perform();
		;

	}

	public void ClickFilterCheckbox(String sFilterLabel) {
		String filterlbl = "(" + getFilterLbl("Filters ") + getFilterLblcheckbox(sFilterLabel);
		WebElement element = driver.findElement(By.xpath(filterlbl));
		Actions builder = new Actions(driver);
		builder.moveToElement(element).click().perform();
		;
	}

	public void ClickFilterCheckboxes(List<String> sFilterLabels) {
		for (String sFilterLabel : sFilterLabels) {
			ClickFilterCheckbox(sFilterLabel);
		}
	}

	public List<String> getFilterContentsDisplayed() {
		List<String> allAvailableFilters = new ArrayList<String>();
		List<WebElement> filtercons = driver.findElements(By.xpath("//div[@data-testid='currentFilters']/div"));
		for (int i = 1; i <= filtercons.size(); i++) {
			allAvailableFilters.add(
					driver.findElement(By.xpath("(//div[@data-testid='currentFilters']/div)[" + i + "]")).getText());
		}
		return allAvailableFilters;
	}

	public void CloseFilterContentDisplayed(String filterContent) {
		driver.findElement(By.xpath(
				filterContentpath.replaceFirst("@filtercontent", filterContent) + "/*[name()='svg']/*[name()='path']"))
				.click();
	}

}
