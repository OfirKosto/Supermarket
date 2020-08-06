package com.supermarket.models;

import static org.junit.Assert.*;

import java.sql.ResultSet;

import org.junit.Before;
import org.junit.Test;

import com.supermarket.models.ConnectToDB;
import com.supermarket.models.Item;
import com.supermarket.models.ItemFactory;
import com.supermarket.models.StoreWorker;
import com.supermarket.models.Storekeeper;
import com.supermarket.models.job;

public class StoreWorkerTest {
	
	StoreWorker i_StoreWorker;
	Storekeeper i_Storekeeper;
	
	@Before
	public void setUp() throws Exception {
		i_StoreWorker = new StoreWorker("TestName","TestID",0);
		i_Storekeeper = new Storekeeper("TestStorekeeperName","TestStorekeeperID",0);
	}

	@Test
	public void testReturnStoreItemTable() {
		boolean thrown = false;
		try {
		Item i_NewItem = ItemFactory.CreateNewStoreItem("TestBarcode","TestItemName",0 ,0,0);
		i_Storekeeper.AddItem(i_NewItem);
		ResultSet rs = i_StoreWorker.ReturnStoreItemTable();
		assertNotNull("ReturnStoreItemTable method isn't working",rs);
		rs.close();
		i_Storekeeper.RemoveItem("TestBarcode");
		}
		catch(Exception e) {
			thrown = true;
		}
		assertFalse("ReturnStoreItemTable Failed To Connect To Database",thrown);
	}

	@Test
	public void testUpdateItem() {
		boolean thrown = false;
		try {
		Item i_NewItem = ItemFactory.CreateNewStoreItem("TestBarcode","TestItemName",0 ,0,0);
		i_Storekeeper.AddItem(i_NewItem);
		i_NewItem.setName("newName");
		i_StoreWorker.UpdateItem(i_NewItem);
		ConnectToDB i_Connect = new ConnectToDB();
		Item i_Item = i_Connect.SearchItem("TestBarcode", job.StoreWorker);
		assertEquals("UpdateItem method isn't working",i_Item.getItemName(),"newName");
		i_Storekeeper.RemoveItem("TestBarcode");
		}
		catch(Exception e) { 
			thrown = true;
		}
		assertFalse("UpdateItem Failed To Connect To Database",thrown);		
	}
	
	@Test
	public void testOrderReportStore() {
		boolean thrown = false;
		try {
			assertTrue("OrderReportStore method isn't working", i_StoreWorker.OrderReportStore());
		}
		catch(Exception e) {
			thrown = true;
		}
		assertFalse("OrderReportStore Failed To Connect To Database",thrown);
	}
	
}
