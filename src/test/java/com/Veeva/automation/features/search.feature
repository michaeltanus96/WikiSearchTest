Feature: Search functionality

  Scenario: Perform a search on the home page
    Given I open the browser and navigate to "https://www.wikipedia.org/"
    When I enter "Cucumber (software)" into the search box
    And I click the search button
    Then the page title should contain "Cucumber (software)"

  Scenario: Invalid search term
    Given I open the browser and navigate to "https://www.wikipedia.org/"
    When I enter "sdfgdsfgdfgdfg" into the search box
    And I click the search button
    Then I should see a message indicating no results were found

  Scenario: Search suggestions
    Given I open the browser and navigate to "https://www.wikipedia.org/"
    When I type "Cat" into the search box
    Then I should see search suggestions containing "Cat"

  Scenario: Search results pagination
    Given I open the browser and navigate to "https://en.wikipedia.org/w/index.php?fulltext=1&search=cars&title=Special%3ASearch&ns0=1"
    And I store the results of the first page
    When I click the next page link
    Then I should see a different set of search results