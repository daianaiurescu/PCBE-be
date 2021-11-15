package org.pcbe.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.pcbe.model.Stock;
import org.pcbe.services.StockService;

import java.util.List;

@Path("/stock")
public class StockResource {

    @Inject
    private StockService stockService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Stock> getAll() {
        return stockService.getAll();
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String addNewStock(Stock stock) {
        return stockService.addStock(stock);
    }

    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String ModifyStock(Stock stock) {
        return stockService.modifyStock(stock);
    }
}
