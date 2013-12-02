package com.scannerapp.ws.common.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.scannerapp.ws.common.util.configuration.ApplicationConstants;

/**
 * This class contains various utility methods those are useful across the application (or belong to any specific functional requirements) 
 * @author Vipul
*/

public class CommonUtility 
{
	/**
	 * This method is used to get the class name (removes the package name) 
	 * @param cls - Class for which user wants class name 
	 * @return String - Class name
	 * @author vipulv 
	*/
	@SuppressWarnings("unchecked")
	public static String getClassName(Class  cls)
	{
		//Fully Qualified Class name with package
		String className = cls.getName();
		//IF class is in Package
		if(className.lastIndexOf(ApplicationConstants.STRING_DOT) != -1) 
		{
			className = className.substring(className.lastIndexOf(ApplicationConstants.STRING_DOT) + 1,className.length());
		}
		return className;
	}
	
	
	/**
	 * This method is used to get value of properties of Object as Object Array 
	 * @param cls - Class for which user wants class name 
	 * @return String - Class name
	 * @author vipulv 
	*/
	public static Object[] getPropertyValuesAsObjectArray(Object bean) 
    {
    	//get All the fields of bean class
    	Field[] fields = bean.getClass().getDeclaredFields();
    	// Get all the fields of bean class's Parent class
        Field[] parentFields = bean.getClass().getSuperclass().getDeclaredFields();
        //Make the object Array
        Object[] object = new Object[fields.length+parentFields.length];
        //start with zero Index
        int index = 0; 
        //Loop through bean class's fields
        try
        { 
        	for(Field  field : fields) 
        	{
        		//set the private field accessible 
        		field.setAccessible(true);
        		object[index] =  field.get(bean); 
        		index++; 
        	}
        	//Loop through parent class's fields
        	for(Field  parentField:parentFields) 
        	{
        		//set the private field accessible
        		parentField.setAccessible(true);
        		object[index] =  parentField.get(bean);
        		index++;
        	}
        }
        catch(IllegalAccessException ie) {
        }
        return object;
    	
    }
	
	/**
	 * This method is used to get value of properties of Object as Object Array 
	 * @param List<T> - Generic List (List of Object for which user want to convert ints property value to Object array)  
	 * @return ArrayList<Object[]>  Object of ArrayList<Object[]>
	 * @author vipulv 
	*/
	public static <T> List<Object[]> fromListToObjectArrayList(List<T> lstT)  
	{
		 List<Object[]> objArrayList = new ArrayList<Object[]>();
		 for (T obj : lstT)   {
		   objArrayList.add(getPropertyValuesAsObjectArray(obj));
		 }
	     return objArrayList;
	}
	
	/**
	 * This method is used to get value of Object Array to String 
	 * @param Object - Object Array  
	 * @return String String Object as Value 
	 * @author vipulv 
	*/
	public static String getObjectArrayAsString(Object ...objectData) 
	{
		StringBuilder queryData = new StringBuilder("");
		queryData.append(ApplicationConstants.START_PARANTHESES);
		//Iterating Through Object Array
		for(Object objectVal : objectData) 
		{
			queryData.append(objectVal + ApplicationConstants.COMMA);
		}
		//End Object Array
		queryData = new StringBuilder(removeLastOccurenceOfString(queryData.toString(),ApplicationConstants.COMMA)); 
		queryData.append(ApplicationConstants.END_PARANTHESES);
		//Returning Object Array as String Value
		return queryData.toString();
		
	}
	
	/**
	 * This method is used to remove occurance of  stringToRemove in data string
	 * @param data - String object for which you want to remove last occurance of string
	 * @param stringToRemove - String that you want to remove
	 * @return String String Object as Value 
	 * @author vipulv 
	*/
	public static String removeLastOccurenceOfString(String data,String stringToRemove) 
	{
		//get the last index of stringToRemove
		int lastIndex = data.lastIndexOf(stringToRemove);
		//get the total length of stringToRemove
		int length = data.length();
		//sub string the data and return to caller
		return data.substring(0,lastIndex != -1 ? lastIndex : length);
	}

}
