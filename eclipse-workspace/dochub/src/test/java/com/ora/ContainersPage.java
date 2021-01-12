package com.ora;

import java.util.List;

import org.openqa.selenium.WebDriver;

public class ContainersPage extends Page{

	String sLandingPage = "//div[@class='dtabs']/button[contains(@class,'selected') and text()='@landingsite']";
	String sFilter = "//div[text()='@filtertype']//following-sibling::div//label";
	String filtercheckbox = "[contains(text(),'@filtertype')]/parent::div//*[name()='svg'])[1]";
	String imglbl = "(//div[text()='Filters ']//following-sibling::div/div[@id='imageFilterList'][//label//span[text()='@filtertype']]//*[name()='svg'])[1]";
	String filterContentpath = "//div[@data-testid='currentFilters']/div[text()='@filtercontent']";
	String sFilterCheckBoxSVG = "//label[text()='@checkbox']/parent::div//div/*[name()='svg' and contains(@style,'143, 158, 168') and not(@fill)]";
	String currentFilterPath="//div[@data-testid='currentFilters']/div";
	WebDriver driver;

	 ContainersPage(WebDriver driver) {
		 super(driver);
		 this.driver = driver;
	}

	public boolean IsLandingPage(String LandingPageTitle) {
		highLighterMethod(getElementByXpath(sLandingPage.replaceFirst("@landingsite", LandingPageTitle)));
		return IsElementByXpathDisplayed(sLandingPage.replaceFirst("@landingsite", LandingPageTitle));
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
		return getXpathElementSize(getFilterLbl(sFilterType));
	}

	public List<String> getCheckBoxLabelFor(String sFilterType) {
		String sLabels = getFilterLbl(sFilterType);
		if (sFilterType.contains("Images")) {
			sLabels = sLabels + "/div/span[contains(@class,'label')]";
		}
		return getElementStrings(sLabels);
	}

	public void ClickImgFilterCheckbox(String sFilterLabel) {
		actionClick(imglbl.replaceFirst("@filtertype", sFilterLabel));
	}

	public void ClickFilterCheckbox(String sFilterLabel) {
		String filterlbl = "(" + getFilterLbl("Filters ") + getFilterLblcheckbox(sFilterLabel);
		actionClick(filterlbl);
	}

	public void ClickFilterCheckboxes(List<String> sFilterLabels) {
		for (String sFilterLabel : sFilterLabels) {
			ClickFilterCheckbox(sFilterLabel);
		}
	}

	public List<String> getFilterContentsDisplayed() {
		return getElementStrings(currentFilterPath);
	}

	public void CloseFilterContentDisplayed(String filterContent) {
		XpathElementClick(filterContentpath.replaceFirst("@filtercontent", filterContent) + "/*[name()='svg']/*[name()='path']");
	}

}
