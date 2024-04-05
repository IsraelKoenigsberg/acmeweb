package com.acme.statusmgr.beans;

/**
 * A facade implementation of the {@link SystemVariablesInterface} interface that provides
 * actual system information.
 * <p>
 * Retrieves system variables such as available processors, free JVM memory,
 * JRE version, total JVM memory, and temporary directory location. It uses methods from
 * the {@link Runtime} class and {@link System} class to gather this information.
 */
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
    public Runtime.Version getJREVersion() {
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
