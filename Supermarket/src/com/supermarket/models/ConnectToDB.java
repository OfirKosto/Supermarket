package com.supermarket.models;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

public  class ConnectToDB {
	
 public  Connection Connect() throws Exception
 {
	 String i_Url = "jdbc:sqlite:Resources\\StoreDB.db";
	 Connection i_Conn= null;
	 i_Conn = DriverManager.getConnection(i_Url);
	 if(i_Conn == null) {
	   throw new Exception("Error connecting to Database");
	 }
	return i_Conn;
 } 

public Employee SearchEmployee(String i_ID, job i_Job) throws Exception {
	 Employee i_new = null;
	
	 Connection i_Conn = this.Connect();
	 String i_SqlStatement ="select * from Employees where ID= ? and Job= ?";
	 PreparedStatement i_Statement = i_Conn.prepareStatement(i_SqlStatement);
	 i_Statement.setString(1, i_ID);
	 i_Statement.setString(2, i_Job.toString());
	 ResultSet i_TableFromDB = i_Statement.executeQuery();
	 
	 if( !i_TableFromDB.isClosed()) {
		 i_new =  EmployeeFactory.creatEmployee(i_TableFromDB.getString("Name"),i_TableFromDB.getString("ID"),i_TableFromDB.getFloat("HourlyWage"), i_TableFromDB.getFloat("MonthlyHours") ,job.valueOf(i_TableFromDB.getString("Job"))); 
	 }
	 else {
		 
		 throw new Exception("Invalid ID.");
	 }
	 i_Conn.close();
	 i_Statement.close();
	 return i_new;
} 

public Item SearchItem(String i_Barcode, job i_Job) throws Exception {
	 Item i_new = null;
	 Connection i_Conn = this.Connect();
	 String i_SqlStatement ="select * from Items where Barcode= ?";
	 PreparedStatement i_Statement = i_Conn.prepareStatement(i_SqlStatement);
	 i_Statement.setString(1, i_Barcode);
	 ResultSet i_TableFromDB = i_Statement.executeQuery();
	 if( !i_TableFromDB.isClosed()) {
		 if(i_Job == job.Storekeeper)
			 i_new =  ItemFactory.CreateNewItemWarehouse(i_TableFromDB.getString("Barcode"), i_TableFromDB.getString("Name"), i_TableFromDB.getInt("AvailableInWarehouse"), i_TableFromDB.getInt("MaxStockWarehouse"));
		 else if(i_Job == job.StoreWorker)	
			 i_new =  ItemFactory.CreateNewStoreItem(i_TableFromDB.getString("Barcode"), i_TableFromDB.getString("Name"), i_TableFromDB.getFloat("Price"), i_TableFromDB.getInt("AvailableInStore"), i_TableFromDB.getInt("MaxStockStore"));
	 }
	 else { 
		 throw new Exception("Invalid Barcode.");
	 }
	 i_Conn.close();
	 i_Statement.close();
	 return i_new;
}


}
