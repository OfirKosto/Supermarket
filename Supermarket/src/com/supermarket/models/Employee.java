package com.supermarket.models;
import java.util.*;

import com.supermarket.models.job;
public abstract class Employee extends Person {
	
	private float MonthlyHours;
	private float HourlyWage;
	private com.supermarket.models.job job;
	
 	public Employee(String i_ID, String i_Name, float i_HourlyWage, job e_JobType) {
		super(i_Name,i_ID);
		this.MonthlyHours = 0;
		this.HourlyWage=i_HourlyWage;
		this.job=e_JobType;
	}
 	
	public float getMountlyHours() {
		return this.MonthlyHours;
	}
	public void setMounthlyHours(float i_MounthlyHours) {
		this.MonthlyHours=i_MounthlyHours;
	}
	public float getHourlyWage() {
		return this.HourlyWage;
	}
	public void setHourlyWage(float i_HourlyWage) {
		this.HourlyWage=i_HourlyWage;
	}
	public com.supermarket.models.job getJobType(){
		return this.job;
	}
	public void setJobType(com.supermarket.models.job i_Job) {
		this.job = i_Job;
	}
}
