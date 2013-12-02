package com.scannerapp.apps.utils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

/**
 * A utility class to create the connection of the client with the server.
 * 
 * @author Shalin Shah<shalin.shah@spec-india.com>
 * @since 15/July/2013
 */
public class ConnectionUtil {

	private static ClientConfig config = null;
	private static Client client = null;
	private static WebResource webService = null;

	private static boolean isObjectsInitialized = false;

	/**
	 * Method to get {@link WebResource} object.
	 * 
	 * @return to get {@link WebResource} object
	 */
	public static WebResource getWebService() {

		if (!isObjectsInitialized) {
			initObjects();
		}

		return webService;
	}

	/**
	 * Method to initialize objects.
	 */
	private static void initObjects() {

		config = new DefaultClientConfig();
		config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,
				Boolean.TRUE);

		client = Client.create(config);

		webService = client.resource(ConstantUtil.getApplicationConstant("webServerURL"));

		isObjectsInitialized = true;
	}
}
