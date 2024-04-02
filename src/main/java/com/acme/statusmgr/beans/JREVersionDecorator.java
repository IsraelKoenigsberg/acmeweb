package com.acme.statusmgr.beans;

/**
 * A class that implements the Decorator Pattern. Extends the Status Decorator class.
 * Add functionality about the available processors on the server.
 */
public class JREVersionDecorator extends ServerStatusDecorator {
    private SystemVariablesInterface systemVariables;

    /**
     * Constructor for JREVersion class.
     *
     * @param server          the server status interface to be decorated
     * @param systemVariables the type of system variables, mock or real.
     */

    public JREVersionDecorator(ServerStatusInterface server, SystemVariablesInterface systemVariables) {
        super(server);
        this.systemVariables = systemVariables;
    }

    @Override
    public long getId() {
        return super.getId();
    }

    @Override
    public String getContentHeader() {
        return super.getContentHeader();
    }

    @Override
    public String getStatusDesc() {
        return super.getStatusDesc() + ", and the JRE version is " + getJREVersion();
    }

    @Override
    public Integer getRequestCost() {
        return super.getRequestCost() + 19;
    }

    /**
     * Retrieves the JRE Version
     *
     * @return the JRE Version.
     */

    public Runtime.Version getJREVersion() {

        return systemVariables.getJREVersion();
    }
}
