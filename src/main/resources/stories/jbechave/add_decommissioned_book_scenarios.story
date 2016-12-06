
Scenario: scenario description
Given a book in library
When book is "decommissioned"
Then book is deleted from usual book-list, and added in decommissioned book-list