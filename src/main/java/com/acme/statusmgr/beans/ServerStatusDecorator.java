package com.acme.statusmgr.beans;

/**
 * Abstract class implementing the decorator pattern for extending the functionality
 * of server status objects.
 * <p>
 * This class serves as a base for creating decorator classes that add additional
 * behavior or information to server status objects.
 */
public abstract class ServerStatusDecorator implements ServerStatusInterface {
    protected ServerStatusInterface detailedServer;

    /**
     * Constructs a ServerStatusDecorator with the specified detailed server.
     *
     * @param detailedServer the detailed server status object to be decorated
     */
    ServerStatusDecorator(ServerStatusInterface detailedServer) {
        this.detailedServer = detailedServer;
    }

    @Override
    public long getId() {
        return detailedServer.getId();
    }

    @Override
    public String getContentHeader() {
        return detailedServer.getContentHeader();
    }

    @Override
    public String getStatusDesc() {
        return detailedServer.getStatusDesc();
    }

    @Override
    public Integer getRequestCost() {
        return detailedServer.getRequestCost();
    }
}
