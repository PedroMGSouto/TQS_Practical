package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class StocksPortfolioTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getTotalValue() {
        IStockMarket marketS = mock(IStockMarket.class);

        StocksPortfolio portfolio = new StocksPortfolio();
        portfolio.setMarketService(marketS);

        when(marketS.getPrice("Amazon")).thenReturn(10.0);
        when(marketS.getPrice("Asus")).thenReturn(7.5);

        portfolio.addStock(new Stock("Amazon",6));
        portfolio.addStock(new Stock("Asus",4));
        double result = portfolio.getTotalValue();

        //Junit
        assertEquals(80.0,result);
        //Hamcrest
        assertThat(result, is(80.0));
        verify(marketS, times(2)).getPrice(anyString());
    }
}