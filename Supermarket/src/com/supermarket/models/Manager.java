package com.supermarket.models;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Manager extends Employee {
	private static com.supermarket.models.job i_ManagerJob =job.Manager;
	
	public Manager(String i_Name,String i_ID, float i_HourlyWage) {
		super(i_ID, i_Name, i_HourlyWage,i_ManagerJob);
	
	} 
	
	
	 public void AddEmployee(Employee i_NewEmployee) throws Exception {
		 if(this.SearchEmploeeExist(i_NewEmployee.getID())) {
             throw new Exception("ID already exist.");
         }
		 
	     Connection i_Connection = DBSingleton.getInstance().Database.Connect();
	     String i_SqlMess = "Insert into Employees (ID,Job,Name,HourlyWage,MonthlyHours) Values(?,?,?,?,?)";
	     PreparedStatement i_Statement =  i_Connection.prepareStatement(i_SqlMess);
	     i_Statement.setString(1, i_NewEmployee.getID());
	     i_Statement.setString(2, i_NewEmployee.getJobType().toString());
	     i_Statement.setString(3, i_NewEmployee.getName());
	     i_Statement.setFloat(4, i_NewEmployee.getHourlyWage());
	     i_Statement.setFloat(5, i_NewEmployee.getMountlyHours());
	     i_Statement.execute();
	     i_Statement.close();
	     
	     }
	
	 public void UpdateEmployee(Employee i_Employee) throws Exception {
		
		 Connection i_Connection = DBSingleton.getInstance().Database.Connect();
		 String i_SqlStatement =" UPDATE Employees SET Name = ?, MonthlyHours = ?, HourlyWage = ?, Job = ? WHERE ID = ?";
		 PreparedStatement i_Statement =  i_Connection.prepareStatement(i_SqlStatement);
	     i_Statement.setString(1, i_Employee.getName());
	     i_Statement.setFloat(2, i_Employee.getMountlyHours());
	     i_Statement.setFloat(3, i_Employee.getHourlyWage());
	     i_Statement.setString(4, i_Employee.getJobType().toString());
	     i_Statement.setString(5, i_Employee.getID());
	     i_Statement.executeUpdate();
	     i_Statement.close();
	   
	 }
	 public ResultSet ReturnWageTable() throws Exception
     {
         
		 Connection i_Connection = DBSingleton.getInstance().Database.Connect();
		 String i_SqlMess = "SELECT ID,Name,(HourlyWage*MonthlyHours) as Wage FROM Employees";
         PreparedStatement i_Statement =  i_Connection.prepareStatement(i_SqlMess);
         ResultSet i_TableFromDB = i_Statement.executeQuery();
         i_Statement.closeOnCompletion();
         
         return i_TableFromDB;
     }
	 
	public void RemoveEmployee(String i_ID) throws Exception
	{
		if(this.getID().equalsIgnoreCase(i_ID)) {
			throw new Exception("Cannot remove current user.");
		}
		else {
		Connection i_Connection = DBSingleton.getInstance().Database.Connect();
		String i_SqlMess = "DELETE FROM Employees WHERE ID = ?";
	    PreparedStatement i_Statement =  i_Connection.prepareStatement(i_SqlMess);
	    i_Statement.setString(1, i_ID);
	    i_Statement.executeUpdate();
	    i_Statement.close();
	    
		}
	}	
	 
	public ResultSet ReturnEmployeeTable() throws Exception
	 {
		 
		 Connection i_Connection=DBSingleton.getInstance().Database.Connect();;
	     String i_SqlMess = "SElECT * FROM Employees";
	     PreparedStatement i_Statement =  i_Connection.prepareStatement(i_SqlMess);
	     ResultSet i_TableFromDB = i_Statement.executeQuery();
	     i_Statement.closeOnCompletion();
	    
	     return i_TableFromDB;
	 }
	public boolean SearchEmploeeExist(String i_ID) throws Exception {
        
         Connection i_Connection = DBSingleton.getInstance().Database.Connect();
         String i_SqlStatement ="select * from Employees where ID= ?";
         PreparedStatement i_Statement = i_Connection.prepareStatement(i_SqlStatement);
         i_Statement.setString(1, i_ID);
         ResultSet i_TableFromDB = i_Statement.executeQuery();
         boolean v_ret;
         if( !i_TableFromDB.isClosed()) {
             v_ret = true; 
         }
         else {

             v_ret = false;
         }
         i_Connection.close();
         i_Statement.close();
         return v_ret;
    }
	
	public boolean CreateWageReport() throws Exception {
		boolean isValid = false;
		ResultSet i_WageTable = this.ReturnWageTable();
		FileWriter fstream = new FileWriter("WageFile.txt");
		if(fstream != null) {
		BufferedWriter out = new BufferedWriter(fstream);
		out.write("Name\tID\tWage\t");
		out.newLine();
		while (i_WageTable.next()) { 
			out.write(i_WageTable.getString("Name") + "\t");
	        out.write(i_WageTable.getString("ID") + "\t");
	        out.write(Float.toString(i_WageTable.getFloat("Wage")) + "\t");
	        out.newLine();
		}
		isValid = true;
		out.close();
		i_WageTable.close();
		} 
		return isValid;
	}
}

