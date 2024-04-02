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
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Controller for all web/REST requests about the status of servers
 * <p>
 * For initial school project - just handles info about this server
 * Syntax for URLS:
 * All start with /server
 * /status  will give back status of server
 * a param of 'name' specifies a requestor name to appear in response
 * <p>
 * Examples:
 * http://localhost:8080/server/status
 * <p>
 * http://localhost:8080/server/status?name=Noach
 */
@RestController
@RequestMapping("/server")
public class StatusController {

    protected static final String template = "Server Status requested by %s";
    protected final AtomicLong counter = new AtomicLong();
    private static SystemVariablesInterface systemVariables = new SystemVariablesFacade();
    private boolean validDetail = false;
    public void setSystemVariables(SystemVariablesInterface systemInterface) {
        systemVariables = systemInterface;
    }

    /**
     * Process a request for server status information
     *
     * @param name optional param identifying the requester
     * @return a ServerStatus object containing the info to be returned to the requestor
     */
    @RequestMapping("/status")
    public ServerStatus getStatus(@RequestParam(value = "name", defaultValue = "Anonymous") String name) {

        return new ServerStatus(counter.incrementAndGet(),
                String.format(template, name));
    }

    /**
     * Process a request for detailed server status information
     *
     * @param name    optional param identifying the requester
     * @param details optional param with a list of server status details being requested
     * @return a ServerStatus object containing the info to be returned to the requestor
     */
    @RequestMapping("/status/detailed")
    public ServerStatusInterface getDetailedStatus(
            @RequestParam(value = "name", defaultValue = "Anonymous") String name,
            @RequestParam List<String> details) {

        ServerStatusInterface detailedStatus = new ServerStatus(counter.incrementAndGet(),
                String.format(template, name));

        if (details != null) {
            Logger logger = LoggerFactory.getLogger("StatusController");
            logger.info("Details were provided: " + Arrays.toString(details.toArray()));

            for (String detail : details) {
                switch (detail) {
                    case "availableProcessors" -> {
                        detailedStatus = new AvailableProcessorsDecorator(detailedStatus, systemVariables);
                        validDetail = true;
                    }
                    case "freeJVMMemory" -> {
                        detailedStatus = new FreeJVMMemoryDecorator(detailedStatus, systemVariables);
                        validDetail = true;
                    }
                    case "jreVersion" -> {
                        detailedStatus = new JREVersionDecorator(detailedStatus, systemVariables);
                        validDetail = true;
                    }
                    case "totalJVMMemory" -> {
                        detailedStatus = new TotalJVMMemoryDecorator(detailedStatus, systemVariables);
                        validDetail = true;
                    }
                    case "tempLocation" -> {
                        detailedStatus = new TempLocationDecorator(detailedStatus, systemVariables);
                        validDetail = true;
                    }
                    default -> checkForNonExistentDetail(detail);
                }
            }

            return detailedStatus;

        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Required request parameter 'details' for method" +
                " parameter type List is not present");
    }

    public void checkForNonExistentDetail(String detail) {
                if (validDetail){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid details option: " +detail);
                }
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Invalid details option: " + detail);

    }
}
