package org.pcbe.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.pcbe.beans.StockManagerBean;
import org.pcbe.model.Stock;

import java.util.List;

@ApplicationScoped
public class StockService {

    @Inject
    private StockManagerBean stockManager;

    public List<Stock> getAll() {
        return stockManager.getStocks();
    }


    public String addStock(Stock stock) {
        try {
            stockManager.createStock(stock);
            return "Stock added";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Failed to add a new stock";
    }

    public String modifyStock(Stock stock) {
        try {
            stockManager.updateStock(stock);
            return "Stock modified succesfully!";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Failed to modify stock";
    }

}
