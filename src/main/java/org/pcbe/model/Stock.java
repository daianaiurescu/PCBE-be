package org.pcbe.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stock")
public class Stock {
    @Id
    private String name;
    private float price;
    private int availableQuantity;
    @ElementCollection
    private List<PriceTimestamp> timestamps;

    public Stock() {
    }

    public Stock(String name, float price, int availableQuantity, List<PriceTimestamp> timestamps) {
        this.name = name;
        this.price = price;
        this.availableQuantity = availableQuantity;
        this.timestamps = timestamps;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public List<PriceTimestamp> getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(List<PriceTimestamp> timestamps) {
        this.timestamps = timestamps;
    }

    public void addTimestamp(PriceTimestamp timestamp) {
        if (timestamps == null)
            timestamps = new ArrayList<>();

        timestamps.add(timestamp);
    }

    @Override
    public String toString() {
        return "Stock{" +
            "name='" + name + '\'' +
            ", price=" + price +
            ", availableQuantity=" + availableQuantity +
            ", timestamps=" + timestamps +
            '}';
    }
}
