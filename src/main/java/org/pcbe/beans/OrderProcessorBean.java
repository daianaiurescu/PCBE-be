package org.pcbe.beans;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.pcbe.dto.Order;
import org.pcbe.model.PriceTimestamp;
import org.pcbe.model.Stock;

import java.sql.Timestamp;
import java.util.Date;

@ApplicationScoped
public class OrderProcessorBean {

    @Inject
    private StockManagerBean stockManager;

    private static final float SELL_PRICE_MULTIPLIER_PER_UNIT = 0.0005f;
    private static final float BUY_PRICE_MULTIPLIER_PER_UNIT = 0.001f;

    public OrderProcessorBean() {
    }

    public boolean processOrder(Order order) {
        Stock stock = stockManager.getStock(order.getStockName());
        // TODO: Maybe make this prettier
        if (
            order.getType() == Order.OrderType.BUY && order.getQuantity() <= stock.getAvailableQuantity() ||
            order.getType() == Order.OrderType.SELL
        ) {
            stock.setPrice(
                order.getType() == Order.OrderType.BUY
                    ? stock.getPrice() * (1f + order.getQuantity() * BUY_PRICE_MULTIPLIER_PER_UNIT)
                    : stock.getPrice() * (1f - order.getQuantity() * SELL_PRICE_MULTIPLIER_PER_UNIT)
            );

            stock.setAvailableQuantity(
                stock.getAvailableQuantity() + (
                    order.getType() == Order.OrderType.SELL
                        ? +order.getQuantity()
                        : -order.getQuantity()
                )
            );

            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            PriceTimestamp priceTimestamp = new PriceTimestamp(stock.getPrice(), timestamp);
            stock.addTimestamp(priceTimestamp);
            stockManager.updateStock(stock);
            return true;
        }
        return false;
    }

    public Stock getStock(String name) {
        return stockManager.getStock(name);
    }

}
