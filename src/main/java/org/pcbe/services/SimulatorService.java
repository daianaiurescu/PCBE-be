package org.pcbe.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.pcbe.dto.Order;

@ApplicationScoped
public class SimulatorService {

    @Inject
    private OrderService orderService;

    @Inject
    private StockService stockService;

    private boolean runner;

    public void simulate() throws InterruptedException {
        runner = true;
        while (runner) {
            var list =stockService.getAll();
            var stock = list.get((int)(Math.random() * list.size()));
            var order = new Order();
            order.setStockName(stock.getName());
            order.setQuantity((int) (Math.random() * stock.getAvailableQuantity()));
            order.setType(((int)(Math.random() * 2 )) > 1 ? Order.OrderType.SELL : Order.OrderType.BUY);
            Thread.sleep(1000);
            orderService.addOrder(order);
        }
    }

    public void stopSimulation() {
        this.runner = false;
    }
}
