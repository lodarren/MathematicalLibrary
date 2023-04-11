# Mathematics Library

## CPSC210 WT2 Project Darren Lo 93930725

For most students, researching mathematical formulas and theorems is something one must do during their time at school. 
However, when simply keeping track of formulas and theorems, one would typically find it very hard to keep track of all 
the different ideas to remember, and is not efficient to studying. The goal of this project is to create a mathematical 
library that allows the user to quickly and easily find a mathematical resource they need, and easily
obtain more information (such as its specific proof, some example problems etc.) tailored to their needs in an 
intuitive way.

The library will be separated into three different types of sections, equations (for math equations), theorems (concepts
to remember) and finally requests (one of the previous two entries that have not been implemented in the main library
yet.). Users will be able to navigate through these menus and view, modify and add the entries that are in those 
libraries. 

This project is aimed towards anyone who is interested in studying mathematics, and will attempt to be made in a way
to be as user-friendly as possible. 

**User Stories**
- As a user, I want to be able to look up a theorem that matches the name I am looking for. 
- As a user, I want to be able to access additional information about the theories, and view the proof when I need to
 view it. 
- As a user, I want to be able to view equations that are in the library, and view the corresponding practice problems 
for them. 
- As a user, I want to view a practice problem while hiding the answer, and then show the answer to the practice problem 
when I am ready. 
- As a user, I want to be able to suggest requests for entries to be added and add the fields I would like to add at the
moment. 
- As a user, I want to be able to contribute to the database adding a request to one of the main lists of equation 
entries or to the main list of theorems depending on the request that is being submitted. 
- As a user, I want to be able to save my to-do list to file. 
- A a user, I want to be prompted to save my request entries, my equation entries and my theorem entries when I attempt
to quit the program. 
- As a user, I want to be able to load my previous theorems, equations and requests when opening up the program, as well
as given the option to start from an empty library. 

# Instructions for Grader

- You can generate the first required action related to adding a Request to the TheoremLibrary or EquationLibrary by
first entering the "Open Request Library!", selecting "View Requests", and then by clicking on "Submit Request" (if the 
entry is not completed, it will ask if you want to submit the entry, select "Submit" for the time being.). Upon completion, 
if this entry was an equation then it will be in the "Equation Library", and if the entry was a theorem then it will be 
in the "Theorem Library". To access either one of these libraries, simply click on "Return to Main Menu" on the right of
the screen and enter the designated library ("Open Theorem Library!" for theorems and "Open Equation Library!" for equations).
Your theorem/equation will be in the dropdown menu in the top right corner. 
- You can generate the second required action by entering either the "Equation Library" or the "Theorem Library" and you can 
delete any entry by clicking the "Edit Equation/Theorem" button to clicking "delete entry" button in the top right corner 
of the menu (this only works when there is more than one entry in the theorems/equation library). 
- You can locate the visual component of the program when loading entering the main menu of the program. You will be 
prompted with a picture of multiple math equations in the center of the screen. 
- You can save the state of my application by clicking the button "Goodbye!". You will be prompted if you would like to
save the application. Click "Save Entries", if you would like to save the application (other options will result in no 
saves).  
- When loading the application, you will be prompted if you would like to load the previous save file. If "Load save" is 
selected, then the previous save file will be loaded (other options will result in no entries added to any library).

# Phase 4: Task 2:

Sample:\
Tue Apr 11 02:29:36 PDT 2023\
Added This is a new theorem to the Request library.\
Tue Apr 11 02:29:46 PDT 2023\
Changed This is a new theorem's completion to 100\
Tue Apr 11 02:29:52 PDT 2023\
Moved This is a new theorem(request) to the main Theorem Library!\
Tue Apr 11 02:29:52 PDT 2023\
Added This is a new theorem to the Theorem library.\
Tue Apr 11 02:29:52 PDT 2023\
Removed This is a new theorem from the Request library.\
Tue Apr 11 02:30:05 PDT 2023\
Changed This is the real name of the theorem's name to This is the real name of the theorem\
Tue Apr 11 02:30:10 PDT 2023\
Removed This is the real name of the theorem from the Theorem library.\
Tue Apr 11 02:30:14 PDT 2023\
Saved Theorem Library.\
Tue Apr 11 02:30:14 PDT 2023\
Saved all practice problems for Fundamental theorem of engineering\
Tue Apr 11 02:30:14 PDT 2023\
Saved all practice problems for The cosine law\
Tue Apr 11 02:30:14 PDT 2023


# Phase 4: Task 3:
If I had more time to work on the project, I would like to factor out the ListOfPracticeProblems and the
ListOfPracticeProblemAnswers into a separate class. Currently, the Equation class has to take care of the functionality 
of the specific Equation entry, as well as viewing problems, saving problems, and managing problems and answers. I believe
it would be a lot more efficient to have this be split into two separate classes instead (One "Equation" class, 
and a second class called "Problems", in which Equation would have a field "Problems"). This way the program is less coupled,
and can allow for easier changes in regard to just editing the PracticeProblem/PracticeProblemAnswer functionality without
having to change the entire Equation class. 

