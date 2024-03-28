package com.acme.statusmgr.beans;

public class AvailableProcessors extends StatusDecorator {
    public AvailableProcessors(ServerStatusInterface server) {
        super(server);
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
           return detailedServer.getStatusDesc() + ", and there are " + getAvailableProcessors() + " " +
                   "processors available";
    }

    @Override
    public Integer getRequestCost() {
        return detailedServer.getRequestCost() + 3;
    }

    public int getAvailableProcessors() {
        return Runtime.getRuntime().availableProcessors();
    }

}
