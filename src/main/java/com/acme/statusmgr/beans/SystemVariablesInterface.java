package com.acme.statusmgr.beans;

public interface SystemVariablesInterface {
    int getAvailableProcessors();
    long getFreeJVMMemory();
    Runtime.Version getJREVersion();

}
