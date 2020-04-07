package RunnerTest;

import java.io.UnsupportedEncodingException;
import java.util.List;

import ModuleName_Pages.pageObjects;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class Empirix_StepDefinition extends pageObjects {
	
	@Steps
	String token = "";
	
	@Given("^I am on Login Page$")
	public void iAmOnLoginPage() {
		loginPage.openUrl();
	}
	
	@When("^I enter correct Username and Password$")
	public void iEnterCorrectUsernameAndPassword() throws InterruptedException {
		loginPage.enterCorrectCredentials();
	    
	}
	
	@When("^I click SignIn button$")
	public void iClickSignInButton() throws InterruptedException {
		loginPage.clickOnSignIn();
	    
	}
	
	@Then("^I should login successfully$")
	public void iShouldLoginSuccessfully() throws InterruptedException {
		loginPage.verifyHomePage();
	    
	}
	
	@Given("^I am LoggedIn with correct username and password$")
	public void iAmLoggedInWithCorrectUsernameAndPassword() {
		loginPage.userLogsInWithCorrectCredentials();
	    
	}
	
	@When("^I click on profile dropdown$")
	public void iClickOnProfileDropdown() {
		dashboardPage.clickOnProfileDropdown();
	    
	}
	
	@When("^I click on \"([^\"]*)\" link$")
	public void iClickOnLink(String language) {
		dashboardPage.clickOnLanguageLink(language);
	    
	}
	
	@When("^I accept alert$")
	public void iAcceptAlert() {
		dashboardPage.acceptingAlert();
	    
	}
	
	@Then("^application should be displayed in \"([^\"]*)\" language$")
	public void applicationDisplayedInLanguage(String language) throws UnsupportedEncodingException {
		dashboardPage.verifyApplicationInAnotherLanguage(language);
	    
	}
	
	@Then("^I should see following tabs in \"([^\"]*)\"$")
	public void iShouldSeeFollowingTabsIn(String language , DataTable arg) throws UnsupportedEncodingException {
		List<List<String>> data = arg.raw();
		List<String> columns = data.get(0);
		for (String tabName : columns) {
		dashboardPage.verifyTabs(language , tabName);
		}
	    
	}
	
	@Then("^I should be able to access following tabs in \"([^\"]*)\"$")
	public void iShouldBeAbleToAccessFollowingTabsIn(String language , DataTable arg) throws UnsupportedEncodingException {
		List<List<String>> data = arg.raw();
		List<String> columns = data.get(0);
		for (String tabName : columns) {
		dashboardPage.accessTabs(language , tabName);
		}
	    
	}


	@When("^I select \"([^\"]*)\"$")
	public void iselect(String link) {
		dashboardPage.selectClientLink(link);
	    
	}
	
	
	@Then("^I should be able to see Client Information as follows$")
	public void iShouldBeAbleToSeeClientInformationAsFollows(DataTable arg) {
		List<List<String>> data = arg.raw();
		List<String> columns = data.get(0);
		for (String clientInformation : columns) {
			dashboardPage.verifyClientInformation(clientInformation);
		}
	    
	}
	
	
	@And("^I logout from the application$")
	public void i_logout_from_the_application() {
		loginPage.logoutFromTheApplication();
	}
}
