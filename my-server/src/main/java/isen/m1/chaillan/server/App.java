package isen.m1.chaillan.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

import isen.m1.chaillan.server.ServerAdmin.ServerStatus;
import isen.m1.chaillan.crud.DiskBookDAO;
import isen.m1.chaillan.util.Book;

/**
 * Hello world!
 *
 */
public class App {
    private static ExecutorService executor = Executors.newFixedThreadPool(20);
    private DiskBookDAO memoryBooks = DiskBookDAO.getInstance();

    public static void main(String[] args)
            throws IOException, InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException {
        int serverPort;
        try {
            serverPort = Integer.parseInt(args[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("no argument");
            serverPort = 8888;
        } catch (NumberFormatException e) {
            System.err.println("Argument" + args[0] + " must be an integer.");
            serverPort = 8888;
        }
        new App().server(serverPort);

    }

    public void server(int serverPort)
            throws IOException, InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException {
        ServerSocket server = null;
        Socket client = null;

        try {
            server = new ServerSocket(serverPort);

            // Get the MBean server
            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
            // register the MBean
            ServerAdmin serverAdmin = new ServerAdmin(server);
            ObjectName name = new ObjectName("com.chaillan:type=ServerAdmin");
            mbs.registerMBean(serverAdmin, name);
            serverAdmin.setStatus(ServerStatus.STARTING);
            while (true) {
                serverAdmin.setStatus(ServerStatus.RUNNING);
                client = server.accept();
                executor.execute(new Client(client));

            }

        } catch (Exception e) {
            System.out.println("shuting down the server");
        } finally {
            executor.shutdown();
            server.close();
            memoryBooks.close();
        }
    }

    /**
     * threadClient
     */
    class Client implements Runnable {

        Socket client = null;

        public Client(Socket client) {
            this.client = client;
        }

        public void run() {
            handleRequest(this.client);
        }

        public void handleRequest(Socket client) {
            try {
                System.out.println("a client has just connected");

                InputStream is = client.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String line = reader.readLine();
                System.out.println(line);
                if (line.contains("/books")) {
                    System.out.println("return :");
                    OutputStream os = client.getOutputStream();
                    PrintWriter pr = new PrintWriter(os);

                    pr.print("HTTP/1.1 200 \r\n");
                    pr.print("Content-Type: text/html\r\n");
                    pr.print("Connection: close\r\n");
                    pr.print("\r\n");
                    pr.println("<body><ul>" );
                    for (Book book : memoryBooks.findAll()) {
                        pr.println("<li> " + book.getTitle() + " </li>" );
                    }
                    pr.print("</ul>" + " \r \n" + "</body>");
                    pr.flush();
                }

            } 
            catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (client != null)
                    try {
                        client.close();
                    } catch (Exception e) {

                    }

            }
        }

    }
}
