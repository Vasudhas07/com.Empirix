Feature: Demo web page

@Automated
Scenario: To verify user is able to login with correct username and password
Given I am on Login Page
When I enter correct Username and Password
And I click SignIn button
Then I should login successfully
And I click on profile dropdown
And I logout from the application


@Automated
Scenario: To verify user is able to switch the language to Japanese from the profile dropdown and vice versa
Given I am LoggedIn with correct username and password
When I click on profile dropdown
And I click on "Japanese" link
#And I accept alert
Then application should be displayed in "Japanese" language
When I click on profile dropdown
And I click on "English" link
#And I accept alert
Then application should be displayed in "English" language
And I click on profile dropdown
And I logout from the application


@Automated
Scenario: To verify user is able to access the required tabs and is able to access them in English as well as Japanese
Given I am LoggedIn with correct username and password
When I click on profile dropdown
And I click on "Japanese" link
#And I accept alert
Then I should see following tabs in "Japanese"
| Dashboard | Alerts | Tests | Variables | Notifications | Dashboard |
And I should be able to access following tabs in "Japanese"
| Dashboard | Alerts | Tests | Variables | Notifications | Dashboard |
When I click on profile dropdown
And I click on "English" link
#And I accept alert
Then I should see following tabs in "English"
| Dashboard | Alerts | Tests | Variables | Notifications | Dashboard |
And I should be able to access following tabs in "English"
| Dashboard | Alerts | Tests | Variables | Notifications | Dashboard |
And I click on profile dropdown
And I logout from the application


@Automated
Scenario: To verify user Client tab information
Given I am LoggedIn with correct username and password
When I click on profile dropdown
And I select "Client"
Then I should be able to see Client Information as follows
| Client Name | Description | Subscription Start Date | Subscription End Date | Maximum Load Tests | Maximum VoiceWatch Tests | Maximum Scenarios | Minimum Test Schedule Period (mins) | 
And I click on profile dropdown
And I logout from the application




