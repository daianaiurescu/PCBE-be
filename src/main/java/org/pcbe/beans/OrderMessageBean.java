package org.pcbe.beans;

import jakarta.annotation.Resource;
import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.ejb.MessageDrivenContext;
import jakarta.inject.Inject;
import jakarta.jms.*;
import org.pcbe.dto.Order;
import org.pcbe.model.Stock;

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

    @Inject
    private OrderProcessorBean orderProcessor;

    public OrderMessageBean() {
    }

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof ObjectMessage) {
                ObjectMessage objectMessage = (ObjectMessage) message;
                Order order = objectMessage.getBody(Order.class);

                Stock stock = orderProcessor.processOrder(order);
                logger.log(Level.INFO, stock.toString());
            } else {
                logger.log(Level.WARNING, "Unknown message type {0}", message.getClass().getName());
            }
        } catch (JMSException e) {
            logger.log(Level.SEVERE, e.toString());
            mdc.setRollbackOnly();
        }
    }

}
