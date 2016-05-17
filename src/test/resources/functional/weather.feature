@weather
Feature: Show weather by city
  In order to show weather by city user should go to home page of our site.

  Scenario: Show the input form
    Given Our application up and running
    When I go to home page
    Then I should be able to see the city input field

  Scenario: Show weather based on the input city
    Given The app home page
    When I fill the city name
    And press enter
    Then I should see the following details: Date, City Name, Weather description, Temperature in C, temperature in F, Sunrise, Sunset.