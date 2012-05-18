package com;

import com.sun.grizzly.http.SelectorThread;
import com.sun.jersey.api.container.grizzly.GrizzlyWebContainerFactory;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @author FedericoL
 */
public class Main {

    protected static SelectorThread startServer(URI uri) throws IOException {
        System.out.println("Starting grizzly...");

        final Map<String, String> initParams = new HashMap<String, String>();
        initParams.put("com.sun.jersey.config.property.packages", "com");
        return GrizzlyWebContainerFactory.create(uri, initParams);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        String herokuPort = System.getenv("PORT");
        int port = (herokuPort == null ? 9998 : Integer.parseInt(herokuPort));
        String host = (herokuPort == null ? "localhost" : "0.0.0.0");

        URI uri = baseURI(host, port);
        SelectorThread server = startServer(uri);
        String format = String.format("Jersey app running: %b\nWADL available at %sapplication.wadl\nTry out %squestions", server.isRunning(), uri, uri);
        System.out.println(format);
        Thread.sleep(Long.MAX_VALUE);
    }

    private static URI baseURI(String localhost, int port) {
        return UriBuilder.fromUri("http://" + localhost + "/jsonp/").port(port).build();
    }
}
