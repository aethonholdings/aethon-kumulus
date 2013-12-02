package com.scannerapp.ws.common.util.aop;
import java.lang.reflect.Proxy;

/**
 * Simplifies the decoration of arbitrary classes with dynamic proxies.
 * Although the dynamic proxy approach works since JDK 1.3, this class uses
 * generics, so is only usable in JDK > 1.5.
 * <br/>
 * Example:
 * <pre>
 * <code>
 * 		Map map =(Map) Decorator.trace(new HashMap(),Map.class);
 * </code>
 * </pre>

 */
public class Decorator {
    /**
     *
     * @param target the instance to decorate
     * @param view the interface, which methods has to be decorated. Target should implement this interface
     * @param handler the concrete delegate which will
     * @return the target decorated with the handler - the type is derived from the parameter view
     */
    public static <T> T decorate(Object target,Class<T> view, Delegate handler) {
        handler.setTargetObject(target);
        Class allInterfaces[] = new Class[]{view};
        return (T)Proxy.newProxyInstance(target.getClass().getClassLoader(),allInterfaces,handler);
    }
    
    /**
     * A convenience-method which uses the com.abien.util.TracingInvocationHandler 
     * behind the scenes. See also @see Decorator#decorate(Object target,Class<T> view, Delegate handler)
     * @param target the instance to decorate
     * @return the target decorated with the @see com.abien.util.TracingInvocationHandler - the type is derived from the parameter view
     */
    public static  <T> T trace(Object target, Class<T> view) {
        return decorate(target,view,new TracingInvocationHandler());
    }
}
