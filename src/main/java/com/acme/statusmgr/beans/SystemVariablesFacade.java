package com.acme.statusmgr.beans;

public class SystemVariablesFacade implements SystemVariablesInterface {

    @Override
    public int getAvailableProcessors() {
        return Runtime.getRuntime().availableProcessors();
    }

    @Override
    public long getFreeJVMMemory() {
        return Runtime.getRuntime().freeMemory();
    }
    @Override
    public Runtime.Version getJREVersion(){
        return Runtime.version();
    }

    @Override
    public long getTotalJVMMemory() {
        return Runtime.getRuntime().totalMemory();
    }

    @Override
    public String getTempLocation() {
        return System.getenv("TEMP");
    }
}
