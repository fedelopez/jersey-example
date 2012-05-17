package com;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.ClassNamesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import org.glassfish.grizzly.http.server.HttpServer;

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

    public static void main(String[] args) throws IOException {
        String host = (args.length == 0 ? "localhost" : "contract-interpreter.heroku.com");
        int port = (args.length == 0 ? 9998 : Integer.valueOf(args[0]));

        URI uri = baseURI(host, port);
        HttpServer httpServer = startServer(uri.getHost(), uri.getPort());
        String format = String.format("Jersey app started with WADL available at %sapplication.wadl\nTry out %sinterpret\nHit enter to stop it...", uri, uri);
        System.out.println(format);
        System.in.read();
        httpServer.stop();
    }

    private static URI baseURI(String localhost, int port) {
        return UriBuilder.fromUri("http://" + localhost + "/").port(port).build();
    }
}
