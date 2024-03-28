package com.acme.statusmgr.beans;

public abstract class StatusDecorator implements ServerStatusInterface {
    protected ServerStatusInterface detailedServer;

    StatusDecorator(ServerStatusInterface detailedServer) {
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
