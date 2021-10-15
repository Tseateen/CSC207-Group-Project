# Domain:
Human Resource Management System (HRM System)

# Description:
HRM System is an application designed for information technology industries that help different sectors to organize and manage the resources and their employee information. Users of this application can perform login/logout action, and according to the user's authority, the application will display different user interfaces: Admin UI and Employee UI. Admins can create or delete employee accounts if new employees join the company or employees leave their position. High-level workers (who have a high authority level) in each sector may access and edit the information stored in the system. Low-level workers (who have a low authority level) can review their personal information and distribute works that they need to complete. It also provides related support for employees to use while accomplishing their work.

# Imformation and Details

## Entities:

- **Employee**: To save an employee's work information and personal profile. It is divided into two categories:

    - ***Part-time workers***: These employees have very limited authority (lowest) and their calculation of wages and records of attendance will be addressed differently compared to Full-time workers.

    - ***Full-time workers***: These employees may belong to different sectors and have different jobs. Their authority levels vary according to their position at company's hierarchy. The variety of positions and job titles of the employees may support future functions in work distribution.

- **User**: To save the general information of an employee. It is used along with the employee entity.

- **Work**: To save basic information about a project and work requirements.

- **Group**: To save information of a workgroup/project team. Including the project, the leader, and team members.

## Use Cases
- **AccountManager**: To manage all the accounts in the system. Including account creation and deletion.

- **Verifier**: To verify an account's username and its password.

- **PayManager**: To distribute wages to employee base on specific evaluations.

- **AccountChanger**: To alter and update account information

- **WorkManager**: To manage all the work-related information. Stores the unfinished work. Records the team information of finished projects.

- **WorkDistributor**: To distribute newly assigned work, and create workgroup/project team.

- **VacationManager**: To manage the employee's vacation and set the employee's status (on vacation/not on vacation).

- **AuthorityManager**: To perform authority-related tasks.


# Target features:
- Loads information of the account when employee logs in
- Loads updated version of work-related information when employee logs in
- Displays user interface according to the department and authority of an employee.
- Records attendance information for full-time employees
- Records attendance information for part-time employees in a different system than full-time employees
- Performs work assigning function
- Evaluates job completion
- Creates workgroup/project team
- Alters and updates employee account according to position variations (promotion, entry, resignation of the employee)
- Stores employee's working hours
- Calculates wages according to the KPI, position and working hour of the employee
- Distribute wages to employee
- Calculates KPI of the employee
- Addresses vacation applications
- Records and stores vaccination information of the employee
