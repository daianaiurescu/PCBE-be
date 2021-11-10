package org.pcbe.api;

import jakarta.annotation.Resource;
import jakarta.inject.Inject;
import jakarta.jms.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.pcbe.dto.Order;

@Path("/order")
public class OrderResource {

    @Resource(lookup = "jms/orderQueue")
    private Queue orderQueue;
    @Inject
    private JMSContext context;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String placeBuyOrder(Order order) {
        try {
            sendObjectMessage(order);
            return "Placed order";
        } catch (JMSException e) {
            e.printStackTrace();
        }

        return "Failed to place order";
    }

    private void sendObjectMessage(Order order) throws JMSException {
        ObjectMessage message = context.createObjectMessage();
        message.setObject(order);
        context.createProducer().send(orderQueue, message);
    }

}
