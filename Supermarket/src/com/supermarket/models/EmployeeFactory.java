package com.supermarket.models;

//Factory Design Pattern
public class EmployeeFactory {
	public static Employee creatEmployee(String i_Name,String i_ID, float i_HourlyWage, float i_MonthlyHours, com.supermarket.models.job e_JobType){
		
		Employee newEmployee = null;
		
		if (e_JobType == com.supermarket.models.job.Manager)
		{
			newEmployee = new Manager(i_Name, i_ID, i_HourlyWage);
		}
		else if(e_JobType == com.supermarket.models.job.StoreWorker)
		{
			newEmployee = new StoreWorker(i_Name, i_ID, i_HourlyWage);
		}
		else if(e_JobType == com.supermarket.models.job.Storekeeper)
		{
			newEmployee = new Storekeeper(i_Name, i_ID, i_HourlyWage);
		}
		
		return newEmployee;
	}
	
}
