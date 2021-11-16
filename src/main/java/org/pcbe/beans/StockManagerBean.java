package org.pcbe.beans;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.pcbe.model.Stock;

import java.util.List;

@ApplicationScoped
@Transactional
public class StockManagerBean {

    @PersistenceContext
    private EntityManager entityManager;

    public StockManagerBean() {
    }

    public void createStock(Stock stock) {
        entityManager.persist(stock);
    }

    public Stock getStock(String name) {
        return entityManager.find(Stock.class, name);
    }

    public List<Stock> getStocks() {
        return entityManager.createQuery("SELECT s FROM Stock s").getResultList();
    }

    public Stock updateStock(Stock stock) {
        return entityManager.merge(stock);
    }
}
