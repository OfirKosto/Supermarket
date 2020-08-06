package com.supermarket.models;


public class DBSingleton {
	  private static  DBSingleton instance = null;
	    public ConnectToDB Database;
	    private DBSingleton()
	    {  
	    	Database= new ConnectToDB();
	    	
	    }
	    public static DBSingleton getInstance()
	    {
	    	if(instance == null) {
	    		synchronized(DBSingleton.class) {
	    			if(instance == null)
	    				instance = new DBSingleton();
				}
	    	}
	    		
	        return instance;
	    }
}
