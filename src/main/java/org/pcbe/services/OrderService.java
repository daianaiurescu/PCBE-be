package org.pcbe.services;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSException;
import jakarta.jms.ObjectMessage;
import jakarta.jms.Queue;
import org.pcbe.dto.Order;

@ApplicationScoped
public class OrderService {

    @Resource(lookup = "jms/orderQueue")
    private Queue orderQueue;

    @Inject
    private JMSContext context;

    public void sendObjectMessage(Order order) throws JMSException {
        ObjectMessage message = context.createObjectMessage();
        message.setObject(order);
        context.createProducer().send(orderQueue, message);
    }

    public String addOrder(Order order) {
        try {
            sendObjectMessage(order);
            return "Placed order";
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return "Failed to place order";
    }
}
