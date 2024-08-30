package com.Veeva.automation.steps;

import com.Veeva.automation.pages.WikiHomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WikiHomePageSteps {
    WebDriver driver;
    WikiHomePage homePage;
    List<String> firstPageResults;

    @Given("I open the browser and navigate to {string}")
    public void i_open_browser_and_navigate_to(String url){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
        homePage = new WikiHomePage(driver);
    }

    @When("I enter {string} into the search box")
    public void i_enter_into_the_search_box(String searchTerm){
        homePage.enterSearchTerm(searchTerm);
    }



    @And("I click the search button")
    public void i_click_the_search_button() {
        homePage.clickSearchButton();
    }

    @Then("the page title should contain {string}")
    public void the_page_title_should_contain(String expectedTitle){
        WebDriver driver = new ChromeDriver();
        homePage.verifyPageTitle("Cucumber (software)");
        driver.quit();
    }

    @Then("I should see a message indicating no results were found")
    public void i_should_see_a_message_indicating_no_results_were_found() {
        WebDriver driver = new ChromeDriver();
        try {
            WebElement noResultsMessage = driver.findElement(By.cssSelector(".mw-search-nonefound"));
            Assert.assertTrue("No results message not found!", noResultsMessage.isDisplayed());
        } catch (NoSuchElementException e) {
            List<WebElement> searchResults = driver.findElements(By.cssSelector(".mw-search-result"));
            Assert.assertTrue("Expected no search results, but some were found.", searchResults.isEmpty());
        }
        driver.quit();
    }

    @When("I type {string} into the search box")
    public void i_type_into_the_search_box(String searchTerm) {
        homePage.enterSearchTerm(searchTerm);
    }

    @Then("I should see search suggestions containing {string}")
    public void i_should_see_search_suggestions_containing(String suggestion_list) {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> suggestions = homePage.getSearchSuggestions();
        for (WebElement suggestion : suggestions) {
            Assert.assertTrue("Suggestion does not contain the query",
                    suggestion.getText().contains(suggestion_list));
        }
        driver.quit();
    }

    @When("I click the next page link")
    public void i_click_the_next_page_link() {
        homePage.clickNextPage();
    }

    @And("I store the results of the first page")
    public void i_store_the_results_of_the_first_page() {
        firstPageResults = homePage.getSearchResultTitles();
    }

    @Then("I should see a different set of search results")
    public void i_should_see_a_different_set_of_search_results() {
        boolean resultsAreDifferent = homePage.areResultsDifferent(firstPageResults);
        Assert.assertTrue("The search results are not different after pagination", resultsAreDifferent);
    }
}