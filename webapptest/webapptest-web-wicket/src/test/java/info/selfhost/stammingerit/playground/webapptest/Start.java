package info.selfhost.stammingerit.playground.webapptest;

import java.io.File;

import org.apache.wicket.util.time.Duration;
import org.eclipse.jetty.http.HttpVersion;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.webapp.WebAppContext;

public class Start {
    public static void main(String[] args) throws Exception {
        int timeout = (int) Duration.ONE_HOUR.getMilliseconds();

        Server server = new Server();
        ServerConnector httpConnector = new ServerConnector(server);

        // Set some timeout options to make debugging easier.
        httpConnector.setIdleTimeout(timeout);
        httpConnector.setSoLingerTime(-1);
        httpConnector.setPort(8080);
        server.addConnector(httpConnector);

		// check if a keystore for a SSL certificate is available, and
		// if so, start a SSL connector on port 8443. By default, the
		// quickstart comes with a Apache Wicket Quickstart Certificate
		// that expires about half way september 2021. Do not use this
		// certificate anywhere important as the passwords are available
		// in the source.

        Resource keystore = Resource.newClassPathResource("/keystore");
        if (keystore != null && keystore.exists()) {
            SslContextFactory factory = new SslContextFactory();
            factory.setKeyStoreResource(keystore);
            factory.setKeyStorePassword("wicket");
            factory.setTrustStoreResource(keystore);
            factory.setKeyManagerPassword("wicket");

            ServerConnector httpsConnector = new ServerConnector(server, new SslConnectionFactory(factory, HttpVersion.HTTP_1_1.asString()));
            httpsConnector.setIdleTimeout(timeout);
            httpsConnector.setPort(8443);
            httpsConnector.setAcceptQueueSize(4);
            server.addConnector(httpsConnector);

            System.out.println("SSL access to the quickstart has been enabled on port 8443");
            System.out.println("You can access the application using SSL on https://localhost:8443");
            System.out.println();
        }

        WebAppContext bb = new WebAppContext();
        bb.setServer(server);
        bb.setContextPath("/");
        if (new File("webapptest-web-wicket/src/main/webapp").exists()) {
            bb.setWar("webapptest-web-wicket/src/main/webapp");
        }
        else {//needed for running inside eclipse
            bb.setWar("src/main/webapp");
        }

        // START JMX SERVER
        // MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        // MBeanContainer mBeanContainer = new MBeanContainer(mBeanServer);
        // server.getContainer().addEventListener(mBeanContainer);
        // mBeanContainer.start();

        server.setHandler(bb);

        try {
            System.out.println(">>> STARTING EMBEDDED JETTY SERVER, PRESS ANY KEY TO STOP");
            server.start();
            System.in.read();
            System.out.println(">>> STOPPING EMBEDDED JETTY SERVER");
            server.stop();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
