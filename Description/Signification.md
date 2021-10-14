#Domain:
Human Resource Management System (HRM System)

#Description:
HRM System is an application designed for information technology industries that help different sectors to organize and manage the resources and their employee information. Users of this application can perform login/logout action, and according to the user's authority, the application will display different user interfaces: Admin UI and Employee UI. Admins can create or delete employee accounts if new employees join the company or employees leave their position. High-level workers (who have a high authority level) in each sector may access and edit the information stored in the system. Low-level workers (who have a low authority level) can review their personal information and distribute works that they need to complete. It also provides related support for employees to use while accomplishing their work.


#Some basic Entities:

**Employee**: To save an employee's work information and personal profile. It is divided into two categories:
        Part-time workers: These employees have very limited authority (lowest) and their calculation of wages and records of attendance will be addressed differently compared to Full-time workers.
        Full-time workers: These employees may belong to different sectors and have different jobs. Their authority levels vary according to their position at company's hierarchy. The variety of positions and job titles of the employees may support future functions in work distribution.

**User**: To save the general information of an employee. It is used along with the employee entity.

**Work**: To save basic information about a project and work requirements. (This has not been implemented yet)


#Target features:
For each use of the application, the employee and work-related information are loaded and auto-updated to the newest version.
The application displays different user interfaces according to the department and authority of an employee.
Part-time and Full-time employees will have different attendance systems, work assessments, and salary distribution.
Work assigning function, job completion evaluator, workgroup/project team creation
Altering and updating employee accounts: employee promotions, hiring new employees, and the resignation of employees.
