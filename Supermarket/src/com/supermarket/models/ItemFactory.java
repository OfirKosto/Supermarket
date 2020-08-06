package com.supermarket.models;

public class ItemFactory {
public static Item CreateNewItemWarehouse(String i_Barcode,String i_Name,int i_AvailableInWarehouse,int i_MaxStockWarehouse) throws Exception {
	Item i_NewItem = new Item(i_Name, i_Barcode, 0, 0, i_MaxStockWarehouse, 0, i_AvailableInWarehouse);
	return i_NewItem;
}

public static Item CreateNewStoreItem(String i_Barcode,String i_Name,float i_Price ,int i_AvailableInStore,int i_MaxStockStore) throws Exception {
    Item i_NewItem = new Item(i_Name, i_Barcode, i_Price, i_MaxStockStore,0, i_AvailableInStore, 0);
    return i_NewItem;
}
} 
