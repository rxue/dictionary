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



# [User Stories](https://www.atlassian.com/agile/project-management/user-stories)
## A client searches for a word
 * A user should be able to search for a word with criteria such as keyword, language
 * Search keyword should be CASE-INSENSITIVE
 * Some word, esp. noun, should also be explained with pictures
### Issues
 * [Make the 1st servlet for getting the image on base of a searched word #1](https://github.com/rxue/dictionary/issues/1)
 * [Make a simple GUI with JSF for input a word and display image on base of the input](https://github.com/rxue/dictionary/issues/4)
 * [Make a Word Search Repository used as a dependency by the Dictionary managed bean](https://github.com/rxue/dictionary/issues/9)

