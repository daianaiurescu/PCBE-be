package org.pcbe.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.pcbe.services.SimulatorService;

@Path("/simulate")
public class SimulateOrdersResource {

    @Inject
    private SimulatorService simulatorService;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/start")
    public void startSimulating() throws InterruptedException {
        simulatorService.simulate();
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/stop")
    public void stopSimulating() {
        simulatorService.stopSimulation();
    }

}
