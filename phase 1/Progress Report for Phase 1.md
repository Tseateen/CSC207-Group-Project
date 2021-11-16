# CSC207 Phase 1 Design Document
## Cathy Pei, Lily Li, Yide Ma, Andy Chang, Luke Kuo, Kyle Chen

## Updated Specification of the Program
HR System is an application designed for information technology industries that help different sectors organize and manage resources and employee information. Users of this application can login to the system, and according to the user's authority level, the user can perform different actions. Higher level workers (managers) and admins can create or delete lower level employee accounts. High-level workers can access and alter confidential information such as a lower level employee’s work information. It also provides related support for employees to use while accomplishing their work, such as creating and saving a work’s information and the group’s information that works on this project. Also, the higher level workers can create a group with the leaders as a “project team” and add members inside to assign Work to them.


## Design Decisions

- Integrate the AuthorityManager into the Facade: 
  It was one of the biggest decisions made in Phase 1. When we designed AuthorityManager, we found that it would be a very troublesome problem and difficult to use if it were made into a class. So, conceivably, we decided to incorporate permission-related content into the Controller and framework. That decision led to the creation of FacadeSys.

- FacadeSys, AccountFacade, and WorkFacade: 
When we started designing the controller, we realized that we just needed to throw the UI functionality into a class and let that class control the use case. So we have facadeSys, which can call all use cases by calling the accountFacade and workFacade.
	

## UML Diagram
The diagram can be accessed [here](https://lucid.app/lucidchart/9cec037d-f77d-48f4-9f61-015444782cca/edit?viewport_loc=-347%2C761%2C2991%2C1392%2CHWEp-vi-RSFO&invitationId=inv_ffb26101-c08f-4cd2-a8c6-5570b213e850)


## Brief Description of How SOLID Principle Implemented

- Single Responsibility Principle
    - Classes in the UsesCases have a single responsibility.
        - `WorkManager` only assigns work to the Employees.
        - `EmployeeList` only stores the information of employees with add and delete methods.
        - `FileReadWrite` only reads and writes files.
        - `GroupList` only stores the information of groups with add and delete methods.
        - `GroupManager` only modifies group information, addMembers and resetMembers and changeLeaders.
        - `LoginList` stores the User information and adds and deletes users.
        - `Verifier` only verifies the User information.
        - `WorkList` only stores the Work information inside.
        - `WorkManager` only manages the Work like changing the states of the Work.


- Open/Close Principle
    - Our `Employee` class can have different types of employee by extending the `Employee` class, but not limited to part time and full time Employee, which is open to extend but closed to modification.


- Liskov Substitution Principle
    - We tried not to override the methods from the parent class.
        - Any class that extends the User should be able to have a profile inside the system with a unique ID.
        - For example, our `FullTimeEmployee` class and `PartTimeEmployee` class did not override their parent class, the Employee class.


- Interface Segregation Principle
    - The interfaces `Userable` and `Workable` are small and specific to the implementation of the functions used by the `User` and `Work` classes.


- Dependency Inversion Principle
    - We designed the `Userable` interface so that our code does not violate the Dependency Inversion Principle. If we do not have this `Userable` interface, when a `SecondUser` class is implemented, `AccountManager` is depending on only `User` class, which means we have to add code that lets the `AccountManager` also depend on `SecondUser`. But if `AccountManager` is dependent on `Userable`, as long as the `User` class and `SecondUser` implemented this interface, the code is extendable and only needs little edition. 
    - Same with workable.

## Clean Architecture
Our program strictly followed the clean architecture design. We purposely built the structure of our code so it does not violate the dependency rule. Our code had four layers as the clean architecture states: entities; use cases; interface adapters; Frameworks.

- Entities
    - All of the objects that represent crucial business models are written as entity classes, which are the innermost cycle in the clean architecture. They can be manipulated and utilized by the use case classes.
    - Examples: Employee Class, Group Class, Work Class, User Class.

- Use cases
    - We had created several types of the use case classes:
        - Basic use case classes: they directly use the entity classes to process information and perform operations.
            - Examples: WorkManager Class, GroupManager Class, Verifier Class
        - Storage use case classes: they are used to store the entity objects in a collection, and they have the methods which is to add or delete objects from the collection.
            - Examples: WorkList Class, GroupList Class, EmployeeList Class
        - Facade use case classes: they are used to provide a unified template for other correlated use case classes and also support the polymorphic behaviors. By implementing this type of use case classes the complexity of the system can be reduced at run-time.
            - Examples: WorkFacade Class, AccountFacade Class
        - Interface Adapters
            - Gateways
We have created a class called DataGateway, which is a gateway that reads and writes data for our program. FacadeSys controller will call the gateway to read “LoginList”, “EmployeeList”, “WorkList”, and “GroupList '' when our HR system starts.  In addition, the FacadeSys controller will call a gateway to save the data from  “LoginList '', “EmployeeList '', “WorkList '', and “GroupList '' to Data Package (i.e. Folder), when the user wants to close this HR system.
            - Controllers
We had created a class called FacadeSys, which is a controller that connects the use case classes to the outer layer of the clean architecture (The UIs). It is used to protect our program from violating the dependency rule of clean architecture.

- Frameworks
Originally, we only have three UI, which are PersonalUI, WorkManagerUI, and HomePageUI. However, we are planning to make a GUI later. Therefore, we have to split these three UI into more specific UI. For instance, WorkManagerUI can be split into “CreateUserUI”, “CreateWorkUI”, “DeletedUserUI”, ...etc.


## Design Patterns
- Iterator: we use iterator design patterns on LoginList, EmployeeList, GroupList, and WorkList. FileReadWrite uses the iterator of the above 4 lists on reading the data from the serialized file. The readFromFile methods in FileReadWrite iterate through the above lists to retrieve the data.
- Facade: we use the Facade design pattern on the class FacadeSys,AccuontFacade and WorkFacade. The class AccountFacade integrates all use case classes that are related to a user and employee’s information. WorkFacade integrates all use case classes that are related to the group and work’s information. FacadeSys class integrate both AccountFacade and WorkFacade together and make use of the methods in the two classes.
- Dependency injection: We create the LoginList, EmployeeList, GroupList and WorkList outside and pass them into the AccountFacade and WorkFacade class instead of initializing them inside the classes. Therefore we can avoid the hard dependency.


## Use of GitHub Features
- Issues
We had posted a few open questions on the github Issues section. Our team members expressed our own opinion and we had several discussions under each of the questions.

- Pull Request
Each of our team members has created a personal branch from the origin. After commits and push the code to our personal branch, we always create a pull request for others to check if the code has conflict with the main branch. This way we can safely merge our new code to the repository without unexpected errors due to constantly merging.

- Code Style and Documentation
Our code style mostly follows the camel case rule. We also used ​​Javadoc to write docstring for each of the classes and methods so they will have a descriptive docstring which concludes the specification, parameters, and returns. Moreover, we tried our best to write commit messages and descriptions if needed whenever we pull requests, so that whenever someone opens our repo and navigates to a random file, they would be able to understand what we changed in our code.

## Testing
We wrote several test cases for a great deal of the methods for the entity classes and use case classes to test its functionality to see if their behaviour meets our expectations.

## Refactoring
- Simplifying methods and method calls
A specific example that we use this refactoring strategy is in the method CreateNewAccount, it initially had a long parameter list, because to create a account we need many kinds of the information from the user (eg. name, phone, address, ID, department, etc.). It identified as a code smell, so in the latest version we organized its parameter list and replaced it by a collection.

- Extract Class
We first put all the UIs inside one single UI class, which is awkward because this UI class does too much work, so we decided to extract this class into multiple UI classes so that each of the UI classes is responsible for just one aspect of the functionality.

- Move Methods and Features
We first built some of the manager methods into the entity classes, but we soon realized that they seemed more eligible to be included into the use case classes, so we moved such methods into the Manager classes (use case classes).
Ex. addMembers used to be in the Group class, but it makes more sense to be in the GroupManager Class, because it is more like a manager method, so it is moved into the GroupManager Class.

- Rename
In order to follow the camel case rule, and to name our variables and methods with meaningful names, we used the refactoring feature Rename in IntelliJ to correct all the names into valid and suitable names. 
Ex. We had named the variable work id with wid, which is bad because it is not descriptive, so we corrected this name into work_id.

## Code Organization
- Packaging
We packed our code by separating them into layers to refer to clean architecture.
    - We had four packages:
        - The Entity package contains all the entity classes that are in the enterprise business rules level.
        - The UseCases package contains all the use case classes that are in the application business rules level.
        - The InterfaceAdapter package contains the controller class and data gateway class that are in the interface adapter level.
        - The Framework package contains all the user interface classes that are in the frameworks and drivers level.
    - We also packaged our test cases into separate folders with different layers of tests.
    - We have organized the methods that have similar functionality or are collaborative in the facade so that they are in the same chunk of code, which is easy for us to find if bugs occurred in the relevant methods.

## Functionality
As the specification states, our program allows users to login and manage their personal information and resources relevant to their work. High-level employees have permission to alter and review the information of employees whose level is lower, they can manage and assign work to those employees and perform other manager-related tasks.

During phase 0, we had a lot of ideas on how to build the HR system with various features. However, after we started to code them, we found that some features are hard to implement and vary cases by cases, so it made it hard to implement all the features we had before. So, in phase 1, we tried to focus on the common features of a HR system, like storing the user information and assigning work to employees, and focused on the features we had now and followed the open/close principle in solid principle. Thus, if we have any other new features we want to add in the future, we are able to modify it based on what we had before.

We had implemented the database so that the changes in information made by the system can be updated during the next use of the program (the updated info can be loaded from the database).

## Instruction for running our program:
Since we did not create any file for all the data, please log in as Admin to do the further test.
- Username: Admin
- Passwork: Admin

The program will initialize the data as Admin if there are no file (data) detected. Since we expect the manager to create data for employees!


## Progress Report

- Open questions
    - Presenter
        - Which type should we use? Which one is suitable for our program?
        - How to implement?

- What has Worked Well
We believe we have worked well in fixing the code. It was our first experience with software design and we initially had a lot of ideas and plans, but it made it hard to implement all of them inside the code. So, we chose the most important and applicable parts to implement. During this experience, we modified our code occasionally to ensure that it follows the software design principles and still developed with functionalities.

- Summary of Each Group Member’s Work and Plan
    - Chieh-An(Andy) Chang
        - Implemented DataGateway with Luke.
        - Implemented LoginList and EmployeeList.
        - Implemented FileReadWrite with Luke.
        - Split WorkManagerUI into CheckSalaryUI, CreateUserUI, PrepareForWorkUI, WorkInfoUI and SetPersonalInfoUI.
        - Organize FacadeSys
        - Organize AccountFacde
        - Future Plan:
            - Implemented GUI for each TextUI

    - Kyle Chen
        - Write the test cases for Entity
        - Implemented methods in WorkFacade, and a few methods in AccountFacade, and finished corresponding method in FacadeSys 
        - Designed and construct some of the method structures (WorkManager, GroupManager) with Cathy
        - Created CreateUserUI and DeleteUserUI
        - Created GroupList and WorkList
        - Future plan: 
            - Optimize code related to Group and Work; work on GUI
	
    - Luke Kuo
        - Implemented DataGateway with Andy
        - Implemented FileReadWrite with Andy
        - Designed and implemented the PersonalInfoUI
        - Implemented PersonalInfoUI related method in FacadeSys and AccountSys
        - Debug UI with Yide
        - Debug classes in InterfaceAdapter and Usescases.
        - Future plan: 
            - Implemented GUI for PersonalInfo.

    - Cathy Pei
        - Implemented methods in AccountFacade, and finished corresponding method in FacadeSys (Check all lower level employees’ salary and other related informations)
        - Wrote docstrings for some of the classes (Most UI classes and some of the use case classes)
        - Designed and construct some of the method structures (WorkManager, GroupManager) with Kyle
        - Created CheckSalaryUI
        - Refactored code to reduce code smells
        - Modified and updated the UML Diagram
        - Contribute in Design Document
        - Future plan: 
            - Polishing and optimizing code; 
            - Work on GUI and Frameworks
            
    - Yide Ma
        - Designed FacadeSys frame structure and Left docstring
        - Designed AccountFacade and WorkFacade frame structure
        - Optimized and rewrote WorkFacade and work part in FacadeSys
        - Optimized all Manager in UseCases
        - Debug unit tests
        - Debug UI with Luke
        - Future Plan:
            - Optimize whole FacadeSys and AccoundFacade
            - Connect Journal with Work
            - Implement and improve more of the features mentioned in Phase0
            
    - Xuelan (Lily) Li 
        - Created the GroupManager UsesCase
        - Wrote the test cases in UsesCases in coding
        - Wrote the dostings in UsesCases in coding
        - Initiated the design document
        - Responsible for the Solid Principle Writeups
        - Drafted the UML diagram
        - Future plan: 
            - Research in different types of presenters
            - Implement presenters into the design

