package com.supermarket.models;

import static org.junit.Assert.*;

import java.sql.ResultSet;

import org.junit.Before;
import org.junit.Test;

import com.supermarket.models.ConnectToDB;
import com.supermarket.models.Employee;
import com.supermarket.models.EmployeeFactory;
import com.supermarket.models.Manager;
import com.supermarket.models.job;

public class ManagerTest {
	
	Manager i_Manager;

	@Before
	public void setUp() throws Exception {
		i_Manager = new Manager("TestName","TestID",0);
	} 


	@Test
	public void testAddEmployee(){
		boolean thrown = false;
		try {
		Employee i_NewEmployee = EmployeeFactory.creatEmployee("AddTestName", "TestID123", 0, 0, job.Manager);
		i_Manager.AddEmployee(i_NewEmployee);
		assertTrue("AddEmployee method isn't working", i_Manager.SearchEmploeeExist("TestID123"));
		i_Manager.RemoveEmployee("TestID123");
		}
		catch(Exception e) {
			thrown = true;
		}
		assertFalse("AddEmployee Failed To Connect To Database",thrown);
	}
	
	@Test
	public void testRemoveEmployee(){
		boolean thrown = false;
		try {
		Employee i_NewEmployee = EmployeeFactory.creatEmployee("AddTestName", "TestID123", 0, 0, job.Manager);
		i_Manager.AddEmployee(i_NewEmployee);
		i_Manager.RemoveEmployee("TestID123");
		assertFalse("RemoveEmployee method isn't working", i_Manager.SearchEmploeeExist("TestID123"));
		}
		catch(Exception e) {
			thrown = true;
		}
		assertFalse("RemoveEmployee Failed To Connect To Database",thrown);
		if(thrown == true) {
			System.exit(0);
		}
	}
	
	@Test
	public void testSearchEmploeeExist() throws Exception {
		boolean thrown = false;
		try {
		Employee i_NewEmployee = EmployeeFactory.creatEmployee("AddTestName", "TestID123", 0, 0, job.Manager);
		i_Manager.AddEmployee(i_NewEmployee);
		assertTrue("SearchEmploeeExist method isn't working", i_Manager.SearchEmploeeExist("TestID123"));
		i_Manager.RemoveEmployee("TestID123");
		}
		catch(Exception e) {
			thrown = true;
		}
		assertFalse("SearchEmploeeExist Failed To Connect To Database",thrown);
	}
	

	@Test
	public void testUpdateEmployee(){
		boolean thrown = false;
		try {
		Employee i_NewEmployee = EmployeeFactory.creatEmployee("AddTestName", "TestID123", 0, 0, job.Manager);
		i_Manager.AddEmployee(i_NewEmployee);
		i_NewEmployee.setName("newName");
		i_Manager.UpdateEmployee(i_NewEmployee);
		ConnectToDB i_Connect = new ConnectToDB();
		Employee i_emp = i_Connect.SearchEmployee("TestID123", job.Manager);
		assertEquals("UpdateEmployee method isn't working",i_emp.getName(),"newName");
		i_Manager.RemoveEmployee("TestID123");
		}
		catch(Exception e) {
			thrown = true;
		}
		assertFalse("UpdateEmployee Failed To Connect To Database",thrown);		
	}

	@Test
	public void testReturnWageTable() {
		boolean thrown = false;
		try {
		Employee i_NewEmployee = EmployeeFactory.creatEmployee("AddTestName", "TestID123", 0, 0, job.Manager);
		i_Manager.AddEmployee(i_NewEmployee);
		ResultSet rs = i_Manager.ReturnWageTable();
		assertNotNull("ReturnWageTable method isn't working",rs);
		rs.close();
		i_Manager.RemoveEmployee("TestID123");
		}
		catch(Exception e) {
			thrown = true;
		}
		assertFalse("ReturnWageTable Failed To Connect To Database",thrown);
	}

	@Test
	public void testReturnEmployeeTable() {
		boolean thrown = false;
		try {
		Employee i_NewEmployee = EmployeeFactory.creatEmployee("AddTestName", "TestID123", 0, 0, job.Manager);
		i_Manager.AddEmployee(i_NewEmployee);
		ResultSet rs = i_Manager.ReturnEmployeeTable();
		assertNotNull("ReturnEmployeeTable method isn't working",rs);
		rs.close();
		i_Manager.RemoveEmployee("TestID123");
		}
		catch(Exception e) {
			thrown = true;
		}
		assertFalse("ReturnEmployeeTable Failed To Connect To Database",thrown);
	}
	
	@Test
	public void testCreateWageReport() {
		boolean thrown = false;
		try {
			assertTrue("CreateWageReport method isn't working", i_Manager.CreateWageReport());
		}
		catch(Exception e) {
			thrown = true;
		}
		assertFalse("CreateWageReport Failed To Connect To Database",thrown);
	}
	

}
