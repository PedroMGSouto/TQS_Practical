Feature: Book Search

  Background: Demo Library
    Given a book with the title 'Eragon', written by 'Christopher Paolini', published in 2003-08-26
    And a book with the title 'The Magicians', written by 'Lev Grossman', published in 2009-02-16
    And a book with the title 'Magician', written by 'George RR Martin', published in 1982-10-01
    And a book with the title 'The Painted man', written by 'Peter V. Brett', published in 2008-09-01

  Scenario: Search By Date between x and y
    When the customer searches for books published between 1982-09-01 and 2021-04-20
    Then 4 books should have been found
    And Book 1 should have the title 'The Magicians'
    And Book 2 should have the title 'The Painted man'
    And Book 3 should have the title 'Eragon'
    And Book 4 should have the title 'Magician'


  Scenario: Search By Author
    When the customer searches for books written by 'Lev Grossman'
    Then 1 books should have been found
    And Book 1 should have the title 'The Magicians'

  Scenario: Search By Similar Title
    When the customer searches for books with a title similar to 'Magician'
    Then 2 books should have been found
    And Book 1 should have the title 'The Magicians'
    And Book 2 should have the title 'Magician'