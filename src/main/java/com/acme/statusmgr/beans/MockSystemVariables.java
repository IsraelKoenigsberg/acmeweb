package com.acme.statusmgr.beans;

public class MockSystemVariables implements SystemVariablesInterface {
    @Override
    public int getAvailableProcessors() {
        return 4;
    }

    @Override
    public long getFreeJVMMemory() {
        return 127268272;
    }

    @Override
    public Runtime.Version getJREVersion() {
        return Runtime.Version.parse("15.0.2+7-27");
    }


}
