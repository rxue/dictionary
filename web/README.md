# How to Run
## Steps to run
1. `git clone git@github.com:rxue/dictionary.git` to a directory
2. open termimal in the directory, where the project is downloaded to, then `cd dictionary/docker`
3. execute `source run_web.sh` for a simple run
## User Guide
1. open `localhost/dictionary/search.jsf` in a browser
2. search for a word, e.g. type "crux" then click the search button

## How to Test
### Steps to Run Integration Test
1. `git clone git@github.com:rxue/dictionary.git` to a directory
2. open termimal in the directory, where the project is downloaded to, then `cd dictionary/docker`
3. execute `source run_integration_test_web.sh`

# Project Management
## Epic: Release a dictionary web application
### [User Stories](https://www.atlassian.com/agile/project-management/user-stories)
#### [As a user, I want to search for a word so that I get the explanation](https://github.com/rxue/dictionary/issues/60)
 * A user should be able to search for a word with criteria such as keyword, language
 * Search keyword should be CASE-INSENSITIVE
 * Some word, esp. noun, should also be explained with pictures
##### Subtask: As a user, when searching for a keyword, I want to get a a list of candidates to select from
##### Subtask: [As a user, after giving a keyword in the input field, I want to get the result](https://github.com/rxue/dictionary/issues/61) 
##### Subtask: [As a user, I want to search a word with a pre-defined source language and explanation language](https://github.com/rxue/dictionary/issues/62)
##### the searched result should be bookmarkable

### Issues
 * [Make the 1st servlet for getting the image on base of a searched word #1](https://github.com/rxue/dictionary/issues/1)
 * [Make a simple GUI with JSF for input a word and display image on base of the input](https://github.com/rxue/dictionary/issues/4)
 * [Make a Word Search Repository used as a dependency by the Dictionary managed bean](https://github.com/rxue/dictionary/issues/9)

