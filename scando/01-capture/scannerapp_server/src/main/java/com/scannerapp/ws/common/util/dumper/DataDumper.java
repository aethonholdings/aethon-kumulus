package com.scannerapp.ws.common.util.dumper;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

import org.apache.log4j.Logger;

import com.scannerapp.ws.common.util.log.MessageLogger;

/**
 * DataDumper Class for Java Objects.
 *
 * @author Niraj Salot <niraj.salot@spec-india.com>
 * @since Dec 31, 2010
 */
public class DataDumper {
	
	/**
	 * Prints the values stored inside the array.
	 *
	 * @param object the object
	 */
	private static void printArray(Object object)
	{
		for (int i = 0; i < Array.getLength(object); i++)
		{
			Object o = Array.get(object, i);
			printObject(o);
		}
	}
	
	/**
	 * Prints the object value passed.
	 *
	 * @param object the object
	 */
	public static void printObject(Object object)
	{

		Logger logger = MessageLogger.getLogger();
		/** The field name. */
		String fieldName;
		
		Class<? extends Object> cClass = object.getClass();
		
		if (cClass.isArray())
		{
			printArray(object);
		} 
		else
		{
			Field[] publicFields = cClass.getDeclaredFields();
			for (int i = 0; i < publicFields.length; i++)
			{
				fieldName = publicFields[i].getName();
				Class<?> typeClass = publicFields[i].getType();
				String fieldType = typeClass.getName();

				if ((!fieldType.equals("int"))
						&& (!fieldType.equals("boolean"))
						&& (!fieldType.equals("java.lang.String"))
						&& (!fieldType.equals("short"))
						&& (!fieldType.equals("byte"))
						&& (!fieldType.equals("long"))
						&& (!fieldType.equals("float"))
						&& (!fieldType.equals("double"))
						&& (!fieldType.equals("char")))

				{
					try
					{											
						printObject(getPrivateField(object,fieldName));

					} catch (Exception exception)
					{
						exception.printStackTrace();
					}

				} else
				{

					try
					{
						Object anotherObj = getPrivateField(object,fieldName);
						if (anotherObj != null)
						{
							logger.debug(cClass.getName() + " : " + fieldName + " : " + anotherObj.toString());
						} 
						else
						{
							logger.debug(cClass.getName() + " : " + fieldName + " : " + " is Null ");
						}

					} catch (SecurityException securityException)
					{
						securityException.printStackTrace();
						
					} catch (Exception exception)
					{
						exception.printStackTrace();
					}
				}
			}
		}
	}


	/**
	 * Gets the private field.
	 *
	 * @param obj the obj
	 * @param fieldName the field name
	 * @return the private field
	 */
	private static Object getPrivateField (Object obj, String fieldName) {   
		
	    final Field fields[] = obj.getClass().getDeclaredFields();
	    for (int i = 0; i < fields.length; ++i) {
	      if (fieldName.equals(fields[i].getName())) {
	        try {
	          fields[i].setAccessible(true);
	          return fields[i].get(obj);
	        } 
	        catch (IllegalAccessException ex) {
	        	ex.printStackTrace();
	        }
	      }
	    }	    
	    return null;
	  }
}


