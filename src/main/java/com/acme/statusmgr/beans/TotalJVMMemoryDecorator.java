package com.acme.statusmgr.beans;

/**
 * A class that implements the Decorator Pattern. Extends the Status Decorator class.
 * Add functionality about the available processors on the server.
 */
public class TotalJVMMemoryDecorator extends ServerStatusDecorator {
    private SystemVariablesInterface systemVariables;

    /**
     * Constructor for TotalJVMMemoryDecorator class.
     *
     * @param server          the server status interface to be decorated
     * @param systemVariables the type of system variables, mock or real.
     */

    public TotalJVMMemoryDecorator(ServerStatusInterface server, SystemVariablesInterface systemVariables) {
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
        return detailedServer.getStatusDesc() + ", and there is a total of " + getTotalJVMMemory() +
                " bytes of JVM memory";
    }

    @Override
    public Integer getRequestCost() {
        return detailedServer.getRequestCost() + 13;
    }

    /**
     * Retrieves the number of total JVM Memory on the server.
     *
     * @return the number of total JVM on the server
     */

    public long getTotalJVMMemory() {
        return systemVariables.getTotalJVMMemory();
    }
}