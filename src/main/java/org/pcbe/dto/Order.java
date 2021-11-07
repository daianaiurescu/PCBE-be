package org.pcbe.dto;

import jakarta.persistence.*;

import java.io.Serializable;

// TODO: Remove entity annotations, since this is not an entity that is going to be stored in the database, only made to demonstrate
@Entity
@Table(name = "order_")
public class Order implements Serializable {

    @Id
    private String stockName;
    @Enumerated(EnumType.STRING)
    private OrderType type;
    private int quantity;

    public enum OrderType {
        SELL,
        BUY
    }

    public Order() {
    }

    public Order(OrderType type, String stockName, int quantity) {
        this.type = type;
        this.stockName = stockName;
        this.quantity = quantity;
    }

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order{" +
            "type=" + type +
            ", stockName='" + stockName + '\'' +
            ", quantity=" + quantity +
            '}';
    }
}
