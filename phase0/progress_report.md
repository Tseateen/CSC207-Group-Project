# CSC207 HR System Progress Report - Group003
Yide Ma, Chieh-An (Andy) Chang, Xuelan (Lily) Li, Luke Kuo, Kai-Fu (Kyle) Chen, Cathy Pei
Oct 11, 2021


## Summary of Specification

HR System is an application designed for information technology industries that help different sectors to organize and manage the resources and their employee information. Users of this application can perform login/logout action, and according to the user's authority, the application will display different user interfaces. Admins can create or delete employee accounts due to position changes. High-level workers (who have a high authority level) and  low-level workers (who have a low authority level) can perform differently when they use the application. It also provides related support for employees to use while accomplishing their work.


## CRC Model

We designed our CRC model based on specification and the clean architecture. In each class, we stated the responsibility of what the class should perform and the collaborator classes. We decided the entity includes Userable (an interface for User), User, Employee (an abstract class for FullTimeEmployee and PartTimeEmployee), FullTimeEmployee, PartTimeEmployee, Work and Group. The Uses case includes AccountManager, Verifier, AccountChanger, AuthorityManager, PayManager, VacationManager, WorkManager, WorkDistributor. Interface adapter includes Admin_System as the controller. Framework includes Command_User_Interface as the Command line UI.


## Scenario Walk-Through

Based on the CRC card, we wrote our scenario walk-through about creating a FullTimeEmployee in the HR system. We demonstrated the scenario under a technology company and described the functionality of the system and the collaboration between each class, when the user of the HR system, which is the HR manager, uses the system, from logging in with verification to creating a new FullTimeEmployee and storing the information into the HR system.


## Skeleton Program

Implemented following classes:
- Userable: Defines abstract methods for classes that implement this interface.
- User: a class that includes non-work-related information of an employee such as name, ID, phone number, address , account username and password.
- Employee: A parent class of all Employee
- Full Time Employee: a class that includes the work related information of a full time employee such as the amount of the vacation, vacation usage, position and status. Also, department, wage, attendance and level of the employee are inherited from the Employee abstract class.
- Part Time Employee: a class that includes the work related information of a part time employee such as the schedule. Also, department, wage, attendance and level of the employee are inherited from the Employee abstract class.
- AccountManager: to store all the information of the employees with the Hashmap using userable as the key and employee as the item; create and delete employees
- Verifier: to verify the account related information, like if the account number exists, if the password is matched with the account number, if the person has enough authority, etc.
- Admin_System: A controller that receives the responses from the user interface and passes the values to the use cases and vice versa.
- Command_User_Interface: A class that provides the command line platform to users so the user can access the HR system.


## What We Have Done

**Yide Ma:**
- Worked with Cathy Pei on specification
- Implemented AccountManager

**Chieh-An (Andy) Chang:**
- Worked with Luke Kuo on CRC
- Implemented Admin_System

**Xuelan (Lily) Li:**
- Worked with Kai-Fu (Kyle) Chen on Scenario Walk-through based on the CRC card
- Improved the CRC model with detailed responsibilities
- Implemented User and Userable 

**Cathy Pei:**
- Worked with Yide Ma on specification
- Implemented the Verifier Class
- Improved the CRC model (added few responsibilities and collaborators on some of the classes)

**Kai-Fu (Kyle) Chen:**
- Designed the CRC cards of the following classes: User, Employee, FullTimeEmployee, PartTimeEmployee, Verifier
- Worked with Xuelan (Lily) Li on Scenario Walk-Through based on the CRC card
- Implemented Employee, FullTimeEmployee, and PartTimeEmployee Class

**Kuan-Lin (Luke) Kuo:**
- Worked with Andy on the CRC model.
- Implemented PartTimeEmployee
- Modify Command_User_Interface


## What to Do Next

**Yide Ma** will implement the **AuthorityManager** use case, which is about the word based on the authority, and implemented with Verifier. 
**Chieh-An Chang** will improve **Command_User_Interface** and **Admin_System**.
**Xuelan (Lily) Li** will implement **Work** and **Group** entities. **Work** contains the related information in one piece of job, like contents of the job, working object, etc. **Group** records the related information of a project group, like group leader, group member and the part that each person in charge of; collaborated with Userable.
**Luke Kuo** will implement **WorkDistributor**, which is the use case, about a new project distribution and the formation of a group. It will collaborate with **Work** and **Group**, and **WorkManager**.
**Kyle Chen** will implement **PayManager** use case, which is about wage/salaries payable based on the completion of one’s job, information of the employee, and other KPI. The salary/wage payable information will also be recorded.
**Cathy Pei** will implement **AccountChanger** and **WorkManager**, as use cases. **AccountChanger** is to change the basic information of a given account (ex. password, phone number, etc.) **WorkManager** is to organize all the projects, including the completion of one project, the person that is in charge of, etc.


## What Has Worked Well So Far

We designed our code to follow the **clean architecture** and some of the **SOLID principles**. For example, we designed the **Userable interface** so that our code does not violate the **Dependency Inversion Principle**. If we do not have this **Userable** interface, when a SecondUser class is implemented, **AccountManager** is depending on only User class, which means we have to add code that let the **AccountManager** also depending on SecondUser. But if **AccountManager** is dependant on **Userable**, as long as the User class and SecondUser implemented this interface, the code is extendable and only needs little edition.


## Some Questions in Phase 0
For our User Interface, we were planning to implement a dual system at the beginning, one for the normal employees, and one for the manager. However, after multiple discussions, we are now implementing a single system and the User Interface being shown to the user is based on their own position, department and authority level. The process will be carried out when the user logins.
