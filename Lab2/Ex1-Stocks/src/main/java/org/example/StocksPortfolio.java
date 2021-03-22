package org.example;

import java.util.ArrayList;

public class StocksPortfolio {
    String name;
    IStockMarket marketS;
    ArrayList<Stock> all_stocks = new ArrayList<>();

    public IStockMarket getMarketService(){
        return marketS;
    }

    public void setMarketService(IStockMarket newMarketS){
        marketS=newMarketS;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotalValue(){
        double total = 0;

        for(Stock stock: all_stocks){
            total += (marketS.getPrice(stock.getName())*stock.getQuantity());
        }
        return total;
    }

    public void addStock(Stock newStock){
        all_stocks.add(newStock);
    }

}
