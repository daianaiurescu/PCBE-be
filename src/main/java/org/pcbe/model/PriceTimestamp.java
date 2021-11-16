package org.pcbe.model;

import jakarta.persistence.Embeddable;

import java.sql.Timestamp;

@Embeddable
public class PriceTimestamp {

    private float price;
    private Timestamp timestamp;

    public PriceTimestamp() {
    }

    public PriceTimestamp(float price, Timestamp timestamp) {
        this.price = price;
        this.timestamp = timestamp;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
