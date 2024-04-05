package com.acme.statusmgr;

import com.acme.statusmgr.beans.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Controller for all web/REST requests about the status of servers.
 * Supports both general server status and detailed server status.
 * <p>
 * Syntax for URLs:
 * All URLs start with /server
 * - /status: returns the general status of the server
 * - Optional parameter 'name' specifies a requestor name to appear in response
 * - Example: http://localhost:8080/server/status?name=Noach
 * - /status/detailed: returns detailed status of the server
 * - Optional parameters:
 * - 'name': specifies a requestor name to appear in response
 * - 'details': a list of server status details being requested
 * - Example: http://localhost:8080/server/status/detailed?details=tempLocation,totalJVMMemory,availableProcessors&name=Yankel
 */
@RestController
@RequestMapping("/server")
public class StatusController {
    private Logger logger = LoggerFactory.getLogger("StatusController");
    protected static final String template = "Server Status requested by %s";
    protected final AtomicLong counter = new AtomicLong();
    protected static SystemVariablesInterface systemVariables = new SystemVariablesFacade();

    public void setSystemVariables(SystemVariablesInterface systemInterface) {
        systemVariables = systemInterface;
    }

    /**
     * Process a request for general server status information.
     *
     * @param name optional param identifying the requester
     * @return a ServerStatus object containing the info to be returned to the requestor
     */
    @RequestMapping("/status")
    public ServerStatus getStatus(@RequestParam(value = "name", defaultValue = "Anonymous") String name) {
        return new ServerStatus(counter.incrementAndGet(), String.format(template, name));
    }

    /**
     * Process a request for detailed server status information.
     *
     * @param name    optional param identifying the requester
     * @param details optional param with a list of server status details being requested
     * @return a ServerStatus object containing the detailed info to be returned to the requestor
     * @throws ResponseStatusException if the required 'details' parameter is not present
     */
    @RequestMapping("/status/detailed")
    public ServerStatusInterface getDetailedStatus(
            @RequestParam(value = "name", defaultValue = "Anonymous") String name,
            @RequestParam(required = false) List<String> details) {

        ServerStatusInterface detailedStatus = new ServerStatus(counter.incrementAndGet(),
                String.format(template, name));

        if (details != null) {
            logger.info("Details were provided: " + Arrays.toString(details.toArray()));

            for (String detail : details) {
                switch (detail) {
                    case "availableProcessors" -> {
                        detailedStatus = new AvailableProcessorsDecorator(detailedStatus, systemVariables);

                    }
                    case "freeJVMMemory" -> {
                        detailedStatus = new FreeJVMMemoryDecorator(detailedStatus, systemVariables);

                    }
                    case "jreVersion" -> {
                        detailedStatus = new JREVersionDecorator(detailedStatus, systemVariables);

                    }
                    case "totalJVMMemory" -> {
                        detailedStatus = new TotalJVMMemoryDecorator(detailedStatus, systemVariables);

                    }
                    case "tempLocation" -> {
                        detailedStatus = new TempLocationDecorator(detailedStatus, systemVariables);

                    }
                    default -> invalidDetailsExceptions(detail);
                }

            }
            logger.info("Total cost for request was: " + detailedStatus.getRequestCost());
            return detailedStatus;
        }
        logger.info("Details parameter was null. No details provided. Bad Request Exception thrown.");
        // If details list was null, throw appropriate exception.
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Required request parameter 'details' for method" +
                " parameter type List is not present");
    }

    /**
     * Handles invalid detail options by throwing a ResponseStatusException.
     *
     * @param detail the invalid detail option
     * @throws ResponseStatusException indicating the invalid details option
     */
    public void invalidDetailsExceptions(String detail) {
        logger.info("Details provided were invalid. Provided detail was: " + detail + ". Bad Request Exception " +
                "thrown.");
        throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Invalid details option: " + detail);
    }
}
