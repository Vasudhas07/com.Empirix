package ModuleName_Pages;

import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import net.serenitybdd.core.Serenity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class CommonMethods extends PageObject {
	private static Logger logger = LoggerFactory.getLogger(CommonMethods.class);
	
	EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();

		
		public WebElement element(By element, String replacement) {
	        WebElement ele = null;
	        WebElement tmpWebElement = null;
	        String xpath = element.toString().substring(10).trim();
	        try {
	            xpath = xpath.replaceAll("\\$\\{.+\\}", replacement);
	            tmpWebElement = findBy(xpath);
	            ele = getDriver().findElement(By.xpath(xpath));
	        } catch (Exception ex) {
	                Assert.assertTrue("!! FAILED TO LOCATE ELEMENT ON WEBPAGE", false);
	            }
	        return ele;
	        }
		
		public WebElement element(WebElementFacade element, String replacement) {
	        WebElement ele = null;
	        WebElement tmpWebElement = null;
	        String xpath = element.toString().substring(10).trim();
	        try {
	            xpath = xpath.replaceAll("\\$\\{.+\\}", replacement);
	            tmpWebElement = findBy(xpath);
	            ele = getDriver().findElement(By.xpath(xpath));
	        } catch (Exception ex) {
	                Assert.assertTrue("!! FAILED TO LOCATE ELEMENT ON WEBPAGE", false);
	            }
	        return ele;
	        }
	        
	    }
