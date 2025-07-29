# command to run tests
PYTHONPATH=src python3 -m unittest tests/importsql/test_importsql.py

# Design
## csv
### example
**header**
`*lexical_item.language,*lexical_item.value,*explanation.language,*explanation.serial_number,explanation.partofspeech,explanation.definition`
* relation names in the header are *lexical_item* and *explanation*
* `*`  means the unique fields. E.g. in *lexical_item* the *unique key* is `language` and `value`

**row**
`en_US,test,,en_US,N,"definition of test"`

### Logic 
#### Dependencies on *Relations* - **left <- right**
example: *Relation* `explanation` is dependent on its **left** neighor *Relation* `lexical_item`
* Default *primary key* of a *relation* is `id`
* Default *foreign key* in a *relation* is *referenced relation* + `_id`. e.g. in the example above, the default foreign key in `explanaiton` referencing `lexical_item` is `lexical_item_id`

## Parser
**Separation of Concern** : parse the csv to SQL `merge` statement without concerns on database connection, i.e. database should not be connected during the whole parsing process

# Daybook
## 20250726
### `pycharm` : How to configure pycharm so that it runs tests with a structure - tests are in `tests` directory and source code is in `src` directory
Settings > Project Structure
![Project Structure](https://private-user-images.githubusercontent.com/3033388/471044662-96120ce1-5266-48ba-b1be-3f145c8876b3.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NTM1MDYyMTUsIm5iZiI6MTc1MzUwNTkxNSwicGF0aCI6Ii8zMDMzMzg4LzQ3MTA0NDY2Mi05NjEyMGNlMS01MjY2LTQ4YmEtYjFiZS0zZjE0NWM4ODc2YjMucG5nP1gtQW16LUFsZ29yaXRobT1BV1M0LUhNQUMtU0hBMjU2JlgtQW16LUNyZWRlbnRpYWw9QUtJQVZDT0RZTFNBNTNQUUs0WkElMkYyMDI1MDcyNiUyRnVzLWVhc3QtMSUyRnMzJTJGYXdzNF9yZXF1ZXN0JlgtQW16LURhdGU9MjAyNTA3MjZUMDQ1ODM1WiZYLUFtei1FeHBpcmVzPTMwMCZYLUFtei1TaWduYXR1cmU9OTE3OWM4NDc0YTg3YmY4NGFjNmMxZjIzY2E0YmUyMjEwZGIxOTQxMzlhY2VhYzFhYzQ5MWVmNDE0MzhlODgxNSZYLUFtei1TaWduZWRIZWFkZXJzPWhvc3QifQ.jxKg7Z0cU6_aRVx3xiccnPPSrlC83Gb8lKKOAs_b-WI)
