package com;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.ClassNamesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import org.glassfish.grizzly.http.server.HttpServer;
import sun.awt.windows.ThemeReader;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;

/**
 * @author FedericoL
 */
public class Main {

    protected static HttpServer startServer(String host, int port) throws IOException {
        System.out.println("Starting grizzly...");
        ResourceConfig rc = new ClassNamesResourceConfig(InterpretResource.class.getName());
        return GrizzlyServerFactory.createHttpServer(baseURI(host, port), rc);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        String herokuPort = System.getenv("PORT");
        int port = (herokuPort == null ? 9998 : Integer.parseInt(herokuPort));
        String host = (herokuPort == null ? "localhost" : "0.0.0.0");

        URI uri = baseURI(host, port);
        HttpServer server = startServer(uri.getHost(), uri.getPort());
        String format = String.format("Jersey app started: %b\nWADL available at %sapplication.wadl\nTry out %sinterpret", server.isStarted(), uri, uri);
        System.out.println(format);
        Thread.sleep(Long.MAX_VALUE);
    }

    private static URI baseURI(String localhost, int port) {
        return UriBuilder.fromUri("http://" + localhost + "/").port(port).build();
    }
}
