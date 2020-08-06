package com.supermarket.models;

import static org.junit.Assert.*;

import java.sql.ResultSet;

import org.junit.Before;
import org.junit.Test;

import com.supermarket.models.ConnectToDB;
import com.supermarket.models.Item;
import com.supermarket.models.ItemFactory;
import com.supermarket.models.Storekeeper;

public class StorekeeperTest {

	 Storekeeper i_Storekeeper; 
	@Before
	public void setUp() throws Exception {
		i_Storekeeper = new Storekeeper("testStorekeeper", "testId", 0);
	}

	@Test
	public void testReturnWarehouseItemTable() {
		boolean thrown = false;
		try {
		Item i_TestItem= ItemFactory.CreateNewItemWarehouse("testBarcode", "testName", 0, 0);
		i_Storekeeper.AddItem(i_TestItem);
		ResultSet rs = i_Storekeeper.ReturnWarehouseItemTable();
		assertNotNull("ReturnWageTable method isn't working",rs);
		rs.close();
		i_Storekeeper.RemoveItem("testBarcode");
		}
		catch(Exception e) {
			thrown = true;
		}
		assertFalse("ReturnWageTable Failed To Connect To Database",thrown);
	}

	@Test
	public void testAddItem() {
		boolean thrown = false;
		try {
		Item i_TestItem= ItemFactory.CreateNewItemWarehouse("testBarcode", "testName", 0, 0);
		i_Storekeeper.AddItem(i_TestItem);
		assertTrue("AddItem method isn't working", i_Storekeeper.SearchItemExist("testBarcode"));
		i_Storekeeper.RemoveItem("testBarcode");
		}
		catch(Exception e) {
			thrown = true;
		}
		assertFalse("RemoveItem Failed To Connect To Database",thrown);
	}
	

	@Test
	public void testRemoveItem() {
		boolean thrown = false;
		try {
		Item i_TestItem= ItemFactory.CreateNewItemWarehouse("testBarcode", "testName", 0, 0);
		i_Storekeeper.AddItem(i_TestItem);
		i_Storekeeper.RemoveItem("testBarcode");
		assertFalse("RemoveItem method isn't working", i_Storekeeper.SearchItemExist("testBarcode"));
		}
		catch(Exception e) {
			thrown = true;
		}
		assertFalse("RemoveItem Failed To Connect To Database",thrown);
	}

	@Test
	public void testUpdateItem() {
		boolean thrown = false;
		try {
		Item i_TestItem= ItemFactory.CreateNewItemWarehouse("testBarcode", "testName", 0, 0);
		i_Storekeeper.AddItem(i_TestItem);
		i_TestItem.setName("TestBarcode");
		i_Storekeeper.UpdateItem(i_TestItem);
		ConnectToDB i_Connect = new ConnectToDB();
		assertEquals("UpdateItem method isn't working",i_Connect.SearchItem("testBarcode", i_Storekeeper.getJobType()).getItemName(),i_TestItem.getItemName());
		i_Storekeeper.RemoveItem("testBarcode");
		}
		catch(Exception e) {
			thrown = true;
		}
		assertFalse("UpdateItem Failed To Connect To Database",thrown);
	}

	

	@Test
	public void testSearchItemExist() {
		boolean thrown = false;
		try {
		Item i_TestItem= ItemFactory.CreateNewItemWarehouse("testBarcode", "testName", 0, 0);
		i_Storekeeper.AddItem(i_TestItem);
		assertTrue("SearchItemExist method isn't working", i_Storekeeper.SearchItemExist("testBarcode"));
		i_Storekeeper.RemoveItem("testBarcode");
		}
		catch(Exception e) {
			thrown = true;
		}
		assertFalse("SearchItemExist Failed To Connect To Database",thrown);
	}
	
	@Test
	public void testOrderReportWarehouse() {
		boolean thrown = false;
		try {
			assertTrue("OrderReportWarehouse method isn't working", i_Storekeeper.OrderReportWarehouse());
		}
		catch(Exception e) {
			thrown = true;
		}
		assertFalse("OrderReportWarehouse Failed To Connect To Database",thrown);
	}

}
