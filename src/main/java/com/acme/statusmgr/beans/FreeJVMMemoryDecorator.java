package com.acme.statusmgr.beans;

/**
 * A class that implements the Decorator Pattern. Extends the Status Decorator class.
 * Add functionality about the available processors on the server.
 */
public class FreeJVMMemoryDecorator extends ServerStatusDecorator {
    private SystemVariablesInterface systemVariables;

    /**
     * Constructor for FreeJVMMemory class.
     *
     * @param server          the server status interface to be decorated
     * @param systemVariables the type of system variables, mock or real.
     */

    public FreeJVMMemoryDecorator(ServerStatusInterface server, SystemVariablesInterface systemVariables) {
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
        return detailedServer.getStatusDesc() + ", and there are " + getJVMMemory() + " bytes of JVM memory free";
    }

    @Override
    public Integer getRequestCost() {
        return detailedServer.getRequestCost() + 7;
    }

    /**
     * Retrieves the number of free JVM Memory on the server.
     *
     * @return the number of free JVM on the server
     */

    public long getJVMMemory() {

        return systemVariables.getFreeJVMMemory();
    }
}
