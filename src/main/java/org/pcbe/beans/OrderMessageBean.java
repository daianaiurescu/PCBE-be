package org.pcbe.beans;

import jakarta.annotation.Resource;
import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.ejb.MessageDrivenContext;
import jakarta.jms.*;
import org.pcbe.dto.Order;

import java.util.logging.Level;
import java.util.logging.Logger;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/orderQueue"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue")
})
public class OrderMessageBean implements MessageListener {

    @Resource
    private MessageDrivenContext mdc;
    private static final Logger logger = Logger.getLogger(OrderMessageBean.class.toString());

    public OrderMessageBean() {
    }

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof ObjectMessage) {
                ObjectMessage objectMessage = (ObjectMessage) message;
                Order order = objectMessage.getBody(Order.class);
                logger.log(Level.INFO, order.toString());
            } else {
                logger.log(Level.WARNING, "Unknown message type {0}", message.getClass().getName());
            }
        } catch (JMSException e) {
            logger.log(Level.SEVERE, e.toString());
            mdc.setRollbackOnly();
        }
    }

}
