/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.scannerapp.apps.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author damyant
 */
public class GetJsonUtil {
    
         
    /**
         * @auther Raj
         * Method to make http request
         * @param loginCredentials
         * @return
         * @throws IOException 
         */
        public static JSONObject getJsonfromServer(String serverUrl) throws IOException, JSONException{
           
               String json=null; 
               JSONObject jObj =null;
           try {
 
		URL url = new URL(serverUrl);
                //URL url = new URL("http://192.168.1.7:8080/kumulus/scanDo/authenticate?j_username=kumulus&j_password=password");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(60000);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Accept", "application/json");
 
                
                
                System.out.println("Input stream from server"+conn.getInputStream());
//		if (conn.getResponseCode() != 200) {
//			throw new RuntimeException("Failed : HTTP error code : "
//					+ conn.getResponseCode());
//		}
 
		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));
 
		String output;
                
                StringBuilder sb = new StringBuilder();
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
                    sb.append(output);
			//System.out.println(output);
		}
                System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"+sb);
                 json= sb.toString();
                try {
                 jObj = new JSONObject(json);
	           //json= jObj.getString("results");            
               System.out.println("Json String "+json +"Json Object"+jObj);  
              } catch (JSONException e) {
                  e.printStackTrace();
			}
		conn.disconnect();
 
	  } catch (MalformedURLException e) {
 
		e.printStackTrace();
 
	  } catch (IOException e) {
 
		e.printStackTrace();
 
	  }
           //   System.out.println("Json String "+json +"Json Object"+jObj);      
            return jObj;
       }
        
        
        
         public static String getJsonfromServertree(String serverUrl) throws IOException, JSONException{
           
               String json=null; 
               JSONObject jObj =null;
           try {
 
		URL url = new URL(serverUrl);
                //URL url = new URL("http://192.168.1.7:8080/kumulus/scanDo/authenticate?j_username=kumulus&j_password=password");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(60000);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Accept", "application/json");
 
                
                
                System.out.println("Input stream from server"+conn.getInputStream());
//		if (conn.getResponseCode() != 200) {
//			throw new RuntimeException("Failed : HTTP error code : "
//					+ conn.getResponseCode());
//		}
 
		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));
 
		String output;
                
                StringBuilder sb = new StringBuilder();
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
                    sb.append(output);
			//System.out.println(output);
		}
                System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"+sb);
                 json= sb.toString();
//                try {
//                 jObj = new JSONObject(json);
//	           //json= jObj.getString("results");            
//               System.out.println("Json String "+json +"Json Object"+jObj);  
//              } catch (JSONException e) {
//                  e.printStackTrace();
//			}
		conn.disconnect();
 
	  } catch (MalformedURLException e) {
 
		e.printStackTrace();
 
	  } catch (IOException e) {
 
		e.printStackTrace();
 
	  }
           //   System.out.println("Json String "+json +"Json Object"+jObj);      
            return json;
       }
    
}
