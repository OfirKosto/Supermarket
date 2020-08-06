package com.supermarket.models;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Storekeeper extends Employee{
	private static com.supermarket.models.job i_StorekeeperJob =job.Storekeeper;
	public Storekeeper(String i_Name,String i_ID, float i_HourlyWage) {
		super(i_ID, i_Name, i_HourlyWage,i_StorekeeperJob);
	
	}
	
	
	public ResultSet ReturnWarehouseItemTable() throws Exception
	 {
		Connection i_Connection = DBSingleton.getInstance().Database.Connect(); 
		String i_SqlMess = "SELECT Barcode,Name,AvailableInWarehouse,MaxStockWarehouse FROM Items";
	     PreparedStatement i_Statement =  i_Connection.prepareStatement(i_SqlMess);
	     ResultSet i_TableFromDB = i_Statement.executeQuery();
	     i_Statement.closeOnCompletion();
	     return i_TableFromDB;
	 }
	 public void AddItem(Item i_NewItem) throws Exception {
		 if(this.SearchItemExist(i_NewItem.getBarcode())) {
             throw new Exception("Barcode already exist.");
         }
		 Connection i_Connection = DBSingleton.getInstance().Database.Connect();
		 String i_SqlStatement ="INSERT INTO Items VALUES(?,?,?,?,?,?,?)";
		 PreparedStatement i_Statement = i_Connection.prepareStatement(i_SqlStatement);
		 i_Statement.setString(1, i_NewItem.getBarcode());
		 i_Statement.setString(2, i_NewItem.getItemName());
		 i_Statement.setFloat(3, i_NewItem.getPrice());
		 i_Statement.setInt(4, i_NewItem.getMaxStockStore());
		 i_Statement.setInt(5, i_NewItem.getMaxStockWarehouse());
		 i_Statement.setInt(6, i_NewItem.getAvailableInStore());
		 i_Statement.setInt(7, i_NewItem.getAvailableInWarehouse());
		 i_Statement.execute();
		 i_Statement.close();
	 }
	 public void RemoveItem(String i_Barcode) throws Exception
		{
		 	Connection i_Connection = DBSingleton.getInstance().Database.Connect();   
		 	String i_SqlMess = "DELETE FROM Items WHERE Barcode = ?";
		    PreparedStatement i_Statement =  i_Connection.prepareStatement(i_SqlMess);
		    i_Statement.setString(1, i_Barcode);
		    i_Statement.executeUpdate();
		    i_Statement.close();
		   
		}
	 
	 public void OrderItem(Item i_ItemToOrder,int i_AmountToOrder) throws Exception{
		 
		 Connection i_Connection = DBSingleton.getInstance().Database.Connect();
		 String i_SqlMess = "SELECT * FROM Orders WHERE Barcode = ?";
		 PreparedStatement i_Statement =  i_Connection.prepareStatement(i_SqlMess);
		 i_Statement.setString(1, i_ItemToOrder.getBarcode());
		 ResultSet i_TableFromDB = i_Statement.executeQuery();
		 if(i_TableFromDB.isClosed()) {
			 i_SqlMess="INSERT INTO Orders VALUES(?,?,0,?)";
			 i_Statement =  i_Connection.prepareStatement(i_SqlMess);
			 i_Statement.setString(1, i_ItemToOrder.getBarcode());
			 i_Statement.setString(2, i_ItemToOrder.getItemName());
			 i_Statement.setInt(3, i_AmountToOrder);
			 i_Statement.execute();
		 }
		 else if(i_TableFromDB.getInt(3)+i_AmountToOrder + i_ItemToOrder.getAvailableInWarehouse() > i_ItemToOrder.getMaxStockWarehouse()) {
			 throw new Exception(String.format("Order to big, you can order %d more", i_ItemToOrder.getMaxStockWarehouse()-(i_TableFromDB.getInt("WarehouseOrder")+i_ItemToOrder.getAvailableInWarehouse())));
		 }
		 else {
			i_SqlMess = "UPDATE Orders SET WarehouseOrder = ?  WHERE Barcode = ?";
			i_Statement =  i_Connection.prepareStatement(i_SqlMess);
			i_Statement.setInt(1, i_TableFromDB.getInt("WarehouseOrder")+i_AmountToOrder);
			i_Statement.setString(2,i_ItemToOrder.getBarcode());
			i_Statement.executeUpdate();
		 }
		 i_Statement.close();
		 i_Connection.close();
	     
	 }
	 
	 public ResultSet ReturnWarehouseOrderTable() throws Exception
	 {
		 	Connection i_Connection = DBSingleton.getInstance().Database.Connect(); 
		 	String i_SqlMess = "SELECT Barcode, Name, WarehouseOrder FROM Orders";
	        PreparedStatement i_Statement =  i_Connection.prepareStatement(i_SqlMess);
	        ResultSet i_TableFromDB = i_Statement.executeQuery();
	        return i_TableFromDB;
	    }
		
	 public void UpdateItem(Item i_NewItem) throws Exception {
		 
		 Connection i_Connection = DBSingleton.getInstance().Database.Connect();
		 String i_SqlStatement ="UPDATE Items SET Name = ?,AvailableInWarehouse = ?, MaxStockWarehouse = ? WHERE Barcode = ?";
		 PreparedStatement i_Statement =  i_Connection.prepareStatement(i_SqlStatement);
	     i_Statement.setString(1, i_NewItem.getItemName());
	     i_Statement.setFloat(2, i_NewItem.getAvailableInWarehouse());
	     i_Statement.setFloat(3, i_NewItem.getMaxStockWarehouse());
	     i_Statement.setString(4, i_NewItem.getBarcode());
	     i_Statement.executeUpdate();
	     i_Statement.close();
	 }
	 public boolean SearchItemExist(String i_Barcode) throws Exception {
         
		 Connection i_Connection = DBSingleton.getInstance().Database.Connect();
		 String i_SqlStatement ="select * from Items where Barcode= ?";
         PreparedStatement i_Statement = i_Connection.prepareStatement(i_SqlStatement);
         i_Statement.setString(1, i_Barcode);
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
	 
		public boolean OrderReportWarehouse() throws Exception {
			boolean isValid = false;
			FileWriter fstream = new FileWriter("WarehouseOrderFile.txt");
			ResultSet i_OrderTable = this.ReturnWarehouseOrderTable();
			if(fstream != null) {
			BufferedWriter out = new BufferedWriter(fstream);
			out.write("Barcode\tName\tWarehouse Order\t");
			out.newLine();
			while (i_OrderTable.next()) { 
				if(i_OrderTable.getInt("WarehouseOrder")!=0) {
					out.write(i_OrderTable.getString("Barcode") + "\t");
					out.write(i_OrderTable.getString("Name") + "\t");
					out.write(Integer.toString(i_OrderTable.getInt("WarehouseOrder")) + "\t");
					out.newLine();
				}
			}
			isValid = true;
			out.close();
			i_OrderTable.close();
			} 
			return isValid;
		}
		
		public void ClearWarehouseOrder() throws Exception {
			 Connection i_Connection = DBSingleton.getInstance().Database.Connect(); 
			 String i_SqlMess ="UPDATE Orders SET WarehouseOrder = 0";
	         PreparedStatement i_Statement =  i_Connection.prepareStatement(i_SqlMess);
	         i_Statement.executeUpdate();
	         i_Statement.close();
	         i_SqlMess = "DELETE FROM Orders WHERE StoreOrder = 0";
	         i_Statement =  i_Connection.prepareStatement(i_SqlMess);
	         i_Statement.executeUpdate();
	         i_Connection.close();
	         i_Statement.close();
	     }
		public int ReturnMaxOrder(String i_Barcode) throws Exception{
			 
			 Connection i_Connection = DBSingleton.getInstance().Database.Connect();  
			 String i_SqlMess = "SELECT * FROM Orders WHERE Barcode = ?";
			 PreparedStatement i_Statement =  i_Connection.prepareStatement(i_SqlMess);
			 i_Statement.setString(1, i_Barcode);
			 ResultSet i_TableFromDB = i_Statement.executeQuery();
			 int i_ValueToReturn;
			 		 
			 if(i_TableFromDB.isClosed()) {
				 i_ValueToReturn= 0;
			 }
			 else {
				 i_ValueToReturn= i_TableFromDB.getInt("WarehouseOrder");
			 }
			 i_Statement.close();
			 
			 return i_ValueToReturn;
		}
}
