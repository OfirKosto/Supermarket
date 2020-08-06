package com.supermarket.models;
import java.util.*;
public abstract class Person {
	private String Name;
	private String ID;
	public Person(String i_Name,String i_ID) {
		this.Name = i_Name;
		this.ID= i_ID;
	}
	public String getName() {
		return this.Name;
	}
	public void setName(String i_Name) {
		this.Name=i_Name;
	}
	public String getID() {
		return this.ID;
	}
}
