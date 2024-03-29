package com.acme.statusmgr.beans;

public abstract class ServerStatusDecorator implements ServerStatusInterface {
    protected ServerStatusInterface detailedServer;

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
