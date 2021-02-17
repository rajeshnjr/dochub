package com.ora.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Page {
	WebDriver driver;

	Page(WebDriver driver) {
		this.driver = driver;
		this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public WebElement getElementByXpath(String sElement) {
		return driver.findElement(By.xpath(sElement));
	}

	public List<WebElement> getElementsByXpath(String sElement) {
		return driver.findElements(By.xpath(sElement));
	}

	public void XpathElementClick(String sElement) {

		getElementByXpath(sElement).click();
	}

	public String getXpathElementText(String sElement) {
		return getElementByXpath(sElement).getText();
	}
	
	public int getXpathElementSize(String sElement) {
		return getElementsByXpath(sElement).size();
	}
	
	public List<String> getElementStrings(String sElement){
		List<String> allAvailableStrings = new ArrayList<String>();
		for (int i = 1; i <= getXpathElementSize(sElement); i++) {
		allAvailableStrings.add(getXpathElementText("("+sElement+")"+"[" + i + "]"));
		}
		return allAvailableStrings;
	}

	public boolean IsElementPresent(String sElement) {
		try {
			return getElementByXpath(sElement).isDisplayed();
		} catch (Exception var1) {
			return false;
		}
	}

	public boolean IsElementByXpathDisplayed(String sElement) {
		return getElementByXpath(sElement).isDisplayed();
	}

	public void actionClick(String sElement) {
		WebElement element = getElementByXpath(sElement);
		
		Actions builder = new Actions(driver);
		builder.moveToElement(element).click().perform();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void highLighterMethod(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 10x solid yellow;');", element);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		js.executeScript("arguments[0].setAttribute('style', '');", element);

	}

}
