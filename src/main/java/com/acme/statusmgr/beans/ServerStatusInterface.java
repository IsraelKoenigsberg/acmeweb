package com.acme.statusmgr.beans;

import com.acme.servermgr.ServerManager;

public interface ServerStatusInterface {


    public long getId();
    public String getContentHeader();
    public String getStatusDesc();
    public Integer getRequestCost();

}
