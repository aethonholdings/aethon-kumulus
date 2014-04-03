/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.aethon.kumulus.stress;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.client.urlconnection.HttpURLConnectionFactory;
import com.sun.jersey.client.urlconnection.URLConnectionClientHandler;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author theo
 */
public class ScanDo {
    
    private final WebResource webService;
    
    public ScanDo(Properties p)
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

        HTTPBasicAuthFilter authenticationFilter = new HTTPBasicAuthFilter(p.auth_username, p.auth_password);
        client.addFilter(authenticationFilter);
        webService = client.resource(p.site_url);
    }

    public void login()
    {
        ClientResponse response = webService.path("scanDo").path("authenticate")
                                            .type(MediaType.APPLICATION_JSON_TYPE)
                                            .accept(MediaType.APPLICATION_JSON_TYPE)
                                            .post(ClientResponse.class, new ArrayList<String>());
        if (response.getClientResponseStatus() != ClientResponse.Status.OK)
            throw new UserCannotWorkException(UserCannotWorkReason.CANNOT_LOGIN);
    }

    public void locate(String barcode) throws RuntimeException
    {
        
    }

    
}
