package com.acme.statusmgr.beans;

/**
 * Interface representing system variables and information.
 */
public interface SystemVariablesInterface {

    /**
     * Retrieves the number of available processors in the system.
     *
     * @return the number of available processors
     */
    int getAvailableProcessors();

    /**
     * Retrieves the amount of free memory in the Java Virtual Machine.
     *
     * @return the amount of free memory in bytes
     */
    long getFreeJVMMemory();

    /**
     * Retrieves the version of the Java Runtime Environment (JRE).
     *
     * @return the version of the JRE
     */
    Runtime.Version getJREVersion();

    /**
     * Retrieves the total amount of memory in the Java Virtual Machine.
     *
     * @return the total amount of memory in bytes
     */
    long getTotalJVMMemory();

    /**
     * Retrieves the location of the temporary directory.
     *
     * @return the path to the temporary directory
     */
    String getTempLocation();
}
