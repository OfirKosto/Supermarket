package com.supermarket.models;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StoreWorker extends Employee {
	private static com.supermarket.models.job i_StoreWorkerJob =job.StoreWorker;
	public StoreWorker(String i_Name,String i_ID, float i_HourlyWage) {
		super(i_ID, i_Name, i_HourlyWage,i_StoreWorkerJob);
	
	}

	
	public ResultSet ReturnStoreItemTable() throws Exception
	 {
		 Connection i_Connection = DBSingleton.getInstance().Database.Connect();
	     String i_SqlMess = "SELECT Barcode,Name,Price,AvailableInStore,MaxStockStore FROM Items";
	     PreparedStatement i_Statement =  i_Connection.prepareStatement(i_SqlMess);
	     ResultSet i_TableFromDB = i_Statement.executeQuery();
	     return i_TableFromDB;
	 }
	public void UpdateItem(Item i_NewItem) throws Exception {
        if (i_NewItem.getMaxStockStore() < i_NewItem.getAvailableInStore()) { 
            throw new Exception("Current quantity cannot be greater than max quantity!");
        }
      
        Connection i_Connection = DBSingleton.getInstance().Database.Connect();
        String i_SqlStatement ="UPDATE Items SET Name = ?, Price = ?, MaxStockStore = ?, AvailableInStore = ? WHERE Barcode = ?";
        PreparedStatement i_Statement = i_Connection.prepareStatement(i_SqlStatement);
        i_Statement.setString(1, i_NewItem.getItemName());
        i_Statement.setFloat(2, i_NewItem.getPrice());
        i_Statement.setInt(3, i_NewItem.getMaxStockStore());
        i_Statement.setInt(4, i_NewItem.getAvailableInStore());
        i_Statement.setString(5, i_NewItem.getBarcode());
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
        else if(i_TableFromDB.getInt(3)+i_AmountToOrder + i_ItemToOrder.getAvailableInStore() > i_ItemToOrder.getMaxStockStore()) {
            throw new Exception(String.format("Order to big, you can order %d more",( i_ItemToOrder.getMaxStockStore()-(i_TableFromDB.getInt("StoreOrder")+i_ItemToOrder.getAvailableInStore()))));
        }
        else {
           i_SqlMess = "UPDATE Orders SET StoreOrder = ?  WHERE Barcode = ?";
           i_Statement =  i_Connection.prepareStatement(i_SqlMess);
           i_Statement.setInt(1, i_TableFromDB.getInt("StoreOrder")+i_AmountToOrder);
           i_Statement.setString(2,i_ItemToOrder.getBarcode());
           i_Statement.executeUpdate();
        }
        i_Statement.close();
        i_Connection.close();
        

    }

	public ResultSet ReturnStoreOrderTable() throws Exception
    {
        
		Connection i_Connection = DBSingleton.getInstance().Database.Connect();
        String i_SqlMess = "SELECT Barcode,Name,StoreOrder FROM Orders";
        PreparedStatement i_Statement =  i_Connection.prepareStatement(i_SqlMess);
        ResultSet i_TableFromDB = i_Statement.executeQuery();
       
        return i_TableFromDB; 
    }
	
	public boolean OrderReportStore() throws Exception {
		boolean isValid = false;
		FileWriter fstream = new FileWriter("StoreOrderFile.txt");
		ResultSet i_OrderTable = this.ReturnStoreOrderTable();
		if(fstream != null) {
		BufferedWriter out = new BufferedWriter(fstream);
		out.write("Barcode\tName\tStore Order\t");
		out.newLine();
		while (i_OrderTable.next()) {
			if(i_OrderTable.getInt("StoreOrder")!=0) {
				out.write(i_OrderTable.getString("Barcode") + "\t");
				out.write(i_OrderTable.getString("Name") + "\t");
				out.write(Integer.toString(i_OrderTable.getInt("StoreOrder")) + "\t");
				out.newLine();
			}
		}
		isValid = true;
		out.close();
		i_OrderTable.close();
		} 
		return isValid;
	}
	
	public void ClearStoreOrder() throws Exception {
       
		Connection i_Connection = DBSingleton.getInstance().Database.Connect();
        String i_SqlMess ="UPDATE Orders SET StoreOrder = 0";
        PreparedStatement i_Statement =  i_Connection.prepareStatement(i_SqlMess);
        i_Statement.executeUpdate();
        i_Statement.close();
        i_SqlMess = "DELETE FROM Orders WHERE WarehouseOrder = 0";
        i_Statement =  i_Connection.prepareStatement(i_SqlMess);
        i_Statement.executeUpdate();
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
        	i_ValueToReturn = 0;
        }
        else {
        	i_ValueToReturn= i_TableFromDB.getInt("StoreOrder");
        }
        i_Statement.close();
       
       return i_ValueToReturn;
	}
}
