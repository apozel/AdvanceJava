package isen.m1.chaillan.server;

import java.net.ServerSocket;

/**
 * ServerAdmin
 */
public class ServerAdmin implements ServerAdminMBean {

    enum ServerStatus {
        STARTING, RUNNING, SHUTTING_DOWN, ERROR
    }

    public ServerStatus status = null;
    private ServerSocket server = null;

    public ServerAdmin(ServerSocket server) {
        this.server = server;
    }

    @Override
    public String getStatus() {
        return this.status.name();
    }

    
    public void setStatus(ServerStatus status) {
        this.status = status;
    }

    @Override
    public void shutdown() {
        this.status = ServerStatus.SHUTTING_DOWN;
        try {
            this.server.close();
        } catch (Exception e) {
            System.err.println(e);
        }
       

    }

    public ServerSocket getServer() {
        return server;
    }

    public void setServer(ServerSocket server) {
        this.server = server;
    }

    

}
