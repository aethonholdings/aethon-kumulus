package com.scannerapp.ws.common.util.aop;
import java.lang.reflect.InvocationHandler;

/**
 * Extends the java.lang.reflect.InvocationHandler.
 * Responsibility: simpler management of the target object 
 */
public interface Delegate extends InvocationHandler {
 
    /**
     * Expects the target objects, which has to be decorated.
     * Is only used internally by the Decorator.
     * @see com.abien.util.Decorator
     * @param object The target which has to be decorated.
     */ 
    public void setTargetObject(Object object);

}
