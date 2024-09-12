# integration with notion
There is a notion database that has name is "Goals" with fields:
- ID
- Name
- Type: a enum with values: "Programming", "English"
- Progress: a number from 0 to 100 in percent
We need to create a table "Goals" in our app with the same fields. 
We create a screen in our app "Goals" and show all items from the database. On this page, we have a sync button. When user click on this button, we need to update the data in our database with the data from the notion database.
