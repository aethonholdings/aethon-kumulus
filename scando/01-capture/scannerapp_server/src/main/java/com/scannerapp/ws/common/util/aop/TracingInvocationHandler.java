package com.scannerapp.ws.common.util.aop;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

import com.scannerapp.ws.common.util.log.MessageLogger;

public class TracingInvocationHandler implements Delegate{
	
	Logger logger = MessageLogger.getLogger();
	
	private Object target = null;
	
	public void setTargetObject(Object target) {
		this.target = target;
	}

	public Object invoke(Object proxy, Method method, Object parameters[])
		throws Throwable {
			Object retVal;
			Method targetMethod = null;
				try {
				long start = System.currentTimeMillis();
				logger.debug("Invoking: " + formatMethodOutput(method,parameters));
				targetMethod = getMethodForTarget(method,method.getParameterTypes());
				retVal = targetMethod.invoke(target, parameters);
				logger.debug(method.getName() + " returns: " + retVal + " performed in: " + (System.currentTimeMillis()- start) + " ms!");
				} catch (InvocationTargetException e) {
					logger.debug(method.getDeclaringClass().getName() + " error executing method " + method.getName() + " Error: " +
						e.getTargetException().toString());
					throw e.getTargetException();
				} catch (Exception e) {
					throw new RuntimeException("unexpected invocation exception: " + e.getMessage());
				}	
		return retVal;
	}
	
	private Method getMethodForTarget(Method method,Class parameterTypes[]) throws SecurityException{
		String name = method.getName();
		Method targetMethod;
		try {
			targetMethod = this.target.getClass().getMethod(name, parameterTypes);
		} catch (NoSuchMethodException e) {
			throw new IllegalStateException("The method with name: " + name + " was not found for target: " + target.getClass() + " Only those methods were found: " + getTargetMethodList());
		}
		return targetMethod;
	}
	
	private String getTargetMethodList() {
		String methodList = "";
		Method methods[] = this.target.getClass().getMethods();
		for (int i = 0; i < methods.length; i++) {
			methodList +=  methods[i].getName() + "\n";
		}
		return methodList;
	}

	private String args2String(Object args[]) {
		 String result = "";
		 if (args == null || args.length == 0)
			 return "";
		 for (int i = 0; i < args.length; i++) {
			 String temp = null;
			 if (args[i] != null)
				 temp = args[i].getClass().getName() + " " + args[i].toString();
		     if(i != args.length-1)
				 result += temp + ",";
			 else
			 	 result += temp;
		 }
		 return result;
	 }	
	 
	 private String formatMethodOutput(Method method,Object args[]){
	 	String returnTypeName = method.getReturnType().getName();
	 	Class exceptionTypes[] = method.getExceptionTypes();
	 	Class declaringClass = method.getDeclaringClass();
	 	String declaringClassName = declaringClass.getName();
	 	String methodName = method.getName();
	 	String retVal = returnTypeName + " " +declaringClassName + "." + methodName + "("+ args2String(args) +") " +formatExceptionTypes(exceptionTypes);
	 	return retVal;
	 }
	 
	 private String formatExceptionTypes(Class exceptionTypes[]){
	 	if(exceptionTypes == null || exceptionTypes.length == 0)
	 		return "";
	 	String retVal = "throws ";
	 	String temp   = null;
	 	for(int i=0;i<exceptionTypes.length;i++){
			temp = exceptionTypes[i].getName();
		if(i != exceptionTypes.length-1)
		  retVal += temp + ",";
		else
		  retVal += temp;
	 		
	 	}
	 	return retVal;
	 }

}
