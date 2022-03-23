# Supermarket
Supermarket management system using Java and SQLite database.

System users types:
- Manager
- Storekeeper
- Store Worker

## Main Menu
![welcome](https://user-images.githubusercontent.com/59827963/159763418-27dc5f0f-bef1-4926-9182-3cc7c9315e5d.png)    
The menu navigates each employee to his part of the system.
Each employee should press the button with his job written on it and a login window will pop.

## Login Page
![login](https://user-images.githubusercontent.com/59827963/159763581-ee6b29a5-2442-4300-97bb-e27e2531af33.png)   
In order to login the employee has to enter his employee number.
The system checks if the employee number is valid and if the user clicked on the right job type.

## Manager
The manager job is to manage the supermarket employees.
When the manager enters the system with his details, a table appears with all the employees details.
![image](https://user-images.githubusercontent.com/59827963/159758039-a8e26490-87f1-46f9-96b2-b0bde526bf9a.png)

Possible actions of the manager:
- Add Employee
- Update Employee information
- Delete Employee
- Calculate Wages (gives a report of all the employee wages)
- cancel (back to the main menu)

## Storekeeper
The storekeeper job is to manage the supply in the warehouse and deliver products to the store.
When the storekeeper enters the system with his details, a table appears with all the products in the warehouse and their details.
![image](https://user-images.githubusercontent.com/59827963/159759900-e6e9875f-4403-4842-a7d2-9122dee0d0bb.png)

Possible actions of the storekeeper:
- Add New Item
- Update Item Quantity
- Order Items
- Delete Item
- Generate Order Report (order from the supplier)
- cancel (back to the main menu)

## Store Worker
The store worker job is to manage the supply in the store.
When the store worker enters the system with his details, a table appears with all the products in the store and their details.
![image](https://user-images.githubusercontent.com/59827963/159761998-1115b17c-a813-4f6d-9add-ac206d0c8c15.png)

Possible actions of the store worker:
- Update Item
- Order Item
- Generate Order Report (from the warehouse)
- cancel (back to the main menu)

## Important notes
In order to use the system for the first time without knowing the staff ids I made an admin manager for the system.
All you need to do is run the program, click Manager and then write "admin" in the id box, after that you can know all the staff ids and even creat new ones.
Another thing you need to know is if you want to change/order something from the tables you have to click it in the table and then select the option you want.


