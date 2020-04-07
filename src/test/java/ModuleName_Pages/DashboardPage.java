package ModuleName_Pages;

import java.io.UnsupportedEncodingException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;

public class DashboardPage extends CommonMethods {
	
	By inputBox = By.xpath("//h4[text() = 'Billing address']//parent::div//input[@id = '${inputBoxName}']");
	By dropdown = By.xpath("//select[@id = '${dropdownName}']");
	By dropdownOption = By.xpath("//select[@id = '${dropdownName}']/option[text() = '${dropdownOption}']");
	By checkbox = By.xpath("//label[text() = '${checkboxName}']");
	By button = By.xpath("//button[text() = '${buttonName}']");
	By languageLink = By.xpath("//a[text() = '${language}']");
	By tabs = By.xpath("//a[@data-i18n = '${tabname}']");
	By filterBox = By.xpath("//div[@class = 'side-navigation']//span[@data-i18n = '${tabname}']");
	By clienLink = By.xpath("//a[text() = 'QA_traininguser20(Empirix_QA_Training)']//following-sibling::ul//span[text() = '${link}']");
	
	By ClientInfo = By.xpath("//label[text() = '${clientInformation}']//following-sibling::label");
//	By Description = By.xpath("//label[text() = 'Description']//following-sibling::label");
//	By SubscriptionStartDate = By.xpath("//label[text() = 'Subscription Start Date']//following-sibling::label");
//	By SubscriptionEndDate = By.xpath("//label[text() = 'Subscription End Date']//following-sibling::label");
//	By MaximumLoadTests = By.xpath("//label[text() = 'Maximum Load Tests']//following-sibling::label");
//	By MaximumVoiceWatchTests = By.xpath("//label[text() = 'Maximum VoiceWatch Tests']//following-sibling::label");
//	By MaximumScenarios = By.xpath("//label[text() = 'Maximum Scenarios']//following-sibling::label");
//	By MinimumTestSchedulePeriod = By.xpath("//label[text() = 'Minimum Test Schedule Period (mins)']//following-sibling::label");
	
	@FindBy(xpath = "//span[@data-i18n = '_total_']")
	private WebElementFacade alertTotal;
	
	@FindBy(xpath = "//span[@data-i18n = '_critical_']")
	private WebElementFacade alertCritical;
	
	@FindBy(xpath = "//span[@data-i18n = '_warning_']")
	private WebElementFacade alertWarning;
	
	@FindBy(xpath = "//a[text() = 'QA_traininguser20(Empirix_QA_Training)']//span")
    private static WebElementFacade profileDropdown;
	
	EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();

	
	public void selectClientLink(String link)
	{
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);",
    			element(clienLink, link));
    	((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", element(clienLink, link));
	}
	
	public void clickButton(String buttonName)
	{
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);",
    			element(button, buttonName));
    	((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", element(button, buttonName));
	}
	
	public void clickOnProfileDropdown()
	{
		 WebDriverWait wait = new WebDriverWait(getDriver(), 40);
	        wait.until(ExpectedConditions.elementToBeClickable(profileDropdown));
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);",
				profileDropdown);
    	((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", profileDropdown);
	}
	
	public void clickOnLanguageLink(String language)
	{
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);",
				element(languageLink, language));
    	((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", element(languageLink, language));
    	waitABit(8000);
	}
	
	public void acceptingAlert()
	{
		try
		{
        getDriver().switchTo().alert().accept();
		}
		catch(NoAlertPresentException e)
		{
			e.printStackTrace();
		}
    }
	
	public void verifyApplicationInAnotherLanguage(String language) throws UnsupportedEncodingException
	{
		if(language.equalsIgnoreCase("Japanese"))
		{
			waitABit(10000);
			Assert.assertTrue(LoginPage.verifyHomePage.getText().equals("総合成績"));
		}
		else if(language.equalsIgnoreCase("English"))
		{
			WebDriverWait wait = new WebDriverWait(getDriver(), 30);
			wait.until(ExpectedConditions.and(
    		ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id = 'overallChartDiv']")) , 
   		 ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id = 'overallChartDiv']"))));
//			String OverallPerformanceInEnglish = variables.getProperty("ApplicationInEnglish");
			Assert.assertTrue(LoginPage.verifyHomePage.getText().equals("Overall Performance"));
		}
		else
		{
//			String OverallPerformanceInEnglish = variables.getProperty("ApplicationInEnglish");
			Assert.assertTrue(LoginPage.verifyHomePage.getText().equals("Overall Performance"));
		}
	}
	
	public void verifyTabs(String language , String tabName) throws UnsupportedEncodingException
	{
		String tabname = "_" +  tabName.toLowerCase() + "_";
		if(language.equalsIgnoreCase("Japanese"))
		{
			waitABit(10000);
			Assert.assertTrue(element(tabs , tabname).isDisplayed());
			if(tabName.equalsIgnoreCase("Dashboard"))
			{
			Assert.assertTrue(element(tabs , tabname).getText().equals("ダッシュボード"));
			}
			else if(tabName.equalsIgnoreCase("Alerts"))
			{
				Assert.assertTrue(element(tabs , tabname).getText().equals("アラート"));
			}
			else if(tabName.equalsIgnoreCase("Tests"))
			{
					Assert.assertTrue(element(tabs , tabname).getText().equals("テスト"));
			}
			else if(tabName.equalsIgnoreCase("Variables"))
			{
					Assert.assertTrue(element(tabs , tabname).getText().equals("変数"));
			}
			else if(tabName.equalsIgnoreCase("Notifications"))
			{
					Assert.assertTrue(element(tabs , tabname).getText().equals("通知"));
			}
		}
		else if(language.equalsIgnoreCase("English"))
		{
			WebDriverWait wait = new WebDriverWait(getDriver(), 30);
			wait.until(ExpectedConditions.and(
    		ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id = 'overallChartDiv']")) , 
   		 ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id = 'overallChartDiv']"))));
//			String OverallPerformanceInEnglish = variables.getProperty("ApplicationInEnglish");
			Assert.assertTrue(element(tabs , tabname).isDisplayed());
			if(tabName.equalsIgnoreCase("Dashboard"))
			{
			Assert.assertTrue(element(tabs , tabname).getText().equals("Dashboard"));
			}
			else if(tabName.equalsIgnoreCase("Alerts"))
			{
				Assert.assertTrue(element(tabs , tabname).getText().equals("Alerts"));
			}
			else if(tabName.equalsIgnoreCase("Tests"))
			{
					Assert.assertTrue(element(tabs , tabname).getText().equals("Tests"));
			}
			else if(tabName.equalsIgnoreCase("Variables"))
			{
					Assert.assertTrue(element(tabs , tabname).getText().equals("Variables"));
			}
			else if(tabName.equalsIgnoreCase("Notifications"))
			{
					Assert.assertTrue(element(tabs , tabname).getText().equals("Notifications"));
			}
		}
		else
		{
//			String OverallPerformanceInEnglish = variables.getProperty("ApplicationInEnglish");
			Assert.assertTrue(element(tabs , tabname).isDisplayed());
			if(tabName.equalsIgnoreCase("Dashboard"))
			{
			Assert.assertTrue(element(tabs , tabname).getText().equals("Dashboard"));
			}
			else if(tabName.equalsIgnoreCase("Alerts"))
			{
				Assert.assertTrue(element(tabs , tabname).getText().equals("Alerts"));
			}
			else if(tabName.equalsIgnoreCase("Tests"))
			{
					Assert.assertTrue(element(tabs , tabname).getText().equals("Tests"));
			}
			else if(tabName.equalsIgnoreCase("Variables"))
			{
					Assert.assertTrue(element(tabs , tabname).getText().equals("Variables"));
			}
			else if(tabName.equalsIgnoreCase("Notifications"))
			{
					Assert.assertTrue(element(tabs , tabname).getText().equals("Notifications"));
			}
		}
	}
	
	public void accessTabs(String language , String tabName) throws UnsupportedEncodingException
	{
		String tabname = "_" +  tabName.toLowerCase() + "_";
		if(language.equalsIgnoreCase("Japanese"))
		{
//			String OverallPerformanceInJapanese = variables.getProperty("ApplicationInJapanese");
			Assert.assertTrue(element(tabs , tabname).isEnabled());
			element(tabs , tabname).click();
			waitABit(5000);
			if(tabName.equalsIgnoreCase("Dashboard"))
			{
				WebDriverWait wait = new WebDriverWait(getDriver(), 30);
				wait.until(ExpectedConditions.or(
	    		ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id = 'overallChartDiv']")) , 
	   		 ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id = 'overallChartDiv']"))));
				Assert.assertTrue(LoginPage.verifyHomePage.isDisplayed());
			Assert.assertTrue(LoginPage.verifyHomePage.getText().equals("総合成績"));
			}
			else if(tabName.equalsIgnoreCase("Alerts"))
			{
				WebDriverWait wait = new WebDriverWait(getDriver(), 30);
				wait.until(ExpectedConditions.and(
	    		ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-i18n = '_total_']")) , 
	   		 ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-i18n = '_critical_']")) ,
	   		ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-i18n = '_warning_']"))));
				Assert.assertTrue(alertTotal.isDisplayed());
				Assert.assertTrue(alertTotal.getText().equals("合計"));
				Assert.assertTrue(alertCritical.isDisplayed());
				Assert.assertTrue(alertCritical.getText().equals("重大"));
				Assert.assertTrue(alertWarning.isDisplayed());
				Assert.assertTrue(alertWarning.getText().equals("警告"));
			}
			else if(tabName.equalsIgnoreCase("Tests"))
			{
				WebDriverWait wait = new WebDriverWait(getDriver(), 30);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class = 'side-navigation']//span[@data-i18n = '_tests_']")));
				Assert.assertTrue(element(filterBox , tabname).isDisplayed());
					Assert.assertTrue(element(filterBox , tabname).getText().equals("テスト"));
			}
			else if(tabName.equalsIgnoreCase("Variables"))
			{
				WebDriverWait wait = new WebDriverWait(getDriver(), 30);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class = 'side-navigation']//span[@data-i18n = '_variables_']")));
				Assert.assertTrue(element(filterBox , tabname).isDisplayed());
					Assert.assertTrue(element(filterBox , tabname).getText().equals("変数"));
			}
			else if(tabName.equalsIgnoreCase("Notifications"))
			{
				WebDriverWait wait = new WebDriverWait(getDriver(), 30);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class = 'side-navigation']//span[@data-i18n = '_notifications_']")));
				Assert.assertTrue(element(filterBox , tabname).isDisplayed());
					Assert.assertTrue(element(filterBox , tabname).getText().equals("通知"));
			}
		}
		else if(language.equalsIgnoreCase("English"))
		{
			Assert.assertTrue(element(tabs , tabname).isEnabled());
			element(tabs , tabname).click();
			if(tabName.equalsIgnoreCase("Dashboard"))
			{
				WebDriverWait wait = new WebDriverWait(getDriver(), 30);
				wait.until(ExpectedConditions.or(
	    		ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id = 'overallChartDiv']")) , 
	   		 ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id = 'overallChartDiv']"))));
				Assert.assertTrue(LoginPage.verifyHomePage.isDisplayed());
			Assert.assertTrue(LoginPage.verifyHomePage.getText().equals("Overall Performance"));
			}
			else if(tabName.equalsIgnoreCase("Alerts"))
			{
				WebDriverWait wait = new WebDriverWait(getDriver(), 30);
				wait.until(ExpectedConditions.and(
	    		ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-i18n = '_total_']")) , 
	   		 ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-i18n = '_critical_']")) ,
	   		ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-i18n = '_warning_']"))));
				Assert.assertTrue(alertTotal.isDisplayed());
				Assert.assertTrue(alertTotal.getText().equals("Total"));
				Assert.assertTrue(alertCritical.isDisplayed());
				Assert.assertTrue(alertCritical.getText().equals("Critical"));
				Assert.assertTrue(alertWarning.isDisplayed());
				Assert.assertTrue(alertWarning.getText().equals("Warning"));
			}
			else if(tabName.equalsIgnoreCase("Tests"))
			{
				WebDriverWait wait = new WebDriverWait(getDriver(), 30);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class = 'side-navigation']//span[@data-i18n = '_tests_']")));
				Assert.assertTrue(element(filterBox , tabname).isDisplayed());
					Assert.assertTrue(element(filterBox , tabname).getText().equals("Tests"));
			}
			else if(tabName.equalsIgnoreCase("Variables"))
			{
				WebDriverWait wait = new WebDriverWait(getDriver(), 30);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class = 'side-navigation']//span[@data-i18n = '_variables_']")));
				Assert.assertTrue(element(filterBox , tabname).isDisplayed());
					Assert.assertTrue(element(filterBox , tabname).getText().equals("Variables"));
			}
			else if(tabName.equalsIgnoreCase("Notifications"))
			{
				WebDriverWait wait = new WebDriverWait(getDriver(), 30);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class = 'side-navigation']//span[@data-i18n = '_notifications_']")));
				Assert.assertTrue(element(filterBox , tabname).isDisplayed());
					Assert.assertTrue(element(filterBox , tabname).getText().equals("Notifications"));
			}
		}
		else
		{
//			String OverallPerformanceInEnglish = variables.getProperty("ApplicationInEnglish");
			Assert.assertTrue(element(tabs , tabname).isEnabled());
			element(tabs , tabname).click();
			if(tabName.equalsIgnoreCase("Dashboard"))
			{
				Assert.assertTrue(LoginPage.verifyHomePage.isDisplayed());
			Assert.assertTrue(LoginPage.verifyHomePage.getText().equals("Dashboard"));
			}
			else if(tabName.equalsIgnoreCase("Alerts"))
			{
				Assert.assertTrue(alertTotal.isDisplayed());
				Assert.assertTrue(alertTotal.getText().equals("Total"));
				Assert.assertTrue(alertCritical.isDisplayed());
				Assert.assertTrue(alertCritical.getText().equals("Critical"));
				Assert.assertTrue(alertWarning.isDisplayed());
				Assert.assertTrue(alertWarning.getText().equals("Warning"));
			}
			else if(tabName.equalsIgnoreCase("Tests"))
			{
				Assert.assertTrue(element(filterBox , tabname).isDisplayed());
					Assert.assertTrue(element(filterBox , tabname).getText().equals("Tests"));
			}
			else if(tabName.equalsIgnoreCase("Variables"))
			{
				Assert.assertTrue(element(filterBox , tabname).isDisplayed());
					Assert.assertTrue(element(filterBox , tabname).getText().equals("Variables"));
			}
			else if(tabName.equalsIgnoreCase("Notifications"))
			{
				Assert.assertTrue(element(filterBox , tabname).isDisplayed());
					Assert.assertTrue(element(filterBox , tabname).getText().equals("Notifications"));
			}
		}
	}
	
	
	public void verifyClientInformation(String clientInformation)
	{
		String clientinfo = variables.getProperty(clientInformation.replaceAll(" ", ""));
		Assert.assertTrue(element(ClientInfo , clientInformation).getText().equals(clientinfo));
		
	}

}
