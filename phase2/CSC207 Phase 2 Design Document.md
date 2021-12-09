# CSC207 Phase 2 Design Document

## Cathy Pei, Lily Li, Yide Ma, Andy Chang, Luke Kuo, Kyle Chen

## Updated Specification of the Program
HR System is an application designed for information technology industries that help different sectors organize and manage resources and employee information. Users of this application can login to the system, and according to the user's authority level, the user can perform different actions. Higher level workers (managers) and admins can create or delete lower level user accounts. After users are deleted, they will be removed from all work group, and the company accounts. It also provides related support for employees to use while accomplishing their work, such as creating and saving a work’s information and the group’s information that works on this project. Also, the higher level workers can create a group with the leaders as a “project team” and add members inside to assign Work to them. And we also implemented the functions that calculate the KPI for the employees based on their work done. Employers can use these functions to check employees’ KPI and bonuses,  and the Full Employees’ salary is calculated with the bonus. Part-time workers’ salary is calculated with their work hours


## Functionality
As the specification states, our program allows users to login and manage their personal information and resources relevant to their work and group.
During phase 2, we added a few more features to better enrich our program’s functionality. We added the Salary calculators that calculates the bonus employees earned from their work completion and vacation with salary. As well as the KPI calculator that calculates the works’ KPI of the employee based on the work they finished. Also, leaders can extend work’s due date and change work’s state now.


## Design Decisions

- Integrate the AuthorityManager into the Facade: 
  When we designed AuthorityManager, we found that it would be a very troublesome problem and difficult to use if it was made into an independent class. So, conceivably, we decided to incorporate permission-related content into the Controller and framework. That decision led to the creation of FacadeSys. The creation of the FacadeSys would also help with our adherence to the Single Responsibility principle in SOLID, which Lily will explain more later.

- FacadeSys, Controllers, and Managers: 
For phase 2, based on the feedback of Phase 1, we deleted the workFacade and the AccountFacade because they were not a true Facade and performed all the business functionality themselves instead of delegating it to other classes. Thus, we only keep the FacadeSys as a controller that distributes different tasks to the uses cases through calling other controllers and follows the Clean Architecture Design, which Andy will explain in detail afterwards.



## UML Diagram
The diagram can be accessed [here](https://lucid.app/lucidchart/9cec037d-f77d-48f4-9f61-015444782cca/edit?viewport_loc=-347%2C761%2C2991%2C1392%2CHWEp-vi-RSFO&invitationId=inv_ffb26101-c08f-4cd2-a8c6-5570b213e850)

## Brief Description of How SOLID Principle Implemented

- Single Responsibility Principle
    - Every class should have a single responsibility.
    - The implementation of the Facade design pattern in the controller that delegates tasks to the Uses Cases ensures our classes in the UsesCases have a single responsibility. 
    - Classes in the UsesCases have a single responsibility.
        - WorkManager only manages work-related functions, like showWorkDetail and extendWork methods.
        - UserManager only get a list of employees that have a lower level than the target employee.
        - EmployeeList only stores the information of employees with add and delete methods.
        - GroupList only stores the information of groups with add and delete methods.
        - GroupManager only modifies group information, addMembers and resetMembers and changeLeaders.
        - LoginList stores the User information and adds and deletes users.
        - Verifier only verifies the information and returns a boolean based on the conditions given from the information.
        - WorkList only stores the Workable information inside.
        - SalaryCalculator only manages the information about KPI and calculates the salary and bonuses for employees.
        - PersonalManager only manages the information about the Employees with check, set and get methods.
        - KPICalculator only calculates the KPI for employees. 


- Open/Close Principle
    - Software entities should be open for extension, but closed for modification. 

    - Our Employee class can have different types of employees by extending the Employee class, but not limited to part-time and full-time Employee, which is open to extension but closed to modification.


- Liskov Substitution Principle
    - If S is a subtype of T, then objects of type S may be substituted for objects of type T, without altering any of the desired properties of the program.

    - We tried not to override the methods from the parent class.
        - Any class that extends the User should be able to have a profile inside the system with a unique ID.
        - For example, our FullTimeEmployee class and PartTimeEmployee class did not override their parent class, the Employee class.


- Interface Segregation Principle
    - We need to keep the interface small and specific to its functionality.
    - In Entities, the interfaces Userable and Workable are small and specific to the implementation of the functions used by the User and Work classes.
    - In UsesCases, the iEmployeeList, iGroupList, iLoginList, iWorkList, are small and specific to the implementation of the functions, which guarantees the datalist has read input and write output functionality, used by the other Use Cases classes. Then, we can let data directly read input or written output from loginList, employeelist, grouplist, and worklist. Also, we wrote the interface initializable independentally and let IloginList, Iemployeelist, Igroup list, and Iworklist extend this interface, so that we are able to initial the data.


- Dependency Inversion Principle
    - To minimize coupling, we introduce an abstract layer between low-level classes and high-level classes by introducing the interfaces.
    - We designed the Userable interface so that our code does not violate the Dependency Inversion Principle. If we do not have this Userable interface, when a SecondUser class is implemented, userManager and personalManager are depending on only User class, which means we have to add code that lets the  userManager and personalManager also depend on SecondUser. But if  the two managers are dependent on Userable, as long as the User class and SecondUser implemented this interface, the code is extendable and only needs a little edition. 
    - Same with workable.

## Clean Architecture
Our program strictly followed the clean architecture design. We purposely built the structure of our code, so it does not violate the dependency rule. Our code had four layers, as the clean architecture states: entities, use cases, interface adapters, Frameworks.
 
- Entities
	- All of the objects that represent crucial business models are written as entity classes, which are the innermost cycle in the clean architecture. They can be manipulated and utilized by the use case classes.
	    - Examples: Employee Class, Group Class, Work Class, User Class.
 
- Use cases
	- We had created several types of use case classes:
    - Basic use case classes: they directly use the entity classes to process information and perform operations.
        - Examples: WorkManager Class, GroupManager Class, Verifier Class
    - Storage use case classes: they are used to store the entity objects in a collection, and they have the methods which is to add or delete objects from the collection.
        - Examples: WorkList Class, GroupList Class, EmployeeList Class, and LoginList
 
    - Input Boundary: We have added the input boundary in phase 2. It can let the controller depends on an abstract interface, but not depends on Use case directly.  Input boundary can help our code less coupling since we have no direct dependence between the controller and its corresponding use case. Moreover, the input boundary can prevent the outer layer know too much information on an inner layer. For instance, the controller cannot see its corresponding use case helper function, it only can see the use case method they needed. Here, we have IGroupList, IGroupManager, ILoginList, IPersonalManager, IVerifier, IWorkList, and IWorkManager as input boundaries.
 
- Interface Adapters
     - 	Gateways
We have created a class called DataGateway, which is a gateway that reads and writes data for our program. FacadeSys controller will call the gateway to read data into “LoginList,” “EmployeeList,” “WorkList,” and “GroupList '' when our HR system starts.  In addition, the FacadeSys will call a gateway to save the data from  “LoginList '', “EmployeeList '', “WorkList '', and “GroupList '' to Data Package (i.e. Folder) when the user wants to close this HR system
    - Facade
We created a class called FacadeSys, a unified place to manipulate controllers. FacadeSys will delegate business functionality to the corresponding controller and let the controller operate Use Case. FacadeSys can help our structure be more organized.
    - Controller
Each controller connects its corresponding use, case classes. It can prevent us from letting UI call the uses case directly, which helps us avoid violating clean architecture's dependency rule.
For example, we have a LoginlistController. This controller can only “add,” “delete,” “find”  users by calling loginList Use case. EmployeeListController and WorkListController works similar with LoginListController. We also have other controllers, and they also call their corresponding Use cases to achieve the business functionality.
 
- Frameworks
Initially, we only had three UI, which are personal, WorkManagerUI, and HomePageUI. However, we are splitting these three UI into more specific U in phase 1I. For instance, WorkManagerUI can be divided into “CreateUserUI,” “CreateWorkUI,” “DeletedUserUI,”...etc.
In phase 2, we have added some new UI and modified some UI. For instance, extendWorkUI is a new UI in phase 2, it can let users extend the due day of their work. In addition, we have modified PersonalManagerUI, it helps us can check their bonus salary.

## Design Patterns
- Iterator: 
We use iterator design patterns on LoginList, EmployeeList, GroupList, and WorkList. We separate the methods in class FileReadWrite into those lists. Thus, we are now reading files in these classes. The read methods iterate through the lists and retrieve the data.

- Facade: 
We use the Facade design pattern on the FacadeSys class. We decided to split AccountFacade and WorkFacade into several controller classes because they were not actually a facade design pattern. Now the FacadeSys class is integrates all the controller classes and make use of the methods in those controller classes.

- Dependency injection: 
Now we create the controller class in the FacadeSys and pass them into corresponding controllers instead of initializing them a new controller inside the controller class.


## Use of GitHub Features
- Issues
We had posted a few open questions on the github Issues section. Our team members expressed our own opinion and we had several discussions under each of the questions. For Phase 2, we have made more issues and most of them are commented by our group members with their thoughts. Also, we have more issues related to each pull request showing their understanding and comments of the changed codes.

- Pull Request
Each of our team members has created a personal branch from the origin. After commits and push the code to our personal branch, we always create a pull request and request for others’ view to check if the code works as expected and if the code follows Solid Principle or Clean Architecture. This way we can ensure that we do not violate the principles and the program is designed as expected. For Phase 2, we have a lot more code reviews on the pull requests and we have made sure that the person submitting the pull request is not the person merging it into Master so that there will be more people to review on the code.

- Code Style and Documentation
Our code style mostly follows the camel case rule. We also used ​​Javadoc to write docstring for each of the classes and methods so they will have a descriptive docstring which concludes the specification, parameters, and returns. Moreover, we tried our best to write commit messages and descriptions if needed whenever we pull requests, so that whenever someone opens our repo and navigates to a random file, they would be able to understand what we changed in our code. For Phase 2, we continue to write more thorough docstrings for the missing methods and new methods to  make sure every method will have a more descriptive and complete docstring. We have also tried our best to avoid using underscore in our local variables and fix all the warnings. 

## Testing
We wrote several test cases for a great deal of the methods for the entity classes and use case classes to test its functionality to see if their behaviour meets our expectations. **For Phase 2, we have tested a lot more methods, including all of the use case methods that are indirectly called  by the most important class FacadeSys. Through this process, we have resolved a few bugs in our code.**

## Refactoring
- Simplifying methods and method calls
    - A specific example that we use this refactoring strategy is in the method CreateNewAccount, it initially had a long parameter list, because to create a account we need many kinds of the information from the user (eg. name, phone, address, ID, department, etc.). It identified as a code smell, so in the latest version we organized its parameter list and replaced it by a collection.
    - **In Phase 2, we avoid writing long method calls, we make the method parameters short and reasonable so they follow a good style.**
    
- Extract Class
    - Phase 1: We first put all the UIs inside one single UI class, which is awkward because this UI class does too much work, so we decided to extract this class into multiple UI classes so that each of the UI classes is responsible for just one aspect of the functionality.
    - **Phase 2: We extract the WorkFacade class and AccountFacade class into smaller use cases class so that it prevents our program from violating the single responsibility principle. 
To be specific: We extracted the methods in WorkFacade class into WorkManager class and GroupManager class. AccountFacade class was extracted into the PersonalManager class and UserManager class.**

- Move Methods and Features
    - We first built some of the manager methods into the entity classes, but we soon realized that they seemed more eligible to be included into the use case classes, so we moved such methods into the Manager classes (use case classes).
Ex. addMembers used to be in the Group class, but it makes more sense to be in the GroupManager Class, because it is more like a manager method, so it is moved into the GroupManager Class.
    - **Phase 2: We further organized the methods to make sure they belong to the appropriate class in each of the clean architecture layers. For example, we managed to move all the methods that include system output into the UI classes.**

- Rename
    - In order to follow the camelcase rule, and to name our variables and methods with meaningful names, we used the refactoring feature Rename in IntelliJ to correct all the names into valid and suitable names. 
Ex. We had named the variable work id with wid, which is bad because it is not descriptive, so we corrected this name into work_id.
    - **In Phase 2, we corrected the leftover naming convention problems from phase 1. We also minimized unwanted warnings and use of names that violate camelcase rule, which made our code clean and in a good coding style.**

- Minimize Hard Coding:
    - In Phase 1, we have several places that use hard coded strings or integers, which are considered as code smells and bad designs. 
    - **In Phase 2, we had corrected these hard coding, instead of using fixed strings or integers, we used variables to store them. That way when we try to extend or modify the feature, it is easy to do.**

## Code Organization
- Packaging
  - We packed our code by separating them into layers to refer to clean architecture.
      - We had four packages:
          - The Entity package contains all the entity classes that are in the enterprise business rules level.
          - The UseCases package contains all the use case classes that are in the application business rules level.
          - The InterfaceAdapter package contains the controller classes, facade class and data gateway class that are in the interface adapter level.
          - The Framework package contains all the user interface classes and the main class that are in the frameworks and drivers level.
      - We also packaged our test cases into separate folders with different layers of tests.
      - We have organized the methods that have similar functionality or are collaborative in the facade so that they are in the same chunk of code, which is easy for us to find if bugs occurred in the relevant methods.

## Project Accessibility Report
1. For each Principle of Universal Design, write 2-5 sentences or point form notes explaining which features your program adheres to that principle. If you do not have any such features you can either:
(a) Describe features that you could implement in the future that would adhere to principle or
(b) Explain why the principle does not apply to a program like yours.

- Principle 1: Equitable Use
For now, in terms of privacy and usage, users have completed the same functions for users with the same level of authority.
We can implement functions like voice recognition, speech-to-text in the future to assist the disabled. Also, we can integrate multi-language interfaces to assist users with different language backgrounds. In terms of privacy and usage, users have completed the same functions as users with the same level of authority. 

- Principle 2: Flexibility in Use
Besides the future implementation of Principle 1, we will provide multiple choices, including but not limited to choosing the speed of voice playback, multi-languages framework. If we are capable of the technology, we hope to include the bioelectrical electronic recognition feature so that everyone can use our design flexibly. 

- Principle 3: Simple and Intuitive Use
At present, we are designing the program with features commonly used by a company, which allows us to be consistent with the user expectations of an HR system. 
In the future, we will implement the voice function and the multi-language design to fully meet this principle and accommodate a wide range of literacy and language skills. Meanwhile, we will further provide more guidance and instructions about using our system in the interface to minimize the complexity of using the system for the users.

- Principle 4: Perceptible Information
Languages and pictures can make the information much more perceptible. In the future, after we implement the voice function, we will add more recognition labels, like pictures. Also, we will present the information in different areas with different sizes and colours.

- Principle 5: Tolerance for Error
In the future, we will back up all the work files in the system and make a backup to ensure the file will not be lost. In addition, we will save the file immediately when the immediate exit of the user happens to prevent the file from being lost or damaged. The system will also ask and verify the user when the file is to be deleted if the user does this action accidentally. Our system will save the deleted files for a long time so that the user can recover the deleted files. These functions will maximize the tolerance for error.

- Principle 6: Low Physical Effort
This principle is not applicable for our design so far because the main functionalities of the HR system are to manage the input information and export the processed data. So it minimizes repetitive actions as well as sustained physical effort. However, we believe that the future implementation of voice recognition and output will allow the user to maintain a neutral body position.

- Principle 7: Size and Space for Approach and Use
Like principle 6, this principle is also not applicable to our design so far. However, if we implement more hardware and accessory equipment in the future, this will help with this principle.

2. Write a paragraph about who you would market your program towards, if you were to sell or license your program to customers. This could be a specific category such as "students" or more vague, such as "people who like games". Try to give a bit more detail along with the category.

- We would like to market our program towards the IT or tech companies that are project-based work. With our program, the people in charge can efficiently distribute one work towards employees with a lower level of authority by creating a project group and managing the work details through our program. Also, our program calculates the KPI for individual employees based on each worker's performance so that the employers can easily see the work accomplishment of different employees. Meanwhile, we also provide information storage for the system's users, like phone numbers and addresses, so that the company can easily access this information afterwards. Last but not least, we have different kinds of employees set by default in our programs, such as full-time employees and part-time employees. We know that part-time employee only works on an hourly basis and are paid hourly, so their task accomplishments are recorded separately. 

3. Write a paragraph about whether or not your program is less likely to be used by certain demographics. For example, a program that converts txt files to files that can be printed by a braille printer are less likely to be used by people who do not read braille.

- The main functionalities of the HR system are to manage the input information and export the processed data. Then theoretically, if the system is supported by technology, like bioelectrical recognition, the system can be used by most people except the unconscious. However, suppose we only implement features, like voice recognition, voice-to-text features and voice-to-braille, in the future. In that case, the system may not be able to support illiterate people, deaf-blind people, dumb people who cannot type, and so on.


## Instruction for running our program:
Since we did not create any file for all the data, please log in as Admin to do the further test.
- Username: Admin
- Passwork: Admin

The program will initialize the data as Admin if there are no file (data) detected. Since we expect the manager to create data for employees!


## Progress Report

- Possible expansions:
    - During phase 2, we did not implement any of the GUI, we rather kept the presenter as the command line interface. But our program is possible to be transformed into applications with GUI such as android and desktop app.
Since our program is built on a clean design basis, which means our program is extendable, we can easily implement more features relevant to Human Resource System

- What has Worked Well
    - We believe we have worked well in fixing the code. It was our first experience with software design and we initially had a lot of ideas and plans, but it made it hard to implement all of them inside the code. So, we chose the most important and applicable parts to implement. During this experience, we modified our code occasionally to ensure that it follows the software design principles and still developed with functionalities.

- Summary of Each Group Member’s Work and Plan
    - Chieh-An(Andy) Chang
      - Modified the code in FacadeSys.
      - Made some comments in FacadeSys.
      - Re-structured the Data Gateway. 
      - Gave the idea of input boundary.
      - Pull Request Link: https://github.com/CSC207-UofT/course-project-group_003/pull/251/files
      - In my opinion, this is the most important pull request I made in phase 2.
  I have refactored some naming conventions, and also give some comments on FacadeSys so that programmers can easier read the method and its corresponding method.
  I also make our loginList, EmployeeList, WorkList and Grouplist become “Polymorphism”
  For example, loginList can be ILoginlist type, IReadFile type, and Intializeable type.
  In the meantime, I made a decision to split the initializable method from IReadFile  interface to new Interface - initializable interface. So, we will not break the Interface Segregation Principle.

    - Kyle Chen
      - Wrote the test cases for all Entity and UsesCases needed
      - Implemented methods in original WorkFacade, and a few methods in original AccountFacade, and finished corresponding method in FacadeSys 
      - Designed and constructed some of the method structures (WorkManager, GroupManager) with Cathy
      - Created CreateUserUI and DeleteUserUI
      - Created GroupList and WorkList
      - Created and designed KPICalculator
      - Pull Request Link: https://github.com/CSC207-UofT/course-project-group_003/pull/280/
      - I believe this is the most important pull request I made in Phase 2 since I have dedicated almost all my time in Phase 2 in writing as many thorough test cases as possible, at the same time trying to debug the code once I found the test cases did not pass. I ended up fixing a number of bugs and made sure all the tests passed.

    - Luke Kuo
      - Split AccountFacade class into 5 use case classes 
      - Created boundaries for loginlist, employeelist, personal information related classes, and verifier related classes.
      - Modified and improved the boundaries for WorkManagerController and FacadeSys
      - Created controller for personal information related classes and verifier related classes
      - Created SetEmployeeInfoUI and InstructionUI.
      - Improved the overall structure for UI.
      - Debugged UI with Yide
      - Created test cases for PersonalInfoController, VerifierController, GroupManagerController, and LoginListController.
      - Pull Request Link: https://github.com/CSC207-UofT/course-project-group_003/pull/239 & https://github.com/CSC207-UofT/course-project-group_003/pull/232 
      - I think these two are the most important pull requests I made throughout phase 2. Adding boundaries is one of the important tasks we needed to do in phase 2. It made our program follow the clean architecture and SOLID principe.

    - Cathy Pei
      - Implemented several new features (Salary Calculator and related controllers and UIs)
      - Wrote and replenished test cases for all the use case classes and entity classes that needed to be tested (with Kyle)
      - Helped in debugging the Manager classes and entity classes
      - Reviewed the pull requests and help with merging
      - Commented and initiated issues on github
      - Edited the design documents (Refactoring and Code organization part)
      - Pull request: https://github.com/CSC207-UofT/course-project-group_003/pull/237
      - I believe this is a significant pull request because I implemented the SalaryCalculator features in the required layers of clean architecture. I changed lots of files in this push, so I asked all of my teammates to review this pull request. They gave me a lot of feedback, which helps me in further modification of this feature.

    - Yide Ma
      - Designed FacadeSys frame structure
      - Separated WorkFacade to GroupManager and WorkManager
      - Optimized and rewrote WorkFacade and work part in FacadeSys
      - Optimized all Manager in UseCases
      - Updated the DistributeWorkUI
      - Created the ChangeWorkStateUI and ExtendWorkUI
      - Created the WorkManagerController
      - Debugged unit tests
      - Debugged UI with Luke
      - Optimized whole FacadeSys
      - Pull Request Link:https://github.com/CSC207-UofT/course-project-group_003/pull/100/files
      It is a pull request that I generated when I was designing FacadeSys, and there are a few small pieces that I didn't put there. This design determined the structure of our subsequent FacadeSys.
      - https://github.com/CSC207-UofT/course-project-group_003/pull/249/files
                       It is a part of pull request that we start to separate the FacadeSys to other Controller; and delete the Account and Work Facade, then create other manager to do their works.

    - Xuelan (Lily) Li 
      - Wrote the docstrings in for the whole projects, including Entities, Use Cases, Interface Adapter and Framework.
      - Refactored some parameter names in Uses Cases.
      - Initiated and modified the design document and the presentation slides.
      - Responsible for the Solid Principle Writeup and Presentation.
      - Assisted with the Project Accessibility Report.
      - Finished the UML diagram for the whole projects.
      - Helped with the code review and merge on Git.
      - Opened one Git issue about accessibility issue and commented other issues on Git.
      - Pull Request Link: https://github.com/CSC207-UofT/course-project-group_003/pull/258
      - The reason that I believe this is important for Phase 2 is because this is one of the pull requests that I made most changes to the files by adding the docstrings with significant lines of codes and commented and approved by my teammate, Cathy. Also, this gives me a chance to review the codes written by my teammates by a second chance and understand the projects from another aspects.