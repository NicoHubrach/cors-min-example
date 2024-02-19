package com.mycompany.app;

import java.util.EnumSet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import jakarta.servlet.DispatcherType;

public class App {

    public static void main(String[] args) throws Exception {
        var server = new Server(8082);

        ServletContextHandler context = new ServletContextHandler();
        context.addServlet(Example.class, "/*");

        /**
         * Insofern die nächsten zwei Zeilen Code auskommentiert sind, erhalte ich einen Cors Fehler auf meiner Seite - der Expo Dev Server Seite.
         * Sind diese Zeilen aktiv werden bei mir nötige Header bei einer Antwort angehangen, die mir den Zugriff von meiner App aus erlauben.
         */
        FilterHolder cors = context.addFilter(CrossOriginFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
        cors.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");

        server.setHandler(context);

        server.start();
        server.join();
    }
}
