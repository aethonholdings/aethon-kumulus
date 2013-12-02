package com.scannerapp.apps.framework.utils;
import java.util.Hashtable;
import java.util.StringTokenizer;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: System Plus P. Ltd</p>
 * @author Kanhaiya Samariya
 * @version 1.0
 */
public class CommandLineParser implements java.io.Serializable {
    private String[] _args;
//    private Hashtable _properties;
    private String _token = "=";
    public CommandLineParser() {
        super();
    }
    public CommandLineParser(String args[]) {
        super();
        setArgs(args);
    }
    public Hashtable getProperties() {
        return System.getProperties();
    }
    public String[] getArgs() {
        return _args;
    }
    public void setArgs(String[] args) {
        _args = args;
        parse();
    }
    public String getToken() {
        return _token;
    }
    public void setToken(String token) {
        _token = token;
    }
    public void setProperty(String key, String value) {
        getProperties().put(key, value);
    }
    private void parse() {
        String value = "";
        String key = "";
        for(int i = 0;i < getArgs().length;i++) {
            try {
                String arg = getArgs()[i];
                StringTokenizer tokenizer = new StringTokenizer(arg, getToken());
                key = tokenizer.nextToken();
                if(arg.indexOf(getToken()) == -1) {
                    value = "true";
                }
                else {
                    value = tokenizer.nextToken();
                }
            }
            catch(java.util.NoSuchElementException ignore) {
                value = "true";
            }
            setProperty(key, value);
        }
    }
}
