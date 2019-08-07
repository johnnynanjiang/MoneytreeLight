### Moneytree Light App

#### Implementation

- Implement the data story, put the json files in assets

- Implement the user stories(1 and 2) 

#### Clarification

- Use the concept of `clean architecture` to organize the app into 3 layers.

- Use `MvRx` and `Epoxy` for the sake of aligning with MoneyTree's tech stack.

- Codebase structure is as follows:
  * `data` - data models and repositories
  * `domain` - data models, data mapping, and business logic
  * `presentation` - view models, view data mapping, activity, fragments, and controllers
  * `viewmodel` - viewmodels for MvRx
  * `util` - utility classes
  * `app` - Android application class

- Tests are in test and androidTest. Some tests need Android application context to load files in assets.

- Total balance on account screen does not have currency, as there are different currencies in the list.

- Use json data for both production and test for the sake of simplicity, test data should be separate in reality.

- Accessibility is skipped due to time constraint, I do have experience implementing and testing accessibility on TalkBack.
