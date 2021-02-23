# OSKmanager — backend

## Notes for developers working on the project

* ### What should I do locally before sending new *pull request*?

  Simply run the command `mvn clean verify` – you may send your pull request only if the project is successfully built
  after this command.

* ### How many *approvals* do I need for my pull request to *merge* it into the master branch?

  Two approvals are needed to merge a *pull request* into the master branch. Remember that you may merge your code into
  the master branch provided it passes `mvn clean verify` command.
  
* ### Are there any special rules I should keep in mind while developing the project?

  When you are writing code related to some class, try to stick to the order of that class fields. E.g., 
  try to appropriately order fields or variables when you introduce them.
  Try to properly order your test methods preparing a unit test class. 

Remember that on our main Discord channel you may ask questions in case anything is unclear to you.
We are there to help you :)
---