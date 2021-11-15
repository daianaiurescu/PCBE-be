package org.pcbe.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.pcbe.dto.Order;
import org.pcbe.services.OrderService;

@Path("/order")
public class OrderResource {

    @Inject
    private OrderService orderService;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String placeBuyOrder(Order order) {
        return orderService.addOrder(order);
    }

}
