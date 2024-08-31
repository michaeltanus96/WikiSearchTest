# Wikipedia Search Automation Testing

This project is an automated testing suite for verifying the search functionality on Wikipedia using Cucumber and Selenium WebDriver. The tests cover various scenarios such as valid and invalid search terms, verifying search suggestions, and checking pagination on search results.

## Prerequisites

### 1. Install ChromeDriver

You need to install a ChromeDriver compatible with your current Chrome browser version. You can download the correct version of ChromeDriver from the [Chrome for Testing site](https://googlechromelabs.github.io/chrome-for-testing/#stable).

1. Visit the [Chrome for Testing site](https://googlechromelabs.github.io/chrome-for-testing/#stable).
2. Download the ChromeDriver that matches your Chrome browser version.
3. Copy the `chromedriver.exe` to the following path in your project: `src/main/resources/chromedriver.exe`


### 2. Install Maven (if not already installed)

This project uses Maven for dependency management. Make sure you have Maven installed and configured.

## Running the Tests

### 1. Clone the Repository

First, clone this repository to your local machine:

```bash
git clone https://github.com/your-username/wikipedia-search-automation.git
cd wikipedia-search-automation
```

### 2. Build the project
`mvn clean install`

### 3. Run the Tests
`mvn test`

Alternatively, if you're using an IDE like IntelliJ IDEA or Eclipse, you can run the results directly from the IDE

### 4. View the Results
After the tests run, you can view the results in the console or in the generated Cucumber reports.

## Test Scenarios

### 1. Valid Search Term

**Scenario:** Perform a search with a valid search term and verify that the search results page displays relevant results.

- **Given** I open the browser and navigate to the Wikipedia homepage
- **When** I enter a valid search term into the search box
- **And** I click the search button
- **Then** the page title should contain the search term

### 2. Invalid Search Term

**Scenario:** Perform a search with an invalid search term and verify that an appropriate message is displayed.

- **Given** I open the browser and navigate to the Wikipedia homepage
- **When** I enter an invalid search term into the search box
- **And** I click the search button
- **Then** I should see a message indicating that no results were found

### 3. Verify Search Suggestions

**Scenario:** Type a query in the search box and verify that the suggestions in the dropdown match the query.

- **Given** I open the browser and navigate to the Wikipedia homepage
- **When** I type a query into the search box
- **Then** the search suggestions should contain the query

### 4. Verify Pagination on Search Results

**Scenario:** Navigate to the second page of search results and verify that new results are displayed.

- **Given** I open the browser and navigate to the first page of search results
- **When** I click on the "next 20" link
- **Then** I should see a different set of search results
