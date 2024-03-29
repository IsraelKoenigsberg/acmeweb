package com.acme.statusmgr.beans;

import com.acme.servermgr.ServerManager;

/**
 * A POJO that represents Server Status and can be returned to Spring as the result of a request.
 */
public class ServerStatus implements ServerStatusInterface {
    private long id;                // Unique identifier of request, sequential number
    private String contentHeader;   // Some info about the request
    /**
     * requestCost constant will have to be changed if we change costs or calc dynamically
     */
    private final Integer requestCost = 1;  // the cost in pennies of this request.

    /**
     * Construct a ServerStatus using info passed in for identification.
     * This class must return a pretty, english-like representation of the server status.
     *
     * @param id            a numeric identifier/counter of which request this is
     * @param contentHeader info about the request
     */
    public ServerStatus(long id, String contentHeader) {
        this.id = id;
        this.contentHeader = contentHeader;
    }

    public ServerStatus() {

    }


    @Override
    public long getId() {
        return id;
    }


    @Override
    public String getContentHeader() {
        return contentHeader;
    }


    public String getStatusDesc() {
        // Obtain current status of server
        return "Server is " + ServerManager.getCurrentServerStatus();
    }


    public Integer getRequestCost() {
        return requestCost;
    }

}
