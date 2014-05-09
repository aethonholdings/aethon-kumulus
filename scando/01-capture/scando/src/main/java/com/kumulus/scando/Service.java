/*
 * Konstantinos - class implementing communication with server over REST API
 * using Jersey
 * Initialised at user logon, holds username and password to authenticate on 
 * server
 */
package com.kumulus.scando;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.client.urlconnection.HttpURLConnectionFactory;
import com.sun.jersey.client.urlconnection.URLConnectionClientHandler;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author theo
 */
public class Service {
    
    private final WebResource webService;
    
    public Service(String username, String password, String base_url)
    {
        ClientConfig config = new DefaultClientConfig();
        config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,
                        Boolean.TRUE);

        Client client;
        if (System.getenv("SCANDO_DEBUG_PROXY") != null)
        {
            client = new Client(new URLConnectionClientHandler(
                    new HttpURLConnectionFactory() {
                Proxy p = null;
                @Override
                public HttpURLConnection getHttpURLConnection(URL url) throws IOException {
                    if (p == null) {
                            p = new Proxy(Proxy.Type.HTTP,
                                    new InetSocketAddress("127.0.0.1", 8888));
                        }
                    return (HttpURLConnection) url.openConnection(p);
                }
            }), config);
        }
        else { client = Client.create(config); }

        HTTPBasicAuthFilter authenticationFilter = new HTTPBasicAuthFilter(username, password);
        client.addFilter(authenticationFilter);
        webService = client.resource(base_url);
    }

    private JSONObject request(String method, Object params) throws JSONException
    {
        ClientResponse response = webService.path("scanDo").path(method)
                                            .type(MediaType.APPLICATION_JSON_TYPE)
                                            .accept(MediaType.APPLICATION_JSON_TYPE)
                                            .post(ClientResponse.class, params);
        ClientResponse.Status status = response.getClientResponseStatus();
        if (status != ClientResponse.Status.OK)
            throw new RuntimeException(status.toString());
        else
            return new JSONObject(response.getEntity(String.class));
    }

    public void login() throws JSONException
    {
        request("authenticate", null);
    }

    public String getProject(String barcode) throws JSONException
    {
        MultivaluedMap request = new MultivaluedMapImpl();
        request.add("barcode", barcode);
        JSONObject json = request("getProjectBybarcode", request);
        return json.getString("projectId");
    }
    
}
