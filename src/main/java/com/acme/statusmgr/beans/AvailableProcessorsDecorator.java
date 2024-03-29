package com.acme.statusmgr.beans;

/**
 * A class that implements the Decorator Pattern. Extends the Status Decorator class.
 * Add functionality about the available processors on the server.
 */
public class AvailableProcessorsDecorator extends ServerStatusDecorator {
    private SystemVariablesInterface systemVariables;

    /**
     * Constructor for AvailableProcessors class.
     *
     * @param server          the server status interface to be decorated
     * @param systemVariables the type of system variables, mock or real.
     */

    public AvailableProcessorsDecorator(ServerStatusInterface server, SystemVariablesInterface systemVariables) {
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
        return detailedServer.getStatusDesc() + ", and there are " + getAvailableProcessors() + " " +
                "processors available";
    }

    @Override
    public Integer getRequestCost() {
        return detailedServer.getRequestCost() + 3;
    }

    /**
     * Retrieves the number of available processors on the server.
     *
     * @return the number of available processors on the server
     */
    public int getAvailableProcessors() {

        return systemVariables.getAvailableProcessors();
    }

}
