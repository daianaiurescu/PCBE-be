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

    private static final float SELL_PRICE_MULTIPLIER = 0.95f;
    private static final float BUY_PRICE_MULTIPLIER = 1.1f;

    public OrderProcessorBean() {
    }

    public Stock processOrder(Order order) {
        Stock stock = stockManager.getStock(order.getStockName());
        stock.setPrice(
            order.getType() == Order.OrderType.BUY
                ? stock.getPrice() * BUY_PRICE_MULTIPLIER
                : stock.getPrice() * SELL_PRICE_MULTIPLIER
        );

//        stock.setAvailableQuantity(
//            stock.getAvailableQuantity() + (
//                order.getType() == Order.OrderType.BUY
//                    ? +order.getQuantity()
//                    : -order.getQuantity()
//            )
//        );


        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        PriceTimestamp priceTimestamp = new PriceTimestamp(stock.getPrice(), timestamp);
        stock.addTimestamp(priceTimestamp);

        return stockManager.updateStock(stock);
    }

}
