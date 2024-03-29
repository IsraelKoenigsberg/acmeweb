package com.acme.statusmgr.beans;

public class SystemVariables implements SystemVariablesInterface {

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
}
