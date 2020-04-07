package ModuleName_Pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends CommonMethods {
	
	@FindBy(xpath = "//label[text() = ' Username ']//following-sibling::input")
    private static WebElementFacade Username;

    @FindBy(xpath = "//label[text() = ' Password ']//following-sibling::input")
    private static WebElementFacade Password;
    
    @FindBy(xpath = "//input[@value = 'Sign in']")
    private WebElementFacade SignIn;
    
    @FindBy(xpath = "//div//strong[text() = ' Sign in to continue']")
    private WebElementFacade verifyLoginPage;
    
    @FindBy(xpath = "//h1[@data-i18n = '_overallPerformance_']")
    public static WebElementFacade verifyHomePage;
    
    @FindBy(xpath = "//span[text() = 'Logout']")
    private WebElementFacade btnLogout;
    
    By Loading = By.xpath("//div[text() = 'Loading...']");
    
    By Spinner = By.xpath("//div[text() = 'Loading...']//div[@class = 'spinner']");
    
    
    EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
    
    String expectedResultForLoginPage = "Sign in to continue";
    String expectedResultForHomePage = "Overall Performance";
    String actualResult;
    static String username = "";
	static String password = "";
    
    
    public LoginPage enterUserName(String UserName) throws InterruptedException {
    	Username.sendKeys(UserName);
        return this;
    }

    public LoginPage enterPassword(String PassWord) {
    	Password.sendKeys(PassWord);
        return this;
    }

    public LoginPage clickOnSignIn() {
    	SignIn.click();
    	WebDriverWait wait = new WebDriverWait(getDriver(), 25);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[text() = 'Loading...'] | //div[text() = 'Loading...']//div[@class = 'spinner']")));
//    	WebDriverWait wait = new WebDriverWait(getDriver(), 25);
//        wait.until(ExpectedConditions.elementToBeClickable(verifyHomePage));
        return this;
    }
    public void openUrl() {
		EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
        String loginPageURL = variables.getProperty("LoginPageURL");
        getDriver().get(loginPageURL);
        getDriver().manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(getDriver(), 25);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[text() = 'Loading...'] | //div[text() = 'Loading...']//div[@class = 'spinner']")));
//        WebDriverWait wait = new WebDriverWait(getDriver(), 25);
//        wait.until(ExpectedConditions.elementToBeClickable(verifyLoginPage));
        verifyLoginPage();
	}
    
    public void enterCorrectCredentials() throws InterruptedException
    {
		username = variables.getProperty("Username");
		password = variables.getProperty("Password");
		enterUserName(username);
		enterPassword(password);
    }
    
    public void userLogsInWithCorrectCredentials()
	{
		try {
			username = variables.getProperty("Username");
			password = variables.getProperty("Password");
			openUrl();
			verifyLoginPage();
			enterUserName(username);
			enterPassword(password);
			clickOnSignIn();
			verifyHomePage();
        } catch (Exception e) {
        	e.printStackTrace();
        }
		
	}
    
    public LoginPage verifyLoginPage() {
    	WebDriverWait wait = new WebDriverWait(getDriver(), 25);
        wait.until(ExpectedConditions.or(
        		ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//strong[text() = ' Sign in to continue']")) , 
        		 ExpectedConditions.presenceOfElementLocated(By.xpath("//div//strong[text() = ' Sign in to continue']"))));
        return this;

    }
    
    public LoginPage verifyHomePage() {
    	WebDriverWait wait = new WebDriverWait(getDriver(), 25);
        wait.until(ExpectedConditions.or(
        		ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@data-i18n = '_overallPerformance_']")) , 
        		 ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[@data-i18n = '_overallPerformance_']"))));
        return this;

    }
    
    public LoginPage logoutFromTheApplication() {
        waitFor(btnLogout).waitUntilClickable().click();
        return this;
    }


}
