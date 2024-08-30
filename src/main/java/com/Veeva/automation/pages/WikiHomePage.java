package com.Veeva.automation.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class WikiHomePage {
    WebDriver driver;

    public WikiHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "search")
    private WebElement searchBox;

    // Locate the search button using the button text
    @FindBy(xpath = "//button[@type='submit' and contains(@class, 'pure-button-primary-progressive')]")
    private WebElement searchButton;

    // Locate the donation banner close button
    @FindBy(css = "button.banner__close")
    private WebElement bannerCloseButton;

    // Locate the page title element
    @FindBy(css = "span.mw-page-title-main")
    private WebElement pageTitle;

    // Locate search suggestions
    @FindBy(css = "#typeahead-suggestions .suggestion-link")
    private List<WebElement> searchSuggestions;

    //Locate the next page button
    @FindBy(css = "a.mw-nextlink")
    private WebElement nextPageLink;

    //Locate results
    @FindBy(css = "div.mw-search-result-heading a")
    private List<WebElement> searchResultLinks;

    // Method to enter search term into search field
    public void enterSearchTerm(String searchTerm) {
        closeDonationBannerIfPresent();
        searchBox.sendKeys(searchTerm);
    }

    // Method to submit search
    public void clickSearchButton() {
        //closing donation banner if present
        closeDonationBannerIfPresent();
        searchButton.click();
    }

    // Method to close the donation banner if it is present
    public void closeDonationBannerIfPresent() {
        try {
            if (bannerCloseButton.isDisplayed()) {
                bannerCloseButton.click();
            }
        } catch (NoSuchElementException e) {
            // The banner is not present, so we can proceed
        }
    }

    //Method to verify Page title matches search term
    public void verifyPageTitle(String expectedTitle) {
        String actualTitle = pageTitle.getText();
        Assert.assertEquals("The page title does not match the expected title.", expectedTitle, actualTitle);
    }

    //Grabbing search suggestions
    public List<WebElement> getSearchSuggestions() {
        return searchSuggestions;
    }

    //Method to click on next page of results
    public void clickNextPage() {
        nextPageLink.click();
    }

    //Storing Search result titles in a list
    public List<String> getSearchResultTitles() {
        return searchResultLinks.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    //Comparing previous page of results with next page of results
    public boolean areResultsDifferent(List<String> previousResults) {
        List<String> currentResults = getSearchResultTitles();
        for (String result : currentResults) {
            if (previousResults.contains(result)) {
                return false; // This means we found a matching result, hence it is false to say they are different
            }
        }
        return true; // No matches found, results are different
    }
}
