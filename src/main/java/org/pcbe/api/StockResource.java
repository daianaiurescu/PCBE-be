package org.pcbe.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.pcbe.beans.StockManagerBean;
import org.pcbe.model.Stock;

import java.util.List;

@Path("/stock")
public class StockResource {

    @Inject
    private StockManagerBean stockManager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Stock> getAll() {
        return stockManager.getStocks();
    }

}
