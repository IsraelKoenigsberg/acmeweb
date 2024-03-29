package com.acme.statusmgr.beans;

import com.acme.servermgr.ServerManager;

public interface ServerStatusInterface {

    /**
     * get the id of this request
     *
     * @return a numeric id that increases during life of server for each request .
     */
    public long getId();
    /**
     * Get the content header that was specified by the request
     *
     * @return some string
     */
    public String getContentHeader();
    /**
     * Get an english-like description of the server's current status,
     * obtained from the appropriate Manager class.
     *
     * @return A string describing status
     */
    public String getStatusDesc();
    /**
     * Get the cost of this request
     * @return Integer representing the cost of request as number of pennies
     */
    public Integer getRequestCost();

}
