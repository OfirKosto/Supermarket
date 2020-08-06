package com.supermarket.models;
import java.util.*;

public class Item {
	private String Name;
	private String Barcode;
	private float Price;
	private int MaxStockStore;
	private int MaxStockWarehouse;
	private int AvailableInStore;
	private int AvailableInWarehouse; 

	public Item(String i_Name,String i_Barcode,float i_Price,int i_MaxStockStore,int i_MaxStockWarehouse, int i_AvailableInStore,int i_AvailableInWarehouse) throws Exception{
		if(i_MaxStockStore<i_AvailableInStore || i_MaxStockWarehouse<i_AvailableInWarehouse) {
            throw new Exception("Max stock must be greater than available stock.");
        }
		this.Name=i_Name;
		this.Barcode=i_Barcode;
		this.Price=i_Price;
		this.MaxStockStore=i_MaxStockStore;
		this.MaxStockWarehouse=i_MaxStockWarehouse;
		this.AvailableInStore=i_AvailableInStore;
		this.AvailableInWarehouse=i_AvailableInWarehouse;
	
	} 

	public String getItemName() {
		return this.Name;
	}
	public void setName(String i_Name) {
		this.Name=i_Name;
	}
	public String getBarcode() {
		return this.Barcode;
	}
	public float getPrice() {
		return this.Price;
	}
	public void setPrice(float i_Price) {
		this.Price=i_Price;
	}
	public int getMaxStockStore() {
		return this.MaxStockStore;
	}
	public void setMaxStockStore(int i_MaxStockStore) {
		this.MaxStockStore=i_MaxStockStore;
	}
	public int getMaxStockWarehouse() {
		return this.MaxStockWarehouse;
	}
	public void setMaxStockWarehouse(int i_MaxStockWarehouse) {
		this.MaxStockWarehouse=i_MaxStockWarehouse;
	}
	public int getAvailableInStore() {
		return this.AvailableInStore;
	}
	public void setAvailableInStore(int i_AvailableInStore) {
		this.AvailableInStore=i_AvailableInStore;
	}
	public int getAvailableInWarehouse() {
		return this.AvailableInWarehouse;
	}
	public void setAvailableInWarehouse(int i_AvailableInWarehouse) {
		this.AvailableInWarehouse=i_AvailableInWarehouse;
	}
}
	
