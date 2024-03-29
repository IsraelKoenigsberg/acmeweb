package com.acme.statusmgr.beans;

/**
 * A class that implements the Decorator Pattern. Extends the Status Decorator class.
 * Add functionality about the available processors on the server.
 */
public class TempLocationDecorator extends ServerStatusDecorator {
    private SystemVariablesInterface systemVariables;

    /**
     * Constructor for TempLocationDecorator class.
     *
     * @param server          the server status interface to be decorated
     * @param systemVariables the type of system variables, mock or real.
     */

    public TempLocationDecorator(ServerStatusInterface server, SystemVariablesInterface systemVariables) {
        super(server);
        this.systemVariables = systemVariables;
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
        return detailedServer.getStatusDesc() + ", and the server's temp file location is "
                + getTempLocation();
    }

    @Override
    public Integer getRequestCost() {
        return detailedServer.getRequestCost() + 29;
    }

    /**
     * Retrieves the Temp Location.
     *
     * @return the Temp Location
     */

    public String getTempLocation() {
        return systemVariables.getTempLocation();
    }
}
